package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolArrayInit extends SymbolBase{

    private TipusSub t;
    private SymbolExpressioSimple expr;

    public SymbolArrayInit(TipusSub t, SymbolExpressioSimple expr, codiTresAdreces codi){
        
        this.t = t;
        this.expr = expr;

    }
}