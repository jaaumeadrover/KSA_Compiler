package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSwitchCase {
    private SymbolExpressioSimple expressioSimple;
    private SymbolLCases lcases;
    private SymbolDefault defa;

    public SymbolSwitchCase(SymbolExpressioSimple expr, SymbolLCases lcases, SymbolDefault defa){
        this.expressioSimple = expr;
        this.lcases = lcases;
        this.defa=defa;
    }

}