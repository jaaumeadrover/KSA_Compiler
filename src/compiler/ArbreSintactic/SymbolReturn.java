package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolReturn extends SymbolBase{

    private SymbolExpressioSimple expr;
    public SymbolReturn(SymbolExpressioSimple expr){
        this.expr = expr;
        System.out.println("soy un SymbolReturn");
    }
}