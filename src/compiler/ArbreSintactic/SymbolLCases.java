package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolLCases extends SymbolBase{

    private SymbolCase cas;
    private SymbolLCases lcases;

    public SymbolLCases(codiTresAdreces){
        System.out.println("soy un LCases");
    }

    public SymbolLCases(SymbolCase c, SymbolLCases l, codiTresAdreces){
        this.cas = c;
        this.lcases = l;
        System.out.println("soy un LCases");
    }
}