package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolArrayInit extends SymbolBase{

    private TipusSub t;
    private SymbolExpressioSimple expr;

    public SymbolArrayInit(TipusSub t, SymbolExpressioSimple expr){
        
        this.t = t;
        this.expr = expr;

    }
}