package compiler.Ensamblador;

import compiler.GeneracioCodiIntermedi.Instruccio;
import compiler.GeneracioCodiIntermedi.OperandsCTA;
import compiler.GeneracioCodiIntermedi.Parametre;
import compiler.GeneracioCodiIntermedi.Procediment;
import compiler.GeneracioCodiIntermedi.Variable;
import compiler.GeneracioCodiIntermedi.codiTresAdreces;
import compiler.Symbols.TaulaSimbols.TipusSub;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class codi68k {

    private Writer f;
    private ArrayList<String> parametres;
    private codiTresAdreces codi;
    private int numLoops = 0;

    public codi68k(String file, codiTresAdreces codi) throws IOException {
        f = new Writer(file,".68K");
        this.parametres = new ArrayList<>();
        this.codi = codi;
    }

    public void generaAssembly() throws IOException {
        f.escriureFitxer("\tORG $700");
        f.escriureFitxer("input DS.L 15"); //variable reservada per emmagatzemar input
        for (int i = 0; i < codi.getTv().getSize(); i++) {
            if (codi.getTv().get(i).getConstant()) {
                generaConstant(codi.getTv().get(i));
            } else {
                generaVariable(codi.getTv().get(i));
            }
        }
        f.escriureFitxer("\tORG $1000");
        f.escriureFitxer("START:");

        for (int i = 0; i < codi.getSize(); i++) {
            generaInstruccio(codi.Instruccions().get(i));
        }

        f.escriureFitxer("\tJMP FINAL");
        f.escriureFitxer("FINAL:");
        f.escriureFitxer("\tEND START");
        f.tancaFitxer();
    }

    public void generaConstant(Variable constant) throws IOException {
        f.escriureFitxer(constant.getNom() + " EQU  " + constant.getValor());
    }

    public void generaVariable(Variable var) throws IOException {
        if(var.getTipusSub()==TipusSub.STRING){
            f.escriureFitxer(var.getNom() + " DS.L "+"15");
        }else{
            f.escriureFitxer(var.getNom() + " DS.L "+var.getDimensio());
        }

    }

    private void generaInstruccio(Instruccio instruccio) throws IOException {
        switch (instruccio.getOperacions()) {
            // OPERACIONS ARITÈTIQUES I LÒGIQUES
            case COPIA:
                // Miram si la instruccio es un procediment
                if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.procediment) {
                    f.escriureFitxer("\tMOVE.L (A0)," + instruccio.getDesti());
                    f.escriureFitxer("\tMOVE.L (A7)+,A0"); // Ficam el procediment a la pila
                    for (int i = this.parametres.size() - 1; i >= 0; i--) {// Anem ficant tots els paràmetre a la pila
                        f.escriureFitxer("\tMOVE.L (A7)+," + this.parametres.get(i)); // Fiquem el paràmetre a la pila
                    }
                    this.parametres.clear();


                } else {

                    // no es un procediment
                    if (!instruccio.getOperadorEsquerra().equals("null")) {

                        if (instruccio.getOperadorEsquerra().getOperand().charAt(0)!='"') { // es una variable

                            if(!Character.isAlphabetic(instruccio.getOperadorEsquerra().getOperand().charAt(0))){//si enterlit
                                f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + "," + instruccio.getDesti());
                            }else{
                                Variable var=codi.getTv().getVariable(instruccio.getOperadorEsquerra().getOperand());
                                //cas variable String
                                if(var.getTipusSub()==TipusSub.STRING){
                                    //GUARDAM DIRECCIÓ DE MEMÒRIOA DINS REGISTRE DIRECCIONS
                                    f.escriureFitxer("\tLEA " + instruccio.getDesti() + ",A1");
                                    f.escriureFitxer("\tLEA " + instruccio.getOperadorEsquerra().getOperand() + ",A2");

                                    //bucle
                                    f.escriureFitxer("bucle_"+instruccio.getOperadorEsquerra().getOperand()+":");
                                    f.escriureFitxer("\tMOVE.B (A2)+,D1");
                                    f.escriureFitxer("\tCMP.B #0,D1");
                                    f.escriureFitxer("\tBEQ finalBucle_"+instruccio.getOperadorEsquerra().getOperand());
                                    f.escriureFitxer("\tMOVE.B D1,(A1)+");
                                    f.escriureFitxer("\tJMP bucle_"+instruccio.getOperadorEsquerra().getOperand());
                                    f.escriureFitxer("finalBucle_"+instruccio.getOperadorEsquerra().getOperand()+":");
                                    f.escriureFitxer("\tMOVE.B #0,(A1)");

                                }else{
                                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + ")," + instruccio.getDesti());//si variable

                                }
                            }

                        } else {
                            //if string
                            if (instruccio.getOperadorEsquerra().getOperand().charAt(0)=='"') {
                                //GUARDAM DINS PILA A1
                                f.escriureFitxer("\tMOVE.L A1,-(A7)");
                                f.escriureFitxer("\tLEA " + instruccio.getDesti() + ",A1");
                                String txt = instruccio.getOperadorEsquerra().getOperand().replaceAll(Character.toString('"'), "");
                                int longitud = txt.length();
                                int i = 0;
                                for (int j = 0; j < (longitud) / 4; i = i + 4) {
                                    f.escriureFitxer("\tMOVE.L #'" + txt.substring(i, i + 4) + "',(A1)+");
                                    j++;
                                }
                                int x=(longitud) % 4;
                                int j=0;
                                if (x!=0) {
                                    for (; j < x; j++) {
                                        f.escriureFitxer("\tMOVE.B #'" + txt.substring(i+j, i+j+1) + "',(A1)+");
                                    }
                                    for (; j< 4; j++) {
                                        f.escriureFitxer("\tMOVE.B #0,(A1)+");
                                    }
                                }
                                f.escriureFitxer("\tMOVE.L #" + 0 + ",(A1)");
                                f.escriureFitxer("\tMOVE.L (A7)+,A1");
                            } else {
                                f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + "," + instruccio.getDesti());

                            }

                        }
                    }
                }
                break;

            case SUMA:
                if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.enterLit) {  // el primer operant es un enter
                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) { // el segon operand també és un enter
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                        f.escriureFitxer("\tADD.L D0,D1"); // sumam els dos operands
                        f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());

                    } else { //el primer operand es un enter i el segon no
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                        //// com el 2n operand no es un enter, no el podem moure amb '#', hem d'utilitzar '(x)'
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tADD.L D0,D1"); // sumam
                        f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                    }
                } else { // el primer operand no és un enter literal
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) { //el segon operand és un enter literal
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                        f.escriureFitxer("\tADD.L D0,D1");
                        f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());

                    } else { // els dos operands no son enters
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tADD.L D0, D1");
                        f.escriureFitxer("\tMOVE.L D1, " + instruccio.getDesti());
                    }
                }
                break;

            case RESTA:
                if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.enterLit) {  //el primer operand es un enter
                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) { //el segon operand tambe es un enter
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                        f.escriureFitxer("\tSUB.L D1, D0");
                        f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                    } else { //el segon operan no és un enter
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tSUB.L D1, D0");
                        f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                    }
                } else { //el primer operand no es un enter
                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) { //el segon operand es un enter
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                        f.escriureFitxer("\tSUB.L D1, D0");
                        f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                    } else { //cap operand es un enter
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tSUB.L D1, D0");
                        f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                    }
                }
                break;

            case PRODUCTE:

                if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.enterLit) { // El primer operand es un ernter
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) { //el segon operan també és un enter
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                        f.escriureFitxer("\tSUB.L #1, D1");
                        f.escriureFitxer("\tCLR.L D2");
                        f.escriureFitxer(".LOOP" + numLoops + "\n\tADD.L D0,D2\n");
                        f.escriureFitxer("\tDBRA D1,.LOOP" + numLoops + "\n");
                        f.escriureFitxer("\tMOVE.L D2," + instruccio.getDesti());
                    } else { //el segon operador és una variable
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tSUB.L #1, D1");
                        f.escriureFitxer("\tCLR.L D2");
                        f.escriureFitxer(".LOOP" + numLoops + "\n\tADD.L D0,D2\n");
                        f.escriureFitxer("\tDBRA D1,.LOOP" + numLoops);
                        f.escriureFitxer("\tMOVE.L D2," + instruccio.getDesti());
                    }
                } else { //el primer operador és una variable
                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) { //el segon operador és un enter literal
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                        f.escriureFitxer("\tSUB.L #1, D1");
                        f.escriureFitxer("\tCLR.L D2");
                        f.escriureFitxer(".LOOP" + numLoops + "\n\tADD.L D0,D2\n");
                        f.escriureFitxer("\tDBRA D1,.LOOP" + numLoops);
                        f.escriureFitxer("\tMOVE.L D2," + instruccio.getDesti());
                    } else { //el segon operador també és una variable
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tSUB.L #1, D1");
                        f.escriureFitxer("\tCLR.L D2");
                        f.escriureFitxer(".LOOP" + numLoops + "\n\tADD.L D0,D2\n");
                        f.escriureFitxer("\tDBRA D1,.LOOP" + numLoops);
                        f.escriureFitxer("\tMOVE.L D2," + instruccio.getDesti());
                    }
                }
                numLoops++;
                break;

            case DIVISIO:
                if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.enterLit) {  //el primer operand es un enter
                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) { //els dos operands son enters
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                        f.escriureFitxer("\tCLR.L D2"); // utilitzam D2 per el resultat
                        // feim un bucle fins que l'operador de l'esquerra sigui 0, i botam a ENDLOOP
                        f.escriureFitxer(".LOOP" + numLoops + "\n\tCMP.L #0,D0\n\tBEQ .ENDLOOP" + numLoops + "\n");
                        f.escriureFitxer("\tSUB.L D1,D0\n\tADD.L #1,D2\n"); //es compten a D2 quants de pics podem restar D0 i D1
                        f.escriureFitxer("\tJMP .LOOP" + numLoops); // tornam al bucle
                        f.escriureFitxer(".ENDLOOP" + numLoops + "\n\tMOVE.L D2," + instruccio.getDesti());

                    } else { //el segon operand no es enter
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tCLR.L D2"); // utilitzam D2 per el resultat
                        // feim un bucle fins que l'operador de l'esquerra sigui 0, i botam a ENDLOOP
                        f.escriureFitxer(".LOOP" + numLoops + "\n\tCMP.L #0,D0\n\tBEQ .ENDLOOP" + numLoops + "\n");
                        f.escriureFitxer("\tSUB.L D1,D0\n\tADD.L #1,D2\n"); //es compten a D2 quants de pics podem restar D0 i D1
                        f.escriureFitxer("\tJMP .LOOP" + numLoops); // tornam al bucle
                        f.escriureFitxer(".ENDLOOP" + numLoops + "\n\tMOVE.L D2," + instruccio.getDesti());
                    }

                } else { //el primer operador no es enter

                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) { //el segon operand es enter
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                        f.escriureFitxer("\tCLR.L D2"); // utilitzam D2 per el resultat
                        // feim un bucle fins que l'operador de l'esquerra sigui 0, i botam a ENDLOOP
                        f.escriureFitxer(".LOOP" + numLoops + "\n\tCMP.L #0,D0\n\tBEQ .ENDLOOP" + numLoops + "\n");
                        f.escriureFitxer("\tSUB.L D1,D0\n\tADD.L #1,D2\n"); //es compten a D2 quants de pics podem restar D0 i D1
                        f.escriureFitxer("\tJMP .LOOP" + numLoops); // tornam al bucle
                        f.escriureFitxer(".ENDLOOP" + numLoops + "\n\tMOVE.L D2," + instruccio.getDesti());

                    } else { //cap operand es enter
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tCLR.L D2"); // utilitzam D2 per el resultat
                        // feim un bucle fins que l'operador de l'esquerra sigui 0, i botam a ENDLOOP
                        f.escriureFitxer(".LOOP" + numLoops + "\n\tCMP.L #0,D0\n\tBEQ .ENDLOOP" + numLoops + "\n");
                        f.escriureFitxer("\tSUB.L D1,D0\n\tADD.L #1,D2\n"); //es compten a D2 quants de pics podem restar D0 i D1
                        f.escriureFitxer("\tJMP .LOOP" + numLoops); // tornam al bucle
                        f.escriureFitxer(".ENDLOOP" + numLoops + "\n\tMOVE.L D2," + instruccio.getDesti());
                    }
                }
                numLoops++;
                break;

            case MODUL:
                if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.enterLit) {  //el primer operand es enter
                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) { //els dos son enters
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D1");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D0");
                        f.escriureFitxer("\tDIVU D0,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                    } else { //el segon no es enter
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D1");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D0");
                        f.escriureFitxer("\tDIVU D0,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                    }
                } else { //el primer operand no es enter
                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) { //el segon operand és un enter
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D1");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D0");
                        f.escriureFitxer("\tDIVU D0,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                    } else { //el segon operan també és una variable
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D1");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D0");
                        f.escriureFitxer("\tDIVU D0,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tASR.L #4,D1");
                        f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                    }
                }
                break;

            case MENYS: // Fem com que restam en 0
                if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.enterLit) {  //operand es un enter
                    f.escriureFitxer("\tMOVE.L #0,D0");
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D1");
                    f.escriureFitxer("\tSUB.L D1, D0");
                    f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());

                } else { //no es enter
                    f.escriureFitxer("\tMOVE.L 0,D0");
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                    f.escriureFitxer("\tSUB.L D1, D0");
                    f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                }
                break;

            case AND:
                //cas Operand esquerra
                if(!Character.isAlphabetic(instruccio.getOperadorEsquerra().getOperand().charAt(0))){
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ", D0");
                }else{
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "), D0");
                }
                //cas Operand dret
                if(!Character.isAlphabetic(instruccio.getOperadorDreta().getOperand().charAt(0))){
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ", D0");
                }else{
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "), D1");
                }
                f.escriureFitxer("\tAND.L D0,D1");
                f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                break;

            case OR:
                //cas Operand esquerra
                if(!Character.isAlphabetic(instruccio.getOperadorEsquerra().getOperand().charAt(0))){
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ", D0");
                }else{
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "), D0");
                }
                //cas Operand dret
                if(!Character.isAlphabetic(instruccio.getOperadorDreta().getOperand().charAt(0))){
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ", D0");
                }else{
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "), D1");
                }
                f.escriureFitxer("\tOR.L D0,D1");
                f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                break;

            case NOT:
                if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.enterLit) {  //operand es un enter
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra() + ",D0");
                    f.escriureFitxer("\tNOT.L D0");
                    f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                } else { //no es enter
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                    f.escriureFitxer("\tNOT.L D0");
                    f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                }

                break;

            // OPERACIONS INDEXACIÓ
            case INDVAL:
                String str=instruccio.getOperadorEsquerra().getOperand();

                //Cas global
                if(!esParametre(str)){
                    f.escriureFitxer("\tLEA.L " + instruccio.getOperadorEsquerra() + ",A0");
                }else{//cas subprograma
                    f.escriureFitxer("\tMOVE.L " + instruccio.getOperadorEsquerra() + ",A0");
                }
                //f.escriureFitxer("\tLEA.L " + instruccio.getOperadorEsquerra() + ",A0");
                f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D0");
                f.escriureFitxer("\tMOVE.L A0,D1");
                f.escriureFitxer("\tADD.L A0,D0");
                f.escriureFitxer("\tMOVE.L D0,A0");
                f.escriureFitxer("\tMOVE.L (A0),(" + instruccio.getDesti() + ")");
                break;

            case INDASS:
                String str2=instruccio.getDesti();
                f.escriureFitxer("\tMOVEM.L D0-D2/A0,-(A7)");
                f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D0");

                //cas programa principal
                if(!esParametre(str2)){
                    f.escriureFitxer("\tLEA.L " + instruccio.getDesti() + ",A0");
                }else{//cas array es parametre
                    f.escriureFitxer("\tMOVE.L " + instruccio.getDesti() + ",A0");
                }
                f.escriureFitxer("\tMOVE.L A0,D1");
                f.escriureFitxer("\tADD.L A0,D0");
                f.escriureFitxer("\tMOVE.L D0,A0");
                f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),(A0)");
                f.escriureFitxer("\tMOVEM.L (A7)+,D0-D2/A0");
                break;


            // OPERACIONS DE BIFURCACIÓ
            case SKIP:
                f.escriureFitxer(instruccio.getDesti() + ":");
                break;

            case GOTO:
                f.escriureFitxer("\tJMP " + instruccio.getDesti());
                break;

            case LT:
                if (instruccio.getOperadorEsquerra().getTipus().equals(OperandsCTA.enterLit)) {
                    // CAS A,B dos entersLit
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBLT e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { //CAS A enterLit, B variable
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBLT e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }

                } else {
                    //CAS A es variable,B es literal
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D1");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D0");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBLT e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { // Cas A es variable, B es variable
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBLT e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }
                }
                break;

            case LE:
                if (instruccio.getOperadorEsquerra().getTipus().equals(OperandsCTA.enterLit)) {
                    // CAS A,B dos entersLit
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBLE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { //CAS A enterLit, B variable
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBLE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }

                } else {
                    //CAS A es variable,B es literal
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D1");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D0");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBLE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { // Cas A es variable, B es variable
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBLE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }
                }
                break;
            //EQUALS {=}
            case EQ:
                //CAS EMMAGATZEMAR VALOR DINS VARIABLE (bool n <- 2 = 3  EN AQUEST EXEMPLE n VAL false)
                //[EQ,a,b,t_x]
                if (instruccio.getDesti().charAt(0)=='t') {
                    if (instruccio.getOperadorEsquerra().getTipus().equals(OperandsCTA.enterLit)) {
                        // CAS A,B dos entersLit
                        if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                            f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                            f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                            f.escriureFitxer("\tCMP.L  D1,D0");
                            f.escriureFitxer("\tBEQ e_" + instruccio.getDesti());
                            f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                            f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                            f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                            f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                            f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                        } else { //CAS A enterLit, B variable
                            f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                            f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                            f.escriureFitxer("\tCMP.L  D1,D0");
                            f.escriureFitxer("\tBEQ e_" + instruccio.getDesti());
                            f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                            f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                            f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                            f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                            f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                        }

                    } else {
                        //CAS A es variable,B es literal
                        if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                            f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D1");
                            f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D0");
                            f.escriureFitxer("\tCMP.L  D1,D0");
                            f.escriureFitxer("\tBEQ e_" + instruccio.getDesti());
                            f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                            f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                            f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                            f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                            f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                        } else { // Cas A es variable, B es variable
                            f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                            f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                            f.escriureFitxer("\tCMP.L  D1,D0");
                            f.escriureFitxer("\tBEQ e_" + instruccio.getDesti());
                            f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                            f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                            f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                            f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                            f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                        }
                    }

                } else {//CAS CONDICIÓ IF/WHILE/FOR/SWITCH

                    //OPERADOR ESQUERRA
                    if (instruccio.getOperadorEsquerra().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                    } else {
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                    }
                    //OPERAND DRETA
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.boolea)) {
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                    } else {
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                    }
                    f.escriureFitxer("\tCMP.L  D1,D0");
                    f.escriureFitxer("\tBEQ " + instruccio.getDesti());
                    //CMP
                    //BNE

                }
                break;

            case NE:
                //Operador Esquerra
                if(instruccio.getOperadorEsquerra().getTipus().equals(OperandsCTA.enterLit)){
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                }else{
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                }

                //Operador Dret
                if(instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)){
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                }else{
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                }

                //CAS EMMAGATZEMAR VARIABLES DINS TEMPORAL
                if(instruccio.getDesti().charAt(0)=='t'){
                    f.escriureFitxer("\tCMP.L D0,D1");

                    f.escriureFitxer("\tBEQ e_" + instruccio.getDesti());
                    f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());
                    f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                    f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                    f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());//assignar true
                    f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final

                }else{//CAS SALT DIRECTE
                    f.escriureFitxer("\tCMP.L D0,D1");
                    f.escriureFitxer("\tBNE " + instruccio.getDesti());
                }

                break;
            //GREATER EQUALS
            case GE:
                if (instruccio.getOperadorEsquerra().getTipus().equals(OperandsCTA.enterLit)) {
                    // CAS A,B dos entersLit
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBGE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { //CAS A enterLit, B variable
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBGE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }

                } else {
                    //CAS A es variable,B es literal
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D1");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D0");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBGE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { // Cas A es variable, B es variable
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBGE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }
                }
                break;

            case GT:

                if (instruccio.getOperadorEsquerra().getTipus().equals(OperandsCTA.enterLit)) {
                    // CAS A,B dos entersLit
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBGT e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { //CAS A enterLit, B variable
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBGT e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }

                } else {
                    //CAS A es variable,B es literal
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBGT e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { // Cas A es variable, B es variable
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBGT e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }
                }
                break;

            // CRIDES A PROCEDIMENTS
            case PMB:
                //feim un recorregut per la taula de procediments per obtenir tots els parametres, començant per el final
                Procediment p = this.codi.getTp().getProcediment(instruccio.getDesti());
                for (int i = 0; i <=p.getParametres().size() - 1; i++) {
                //obtenim la posicio del parametre a la pila
                    int posPila = ((p.getParametres().size()-1 - i) * 4) + 4;
                    f.escriureFitxer("\tMOVE.L " + posPila + "(A7),"
                            + p.getParametres().get(i).getNom());
                }

                f.escriureFitxer("\tMOVEM.L D0-D2,-(A7)");
                break;


            case CALL:
                f.escriureFitxer("\tJSR e" + instruccio.getOperadorEsquerra().getOperand());
                if (instruccio.getDesti()!=null){
                    f.escriureFitxer("\tMOVE.L (A2),(" + instruccio.getDesti() + ")");
                }
                //obtenim proc
                Procediment proc =codi.getTp().getProcediment(instruccio.getOperadorEsquerra().getOperand());
                int params=proc.getParametres().size();
                //restablim l'ordre de la pila com estava
                for (int i = 0; i < params; i++) {
                    f.escriureFitxer("\tMOVE.L (A7)+,D7");
                }

                break;

            case RTN:
                if (instruccio.getDesti() != null) { // si hi ha desti movem la instruccio a una posicio de memoria

                    f.escriureFitxer("\tMOVE.L (" + instruccio.getDesti() + "),(A2)");
                }
                f.escriureFitxer("\tMOVEM.L (A7)+,D0-D2");
                f.escriureFitxer("\tRTS");
                break;

            case PARAMS:
                this.parametres.add(instruccio.getOperadorEsquerra().getOperand());
                Variable var = codi.getTv().getVariable(instruccio.getOperadorEsquerra().getOperand());
                if(var.getDimensio()>1){
                    f.escriureFitxer("\tLEA " + instruccio.getOperadorEsquerra() + ",A0");
                    f.escriureFitxer("\tMOVE.L A0,-(A7)");
                }else{
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),-(A7)");
                }

                break;

            case PARAMC:
                f.escriureFitxer("\tMOVEM.L D0-D1/A0,-(A7)");
                if(!esParametre(instruccio.getDesti())){
                    f.escriureFitxer("\tLEA.L " + instruccio.getDesti() + ",A0");

                }else{
                    f.escriureFitxer("\tMOVE.L " + instruccio.getDesti() + ",A0");

                }
                f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                f.escriureFitxer("\tMOVE.L (A0,D0),D2");
                f.escriureFitxer("\tMOVEM.L (A7)+,D0-D1/A0");
                f.escriureFitxer("\tMOVE.L D2,-(A7)");
                break;

            //MOSTRAR PER PANTALLA I DEMANAR ENTRADA PER TECLAT
            case PRINT:
                //[PRINT,tx/var,null,null]
                //GUARDAM VARIABLES DINS PILA
                f.escriureFitxer("\tMOVE.L D0,-(A7)");
                f.escriureFitxer("\tMOVE.L A1,-(A7)");
                //
                f.escriureFitxer("\tLEA " + instruccio.getOperadorEsquerra() + ",A1");
                f.escriureFitxer("\tMOVE.W #14,D0");
                f.escriureFitxer("\tTRAP #15");
                //RETORNAM VALOR A VARIABLES ANTERIORS
                f.escriureFitxer("\tMOVE.L (A7)+,A1");
                f.escriureFitxer("\tMOVE.L (A7)+,D0");
                break;
            case INPUT:
                //GUARDAM VARIABLES DINS PILA
                f.escriureFitxer("\tMOVE.L D0,-(A7)");
                f.escriureFitxer("\tMOVE.L A1,-(A7)");
                //POSAM PARAMETRES PER A TRAP #15
                //RETORNAM VALOR A VARIABLES ANTERIORS
                f.escriureFitxer("\tMOVE.W #2,D0");
                f.escriureFitxer("\tLEA "+instruccio.getDesti()+",A1");
                f.escriureFitxer("\tTRAP #15");
                
                //CONTROLAR TAMANY INTRODUIT DE STRING? AMB CMP D1.W

                //RETORNAM VALOR A VARIABLES ANTERIORS
                f.escriureFitxer("\tMOVE.L (A7)+,A1");
                f.escriureFitxer("\tMOVE.L (A7)+,D0");
                break;
        }
    }

    private boolean esParametre(String s){
        boolean parametre=false;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);

        String number="";
        if (matcher.find()) {
            number = matcher.group();
        }
        int num=Integer.parseInt(number);
        Procediment proc2 = null;
        if(num!=0){
            proc2=codi.getTp().getProcediment(num-1);
        }
        if(proc2!=null){
            ArrayList<Parametre> params2=proc2.getParametres();
            for (int i = 0; i < params2.size(); i++) {
                if(params2.get(i).getNom().equals(s)){
                    parametre=true;
                }
            }
        }
        return parametre;
    }


}
