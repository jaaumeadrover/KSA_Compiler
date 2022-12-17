package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolStatementList extends SymbolBase {

    private SymbolStatement stat;
    private SymbolStatementList stats;
    public SymbolStatementList(){

        System.out.println("soy un statementlist");
    }
    public SymbolStatementList(SymbolStatement stat,SymbolStatementList stats){
        this.stat=stat;
        this.stats=stats;
        System.out.println("soy un statementlist");
    }
}