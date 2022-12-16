package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolForInit extends SymbolBase{

    private SymbolVarDecl declaracioVar;

    public SymbolForInit(SymbolVarDecl vardecl){
        this.declaracioVar=vardecl;
        System.out.println("SOC UN SYMBOL SYMBOLFORINIT");

    }

    public SymbolForInit(){
        System.out.println("SOC UN SYMBOL SYMBOLFORINIT");
    }
}