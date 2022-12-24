package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolForPostExpression {

    private SymbolExpressioSimple expressioSimple;

    public SymbolForPostExpression(SymbolExpressioSimple expr, codiTresAdreces codi){
        this.expressioSimple=expr;
        System.out.println("SOC UN SYMBOL FORPOSTEXPRESSION");
    }

    public SymbolForPostExpression(codiTresAdreces codi){
        System.out.println("SOC UN SYMBOL FORPOSTEXPRESSION");
    }
}