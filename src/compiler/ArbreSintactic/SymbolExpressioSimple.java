package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolExpressioSimple {

    private SymbolExpressioSimple expressioSimple;
    private SymbolOperacio operacio;
    private SymbolValor valor;
    private String iden;
    private SymbolVarInit varinit;
    private TipusSub tsOperador;
    private TipusSub tsResultat;

    /*
    Constructor en el qual hi ha més d'una expressió
     */
    public SymbolExpressioSimple(SymbolExpressioSimple expr, SymbolOperacio oper) {
        this.expressioSimple = expr;
        this.operacio = oper;
        this.tsOperador = operacio.getTipusSub();
        setTsResultat();

    }

    public SymbolExpressioSimple() {
    }

    /*
    Constructor expressio Simple sense expressions interiors.
     */
    public SymbolExpressioSimple(SymbolValor val, SymbolOperacio oper) {
        this.expressioSimple = null;
        this.valor = val;
        this.operacio = oper;
        this.tsOperador = operacio.getTipusSub();

        //if oper is null
        if (oper.isEmpty() || (oper == null)) {
            this.tsResultat = val.getTipusSub();

        } else {
            if (oper.isAssignacio()) {

            } else {
                setTsResultat();
            }
        }
        //else 
    }

    public SymbolExpressioSimple(String id, SymbolVarInit varini) {
        this.iden = id;
        this.varinit = varinit;

    }

    public TipusSub tsOperador() {
        return tsOperador;
    }

    public SymbolExpressioSimple getExpr() {
        return expressioSimple;
    }

    /*
    tsResultat:
    NULL: si incorrecto
    INT:
    BOOLEAN: correcte per al whileStatement
    STRING: 
     */
    public void setTsResultat() {
        TipusSub x = null;

        //Cas 1: expressió sense expressions anteriors
        if (this.expressioSimple == null) {
            switch (this.valor.getTipusSub()) {
                //Cas Valor Int i operador aritmètic
                case INT:

                    //Pot ser signe aritmètic o real
                    if (operacio.getTipusSub() == TipusSub.INT) {
                        //signe aritmètic
                        if (operacio.getOperador().getTipusOperador() == 'A') {
                            x = TipusSub.INT;
                            //signe real
                        } else {
                            x = TipusSub.BOOLEAN;
                        }
                    }
                    break;
                case BOOLEAN:
                    //Si signe boolean, expressió correcta amb resultat boolean
                    if (operacio.getTipusSub() == TipusSub.BOOLEAN) {
                        x = TipusSub.BOOLEAN;
                    }
                    break;

            }

            //Cas 2: expressió amb expressins dintre ella.
        } else {

            //mentre no quedin expressons per recórrer
            SymbolExpressioSimple exprSimple = new SymbolExpressioSimple(this.expressioSimple, this.expressioSimple.getOperacio());
            if (exprSimple.getTipusSubResultat() == null) {
                //Hi ha hagut un format incorrecte
                x = null;
            } else {
                x = exprSimple.getTipusSubResultat();
            }

        }
        if (x == null) {
        }
        this.tsResultat = x;
    }

    public void setTsResultatAssignacio() {
       this.tsResultat=this.operacio.getTipusSub();
    }

    public TipusSub getTipusSubResultat() {
        return this.tsResultat;
    }

    public SymbolOperacio getOperacio() {
        return operacio;
    }
}
