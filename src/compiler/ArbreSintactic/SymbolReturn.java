package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolReturn {

    private SymbolExpressioSimple expr;
    public SymbolReturn(SymbolExpressioSimple expr){
        this.expr = expr;
        System.out.println("soy un SymbolReturn");
    }

    public SymbolExpressioSimple getExpr() {
        return expr;
    }
}