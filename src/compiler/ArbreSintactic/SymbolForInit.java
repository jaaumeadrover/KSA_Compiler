package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolForInit {

    private SymbolVarDecl declaracioVar;

    public SymbolForInit(SymbolVarDecl vardecl, codiTresAdreces codi){
        this.declaracioVar=vardecl;
        System.out.println("SOC UN SYMBOL SYMBOLFORINIT");

    }

    public SymbolForInit(codiTresAdreces codi){
        System.out.println("SOC UN SYMBOL SYMBOLFORINIT");
    }
}