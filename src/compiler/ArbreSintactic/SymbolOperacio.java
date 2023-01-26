package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

/*
Classe SymbolOperacio:
ATRIBUTS:
    SimbolOperador
    Simbol ExpressioSimple
    SimbolVarInit
    TipusSub --> IMPORTANT!! REPRESENTA EL TIPUS SUBJACENT DE EXPRESSIO QUE POT ANAR ABANS.
                 SI ES NULL, HI HA UN FORMAT INCORRECTE.
 */
public class SymbolOperacio {

    private SymbolOp operador;
    private SymbolExpressioSimple expressioSimple;
    private SymbolVarInit varInit;
    private TipusSub tipusSBAnterior;
    private boolean isEmpty;
    private boolean esAssignacio;

    /*
    Cas en que symbol operació agafa VarInit que pot ser lambda.
     */
    public SymbolOperacio(SymbolVarInit simbol) {
        this.varInit = simbol;

        if (!simbol.esBuit()) {
            this.esAssignacio = true;
            if (simbol.getExpr()!=null) {
                 this.tipusSBAnterior = simbol.getExpr().getTipusSubResultat();
            }else{
                    
            }
        } else {
            this.isEmpty = true;
        }
    }

    /*
    Cas en que SymbolOperació utilitza un operador i una altra expressió simple
     */
    public SymbolOperacio(SymbolOp op, SymbolExpressioSimple expr) {
        this.operador = op;
        this.expressioSimple = expr;
        setTsResultat();
    }

    /*s
    VERIFICAR SymbolOperació.
     */
    private void setTsResultat() {
        TipusSub x = null;

        switch (operador.getTipusOperador()) {
            //Cas 1: Symbol Aritmètic(+,*
            case 'A':
                if (expressioSimple.getTipusSubResultat() == TipusSub.INT) {
                    x = TipusSub.INT;
                }
                break;
            //Cas 2: Symbol Boolean
            case 'B':
                if (expressioSimple.getTipusSubResultat() == TipusSub.BOOLEAN) {
                    System.out.println("SET TS RESULTAT SYMBOLOPERACIO");
                    x = TipusSub.BOOLEAN;
                }
                break;
            //Cas 3: Symbol Real
            case 'R':
                if (expressioSimple.getTipusSubResultat() == TipusSub.INT) {
                    x = TipusSub.INT;
                }
                break;
        }
        this.tipusSBAnterior = x;

    }

    public SymbolOp getOperador() {
        return operador;
    }

    public TipusSub getTipusSub() {
        return tipusSBAnterior;
    }

    public SymbolExpressioSimple getExpr() {
        if (this.varInit != null) {
            return this.varInit.getExpr();
        }
        return this.expressioSimple;
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public boolean isAssignacio() {
        return this.esAssignacio;
    }

    public SymbolVarInit getVarInit() {
        return varInit;
    }

    public TipusInstruccionsCTA codiTresAdreces(codiTresAdreces codi) {
        // Cas op expressioSimple
        if (operador != null) {
            TipusInstruccionsCTA op = operador.codiTresAdreces(codi);
            //codi.genera(op, null, null, null);
            expressioSimple.codiTresAdreces(codi);
            return op;
            // Cas VARINIT
        } else {
            return null;
        }
    }

}
