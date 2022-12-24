package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolStatementList extends SymbolBase {

    private SymbolStatement stat;
    private SymbolStatementList stats;
    public SymbolStatementList(codiTresAdreces){

        System.out.println("soy un statementlist");
    }
    public SymbolStatementList(SymbolStatement stat,SymbolStatementList stats, codiTresAdreces){
        this.stat=stat;
        this.stats=stats;
        System.out.println("soy un statementlist");
    }
}