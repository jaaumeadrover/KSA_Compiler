package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolReturn extends SymbolBase{

    private SymbolExpressioSimple expr;
    public SymbolReturn(SymbolExpressioSimple expr, codiTresAdreces codi){
        this.expr = expr;
        System.out.println("soy un SymbolReturn");
    }

    public SymbolExpressioSimple getExpr() {
        return expr;
    }
}