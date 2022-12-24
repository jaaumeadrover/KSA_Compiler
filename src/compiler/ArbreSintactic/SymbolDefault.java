package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolDefault extends SymbolBase{
    private SymbolStatementList statementList;
    public SymbolDefault(SymbolStatementList state, codiTresAdreces codi){
        this.statementList=state;
        System.out.println("SOC UN SYMBOLDEFAULT");
    }

    public SymbolDefault(codiTresAdreces codi){
        System.out.println("SOC UN SYMBOLDEFAULT");
    }
}