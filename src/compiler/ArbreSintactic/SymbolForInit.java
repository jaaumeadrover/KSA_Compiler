package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolForInit extends SymbolBase{

    private SymbolVarDecl declaracioVar;

    public SymbolForInit(SymbolVarDecl vardecl, codiTresAdreces codi){
        this.declaracioVar=vardecl;
        System.out.println("SOC UN SYMBOL SYMBOLFORINIT");

    }

    public SymbolForInit(codiTresAdreces codi){
        System.out.println("SOC UN SYMBOL SYMBOLFORINIT");
    }
}