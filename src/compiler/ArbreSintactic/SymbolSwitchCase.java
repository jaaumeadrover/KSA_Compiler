package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolSwitchCase extends SymbolBase {
    private SymbolExpressioSimple expressioSimple;
    private SymbolLCases lcases;

    public SymbolSwitchCase(SymbolExpressioSimple expr, SymbolLCases lcases){
        this.expressioSimple = expr;
        this.lcases = lcases;
        System.out.println("soy un switch");
    }

}