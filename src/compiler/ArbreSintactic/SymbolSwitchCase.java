package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSwitchCase {
    private SymbolExpressioSimple expressioSimple;
    private SymbolLCases lcases;

    public SymbolSwitchCase(SymbolExpressioSimple expr, SymbolLCases lcases, codiTresAdreces codi){
        this.expressioSimple = expr;
        this.lcases = lcases;
        System.out.println("soy un switch");
    }

}