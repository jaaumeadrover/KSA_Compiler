package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolDefault extends SymbolBase{
    private SymbolStatementList statementList;
    public SymbolDefault(SymbolStatementList state){
        this.statementList=state;
        System.out.println("SOC UN SYMBOLDEFAULT");
    }

    public SymbolDefault(){
        System.out.println("SOC UN SYMBOLDEFAULT");
    }
}