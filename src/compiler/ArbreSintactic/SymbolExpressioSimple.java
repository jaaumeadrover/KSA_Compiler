package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolExpressioSimple {

    private SymbolOperacio operacio;
    private SymbolValor valor;
    private TipusSub tsResultat;
    private boolean esBuit;

    /*
    Constructor buit en cas d'errors
     */
    public SymbolExpressioSimple() {
        this.esBuit = true;
    }

    /*
    Constructor que rep per paràemtres valor i operació
     */
    public SymbolExpressioSimple(SymbolValor val, SymbolOperacio oper) {
        this.valor = val;
        this.operacio = oper;

        this.tsResultat = valor.getTipusSub();

        if (oper.isEmpty()) {
            this.tsResultat = valor.getTipusSub();
        } else {
            if (oper.getExpr() != null) {
                //si expressions anteriors son correctes
                if (!oper.getExpr().esBuit()) {
                    setTsResultat();
                }
            }
        }
    }

    //obtenir tipus resultat si operació no es buit
    private void setTsResultat() {
        if (this.tsResultat == this.operacio.getTipusSub()) {
            if (!this.operacio.isAssignacio()) {
                if (this.operacio.getOperador().getTipusOperador() != 'A') {
                    this.tsResultat = TipusSub.BOOLEAN;
                } else {
                    this.tsResultat = TipusSub.INT;
                }
            }
        } else {
            this.tsResultat = null;
        }
    }

    public SymbolValor getValor() {
        return this.valor;
    }

    public SymbolOperacio getOperacio() {
        return operacio;
    }

    public TipusSub getTipusSubResultat() {
        return this.tsResultat;
    }

    public boolean esBuit() {
        return this.esBuit;
    }

    @Override
    public String toString() {
        if (this.valor != null) {
            return this.valor.toString();
        } else {
            return "";
        }
    }

    /*
    Aquest mètode retorna un String que sirà utilitzat en altres classes com per exemple SymbolValor.
     */
    public String codiTresAdreces(codiTresAdreces codi) {

        //Cas assignació [copy,b,null,a]
        if (this.operacio.isAssignacio()) {
            String valor = this.valor.codiTresAdreces(codi, true);
            String oper = this.operacio.getExpr().codiTresAdreces(codi);
            Operand o1;
            if(Character.isAlphabetic(oper.charAt(0))){
                 o1 = new Operand(oper, OperandsCTA.variable);
            }else{
                 o1 = new Operand(oper, OperandsCTA.enterLit);
            }

            int pos1 = valor.indexOf('[');
            int pos2 = valor.indexOf(']');

            //Assignació amb variable no array
            if (pos1 == -1) {
                //mirar tipus o1
                codi.generar(TipusInstruccionsCTA.COPIA, o1, null, valor);
            } else {

                String index = valor.substring(pos1 + 1, pos2);
                String iden = valor.substring(0, pos1);
                Operand o2 = new Operand(index, OperandsCTA.variable);

                codi.generar(TipusInstruccionsCTA.PRODUCTE, o2, new Operand("4", OperandsCTA.enterLit), index);


                codi.generar(TipusInstruccionsCTA.INDASS, o1, o2, iden+"_"+codi.getTp().getNumProcActius());
            }
            return null;

        } else {
            //Cas no assignació
            String valor = this.valor.codiTresAdreces(codi, false);
            Operand val = new Operand(valor, OperandsCTA.variable);

            //si no es té operador i és un valor simple
            if (this.operacio.isEmpty()) {
                //retornam la variable temporal
                return valor;
            } else {
                //es té un operador i s'ha de generar codi
                TipusInstruccionsCTA tipus = this.operacio.getOperador().codiTresAdreces(codi);
                String op2 = this.operacio.getExpr().codiTresAdreces(codi);
                Operand o2;
                if (Character.isAlphabetic(op2.charAt(0))) {
                    o2 = new Operand(op2, OperandsCTA.variable);
                } else {
                    o2 = new Operand(op2, OperandsCTA.enterLit);
                }

                //crear variable temporal
                String temp = codi.addVariable(TipusSub.INT, "t");

                codi.generar(tipus, val, o2, temp);
                return temp;
            }

        }

    }

}
