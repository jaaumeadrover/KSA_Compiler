package compiler.ArbreSintactic;
import compiler.GeneracioCodiIntermedi.*;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;

public class SymbolArrayInit extends SymbolBase{

    private TipusSub t;
    private SymbolExpressioSimple expr;

    public SymbolArrayInit(TipusSub t, SymbolExpressioSimple expr, codiTresAdreces codi){
        
        this.t = t;
        this.expr = expr;

    }
}