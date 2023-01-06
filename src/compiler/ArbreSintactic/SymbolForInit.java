package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolForInit {

    private SymbolVarDecl declaracioVar;

    public SymbolForInit(SymbolVarDecl vardecl){
        this.declaracioVar=vardecl;

    }

    public SymbolForInit(){
    }

    public SymbolVarDecl getVarDecl(){
        return this.declaracioVar;
    }

    public void codiTresAdreces(codiTresAdreces codi){
        this.declaracioVar.codiTresAdreces(codi);
    }
}