package compiler.Ensamblador;

import compiler.GeneracioCodiIntermedi.Instruccio;
import compiler.GeneracioCodiIntermedi.OperandsCTA;
import compiler.GeneracioCodiIntermedi.Variable;
import compiler.GeneracioCodiIntermedi.codiTresAdreces;
import java.io.IOException;
import java.util.ArrayList;

public class codi68k {

    private Writer f;
    private ArrayList<String> parametres;
    private codiTresAdreces codi;
    private int numLoops = 0;

    public codi68k(String file, codiTresAdreces codi) throws IOException {
        f = new Writer(file);
        this.parametres = new ArrayList<>();
        this.codi = codi;
    }

    public void generaAssembly() throws IOException {
        f.escriureFitxer("\tORG $700");
        for (int i = 0; i < codi.getTv().getSize(); i++) {
            if (codi.getTv().get(i).getConstant()) {
                generaConstant(codi.getTv().get(i));
            } else {
                generaVariable(codi.getTv().get(i));
            }
        }
        f.escriureFitxer("\tORG $1000");
        f.escriureFitxer("START:");

        //GETSIZE DE Tv?
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
        f.escriureFitxer(var.getNom() + " DS.L 1");
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
                } else { // no es un procediment
                    if (!instruccio.getOperadorEsquerra().equals("null")) {

                        if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.variable) { // es una variable
                            f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + ")," + instruccio.getDesti());
                        } else {
                            //if string
                            if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.stringLit) {
                                //GUARDAM DINS PILA A1
                                f.escriureFitxer("\tMOVE.L A1,-(A7)");
                                //String txt = instruccio.getOperadorEsquerra().getOperand().substring(1, longitud);
                                f.escriureFitxer("\tLEA "+instruccio.getDesti()+",A1");
                                String txt = instruccio.getOperadorEsquerra().getOperand().replaceAll(Character.toString('"'), "");
                                int longitud = txt.length();
                                int i = 0;
                                for (int j = 0; j < (longitud) / 4; i = i + 4) {
                                    f.escriureFitxer("\tMOVE.L #'" + txt.substring(i, i + 4) + "',(A1)+");
                                    j++;
                                }
                                if ((longitud) % 4 != 0) {
                                    f.escriureFitxer("\tMOVE.L #'" + txt.substring(i, i + (longitud % 4)) + "',(A1)+");
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
                        f.escriureFitxer("\t.DBRA D1,.LOOP" + numLoops);
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
                f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "), D0");
                f.escriureFitxer("\tMOVE.L (" + codi.getTv().get(Integer.parseInt(instruccio.getOperadorDreta().getOperand())).getNom() + "), D1");
                f.escriureFitxer("\tAND.L D0,D1");
                f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                break;

            case OR:
                f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "), D0");
                f.escriureFitxer("\tMOVE.L (" + codi.getTv().get(Integer.parseInt(instruccio.getOperadorDreta().getOperand())).getNom() + "), D1");
                f.escriureFitxer("\tOR.L D0,D1");
                f.escriureFitxer("\tMOVE.L D1," + instruccio.getDesti());
                break;

            case NOT:
                if (instruccio.getOperadorEsquerra().getTipus() == OperandsCTA.enterLit) {  //operand es un enter
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + ",D0");
                    f.escriureFitxer("\tNOT.L D0");
                    f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                } else { //no es enter
                    f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D0");
                    f.escriureFitxer("\tNOT.L D0");
                    f.escriureFitxer("\tMOVE.L D0, " + instruccio.getDesti());
                }

                break;

            // OPERACIONS INDEXACIÓ
            case INDVAL:
                break;

            case INDASS:
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
                if (instruccio.getDesti().contains("t")) {
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
                        System.out.println("                ERROR CONFUSIÓ VAR");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                    }
                    f.escriureFitxer("\tCMP.L  D1,D0");
                    f.escriureFitxer("\tBEQ " + instruccio.getDesti());
                    //CMP
                    //BNE

                }
                break;

            case NE:
                if (instruccio.getOperadorEsquerra().getTipus().equals(OperandsCTA.enterLit)) {
                    // CAS A,B dos entersLit
                    if (instruccio.getOperadorDreta().getTipus().equals(OperandsCTA.enterLit)) {
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta().getOperand() + ",D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBNE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { //CAS A enterLit, B variable
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorEsquerra().getOperand() + ",D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBNE e_" + instruccio.getDesti());
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
                        f.escriureFitxer("\tBNE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    } else { // Cas A es variable, B es variable
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra().getOperand() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta().getOperand() + "),D1");
                        f.escriureFitxer("\tCMP.L  D1,D0");
                        f.escriureFitxer("\tBNE e_" + instruccio.getDesti());
                        f.escriureFitxer("\tMOVE.L #0," + instruccio.getDesti());
                        f.escriureFitxer("\tJMP ef_" + instruccio.getDesti());
                        f.escriureFitxer("e_" + instruccio.getDesti() + ":");
                        f.escriureFitxer("\tMOVE.L #-1," + instruccio.getDesti());//assignar true
                        f.escriureFitxer("ef_" + instruccio.getDesti() + ":");//final
                    }
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
                for (int i = this.codi.getTp().getTaulaProc().get(this.codi.getTp().getTaulaSize() - 1).getParametres().size() - 1; i >= 0; i--) {
                    //obtenim la posicio del parametre a la pila
                    int posPila = ((this.codi.getTp().getTaulaProc().get(this.codi.getTp().getTaulaProc().size() - 1).getParametres().size() - i) * 4) + 4;
                    f.escriureFitxer("\tMOVE.L " + posPila + "(A7),"
                            + codi.getTp().getTaulaProc().get(this.codi.getTp().getTaulaProc().size() - 1).getParametres().get(i).getNom());
                }
                f.escriureFitxer("\tMOVEM.L D0-D2,-(A7)");
                break;

            case CALL:
//                if (instruccio.getDesti() != null) { // CAS FUNCIO
//                    f.escribirFichero("\tJSR " + inst.getDesti()); //subrutina per el CALL
//                    f.escribirFichero("\tMOVE.L (A7)+,(" + parametros.get(0) + ")");//Recuperació de parámetros
//                    f.escribirFichero("\tADD.L #1,SL"); //Aumentro SL
//                    parametros.clear();
//                    break;
//
//                } else { //CAS PROCEDIMENT
//                    if ("escriure".equals(inst.destino.toString())) {
//
//                        f.escribirFichero("\tJSR " + inst.destino);// CALL
//                        f.escribirFichero("\tMOVE.L D2,-(A7)");
//                        f.escribirFichero("\tMOVE.L D2," + parametros.get(0));
//                        f.escribirFichero("\tMOVE.L (A7)+,D2");
//                        f.escribirFichero("\tMOVE.L (A7)+," + parametros.get(0));
//                        f.escribirFichero("\tADD.L #1,SL");
//                        parametros.clear();
//                        break;
//                    } else {
//                        if ("neg".equals(inst.destino.toString())) {
//                            f.escribirFichero("\tMOVE.L D1,-(A7)");
//                            f.escribirFichero("\tJSR " + inst.destino);// CALL
//                            f.escribirFichero("\tMOVE.L D1," + inst.param2);
//                            f.escribirFichero("    MOVE.L (A7)+,D1\n\tMOVE.L (A7)+," + parametros.get(0));
//                            parametros.clear();
//                        } else {
//                            f.escribirFichero("\tMOVE.L A0,-(A7)");// CALL
//                            f.escribirFichero("\tJSR " + inst.destino);// CALL
//                            if (TablaProcedimientos.getProcedimiento2(inst.destino.toString()).tipo == tipoSub.tipoSubVoid) {
//                                f.escribirFichero("\tMOVE.L (A7)+,A0");
//                                for (int i = parametros.size() - 1; i >= 0; i--) {
//                                    f.escribirFichero("\tMOVE.L (A7)+," + parametros.get(i));
//                                }
//                                parametros.clear();
//                            }
//                        }
//                    }
//                }
                break;

            case RTN:
                if (instruccio.getDesti() != null) { // si hi ha desti movem la instruccio a una posicio de memoria
                    f.escriureFitxer("\tMOVE.L #" + instruccio.getDesti() + ",A0");
                }
                f.escriureFitxer("\tMOVEM.L (A7)+,D0-D2");
                f.escriureFitxer("\tRTS");
                break;

            case PARAMS:
                this.parametres.add(instruccio.getDesti());
                f.escriureFitxer("\tMOVE.L (" + instruccio.getDesti() + "),-(A7)");
                break;

            case PARAMC:

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

}
