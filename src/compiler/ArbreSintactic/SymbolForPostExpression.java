package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolForPostExpression extends SymbolBase{

    private SymbolExpressioSimple expressioSimple;

    public SymbolForPostExpression(SymbolExpressioSimple expr){
        this.expressioSimple=expr;
        System.out.println("SOC UN SYMBOL FORPOSTEXPRESSION");
    }

    public SymbolForPostExpression(){
        System.out.println("SOC UN SYMBOL FORPOSTEXPRESSION");
    }
}