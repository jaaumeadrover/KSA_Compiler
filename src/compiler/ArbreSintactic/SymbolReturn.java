package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolReturn {

    private SymbolExpressioSimple expr;
    public SymbolReturn(SymbolExpressioSimple expr){
        this.expr = expr;
    }

    public SymbolExpressioSimple getExpr() {
        return expr;
    }
}