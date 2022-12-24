package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolSwitchCase extends SymbolBase {
    private SymbolExpressioSimple expressioSimple;
    private SymbolLCases lcases;

    public SymbolSwitchCase(SymbolExpressioSimple expr, SymbolLCases lcases, codiTresAdreces codi){
        this.expressioSimple = expr;
        this.lcases = lcases;
        System.out.println("soy un switch");
    }

}