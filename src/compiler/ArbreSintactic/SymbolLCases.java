package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolLCases {

    private SymbolCase cas;
    private SymbolLCases lcases;

    public SymbolLCases(codiTresAdreces codi){
        System.out.println("soy un LCases");
    }

    public SymbolLCases(SymbolCase c, SymbolLCases l, codiTresAdreces codi){
        this.cas = c;
        this.lcases = l;
        System.out.println("soy un LCases");
    }
}