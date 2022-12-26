package compiler.ArbreSintactic;
import compiler.GeneracioCodiIntermedi.*;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;

public class SymbolArrayInit {

    private TipusSub t;
    private SymbolExpressioSimple expr;

    public SymbolArrayInit(TipusSub t, SymbolExpressioSimple expr){
        
        this.t = t;
        this.expr = expr;

    }
}