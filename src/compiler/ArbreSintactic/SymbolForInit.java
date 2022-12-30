package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolForInit {

    private SymbolVarDecl declaracioVar;

    public SymbolForInit(SymbolVarDecl vardecl){
        this.declaracioVar=vardecl;
        //System.out.println("SOC UN SYMBOL SYMBOLFORINIT");

    }

    public SymbolForInit(){
        //System.out.println("SOC UN SYMBOL SYMBOLFORINIT");
    }

    public SymbolVarDecl getVarDecl(){
        return this.declaracioVar;
    }

    public void codiTresAdreces(codi3A codi){
        this.declaracioVar.codiTresAdreces(codi);
    }
}