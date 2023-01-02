package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolForPostExpression {

    private SymbolExpressioSimple expressioSimple;

    public SymbolForPostExpression(SymbolExpressioSimple expr){
        this.expressioSimple=expr;
    }

    public SymbolForPostExpression(){

    }

    public SymbolExpressioSimple getExpressioSimple() {
        return expressioSimple;
    }

    public void codiTresAdreces(codiTresAdreces codi){

        this.expressioSimple.codiTresAdreces(codi);
    }
}