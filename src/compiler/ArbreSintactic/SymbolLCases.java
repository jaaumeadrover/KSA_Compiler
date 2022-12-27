package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolLCases {

    private SymbolCase cas;
    private SymbolLCases lcases;

    public SymbolLCases(){

    }

    public SymbolLCases(SymbolCase c, SymbolLCases l){
        this.cas = c;
        this.lcases = l;

    }

    public SymbolCase getCase() {
        return cas;
    }

    public SymbolLCases getLCases() {
        return lcases;
    }
}