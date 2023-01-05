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

    //
    public void codiTresAdreces(codiTresAdreces codi, String r0, String ef){
        if(cas!=null){
            cas.codiTresAdreces(codi, r0, ef);
        }

        if(lcases!=null){
            lcases.codiTresAdreces(codi,r0,ef);
        }
    }
}