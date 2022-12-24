package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolReturn extends SymbolBase{

    private SymbolExpressioSimple expr;
    public SymbolReturn(SymbolExpressioSimple expr, codiTresAdreces){
        this.expr = expr;
        System.out.println("soy un SymbolReturn");
    }

    public SymbolExpressioSimple getExpr() {
        return expr;
    }
}