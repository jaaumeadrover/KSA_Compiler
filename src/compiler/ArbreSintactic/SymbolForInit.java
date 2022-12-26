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
}