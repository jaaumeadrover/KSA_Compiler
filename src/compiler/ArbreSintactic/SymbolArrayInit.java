package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolArrayInit extends SymbolBase{

    private Tipus t;
    private SymbolExpressioSimple expr
    public SymbolArrayInit(Tipus t, SymbolExpressioSimple expr){
        
        this.t = t;
        this.expr = expr;

    }
}