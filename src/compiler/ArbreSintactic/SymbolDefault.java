package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolDefault {
    private SymbolStatementList statementList;
    public SymbolDefault(SymbolStatementList state, codiTresAdreces codi){
        this.statementList=state;
        System.out.println("SOC UN SYMBOLDEFAULT");
    }

    public SymbolDefault(codiTresAdreces codi){
        System.out.println("SOC UN SYMBOLDEFAULT");
    }
}