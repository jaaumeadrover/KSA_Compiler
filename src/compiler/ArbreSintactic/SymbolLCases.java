package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolLCases extends SymbolBase{

    private SymbolCase cas;
    private SymbolLCases lcases;

    public SymbolLCases(SymbolCase c, SymbolLCases l){
        this.cas = c;
        this.lcases = l;
        System.out.println("soy un LCases");
    }
}