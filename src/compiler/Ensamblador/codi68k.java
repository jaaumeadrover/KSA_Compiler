package compiler.Ensamblador;

import compiler.GeneracioCodiIntermedi.Instruccio;
import compiler.GeneracioCodiIntermedi.Variable;
import compiler.GeneracioCodiIntermedi.codiTresAdreces;
import java.io.IOException;
import java.util.ArrayList;

public class codi68k {

    private Writer f;
    private ArrayList<String> parametres;
    private codiTresAdreces codi;

    public codi68k(String file,codiTresAdreces codi) throws IOException{
        f= new Writer(file);
        this.parametres=new ArrayList<>();
        this.codi=codi;
    }
    public void generaAssembly() throws IOException{
        f.escriureFitxer("ORG $700");
        for (int i = 0; i < codi.getTv().getSize(); i++) {
            if(codi.getTv().get(i).getConstant()){
                generaConstant(codi.getTv().get(i));
            }else{
                generaVariable(codi.getTv().get(i));
            }
        }
        f.escriureFitxer("ORG $1000");
        f.escriureFitxer("START");
        
        //GETSIZE DE Tv?
        for(int i = 0; i<codi.getSize();i++){
            generaInstruccio(codi.Instruccions().get(i));
        }

        f.escriureFitxer("JMP FINAL");
        f.escriureFitxer("FINAL");
        f.escriureFitxer("END START");
        f.tancafitxer();
    }

    public void generaConstant(Variable constant) throws IOException{
        f.escriureFitxer(constant.getNom() + " EQU  " + constant.getValor());
    }

    public void generaVariable(Variable var) throws IOException{
        f.escriureFitxer(var.getNom() + " DS.L 1");
    }

    private void generaInstruccio(Instruccio instruccio) {
        switch (instruccio.getOperacions()){
            // OPERACIONS ARITÈTIQUES I LÒGIQUES
            case COPIA:
                // Miram si la instruccio es un procediment
                if (instruccio.getparametreDreta().getTipus==OperandsCTA.procediment){
                    f.escriureFitxer("\tMOVE.L (A0)," + instruccio.getDesti());
                    f.escriureFitxer("\tMOVE.L (A7)+,A0"); // Ficam el procediment a la pila
                    for (int i = this.parametres.size() - 1; i >= 0; i--) {// Anem ficant tots els paràmetre a la pila
                        f.escribirFichero("\tMOVE.L (A7)+," + this.parametres.get(i)); // Fiquem el paràmetre a la pila
                    }
                    this.parametres.clear();
                }else{ // no es un procediment
                    if (instruccio.getOperadorDreta().getTipus() == OperandsCTA.variable) { // es una variable
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + ")," + instruccio.getDesti());
                    } else {
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDreta() + "," + instruccio.getDesti());
                    }
                }
                break;

            case SUMA:
                if (instruccio.getOperadorEsquerra().getTipus()==OperandsCTA.enterLit)) {  // el primer operant es un enter
                    if (instruccio.getOperadorDret().getTipus()==OperandsCTA.enterLit)) { // el segon operand també és un enter
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
                    if (instruccio.getOperadorDret().getTipus().equals(OperandsCTA.enterLit)) { //el segon operand és un enter literal
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L #" + instruccio.getOperadorDret() + ",D1");
                        f.escriureFitxer("\tADD.L D0,D1");
                        f.escriureFitxer("\tMOVE.L D1," + inst.destino);

                    } else { // els dos operands no son enters
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorEsquerra() + "),D0");
                        f.escriureFitxer("\tMOVE.L (" + instruccio.getOperadorDreta() + "),D1");
                        f.escriureFitxer("\tADD.L D0, D1");
                        f.escriureFitxer("\tMOVE.L D1, " + instruccio.getDesti());
                    }
                }
                break;

            case RESTA:
                if (inst.param1.type.equals(TiposOperandoC3A.enteroLit)) {  //x=#*?
                    if (inst.param2.type.equals(TiposOperandoC3A.enteroLit)) { //x= #*#
                        f.escribirFichero("\tMOVE.L #" + inst.param1 + ",D0");
                        f.escribirFichero("\tMOVE.L #" + inst.param2 + ",D1");
                        f.escribirFichero("\tSUB.L D1, D0");
                        f.escribirFichero("\tMOVE.L D0, " + inst.destino);
                    } else { //x=#*v
                        f.escribirFichero("\tMOVE.L #" + inst.param1 + ",D0");
                        f.escribirFichero("\tMOVE.L (" + inst.param2 + "),D1");
                        f.escribirFichero("\tSUB.L D1, D0");
                        f.escribirFichero("\tMOVE.L D0, " + inst.destino);
                    }
                } else { //x = v*?
                    if (inst.param2.type.equals(TiposOperandoC3A.enteroLit)) { //x= v*#
                        f.escribirFichero("\tMOVE.L (" + inst.param1 + "),D0");
                        f.escribirFichero("\tMOVE.L #" + inst.param2 + ",D1");
                        f.escribirFichero("\tSUB.L D1, D0");
                        f.escribirFichero("\tMOVE.L D0, " + inst.destino);
                    } else { //x=v*v
                        f.escribirFichero("\tMOVE.L (" + inst.param1 + "),D0");
                        f.escribirFichero("\tMOVE.L (" + inst.param2 + "),D1");
                        f.escribirFichero("\tSUB.L D1, D0");
                        f.escribirFichero("\tMOVE.L D0, " + inst.destino);
                    }
                }
                break;

            case PRODUCTE:
                break;

            case DIVISIO:
                break;

            case MODUL:
                break;

            case MENYS:
                break;

            case AND:
                break;

            case OR:
                break;

            case NOT:
                break;

            // OPERACIONS INDEXACIÓ
            case INDVAL:
                break;

            case INDASS:
                break;

            // OPERACIONS DE BIFURCACIÓ
            case SKIP:
                f.escriureFitxer(instruccio.getDesti() + " :");
                break;

            case GO:
                break;

            case IF:
                break;

            case LT:
                break;

            case LE:
                break;

            case EQ:
                break;

            case NE:
                break;

            case GE:
                break;

            case GT:
                break;

            // CRIDES A PROCEDIMENTS
            case PMB:
                break;

            case CALL:
                break;

            case RTN:
                break;

            case PARAMS:
                break;

            case PARAMC:
                break;

            // MOSTRAR PER PANTALLA I DEMANAR ENTRADA PER TECLAT
            case PRINT:
                break;

            case INPUT:
                break;

        }
    }


}