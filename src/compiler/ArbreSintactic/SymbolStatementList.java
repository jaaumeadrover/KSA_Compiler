package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolStatementList extends SymbolBase {

    private SymbolStatement stat;
    private SymbolStatementList stats;
    public SymbolStatementList(codiTresAdreces codi){

        System.out.println("soy un statementlist");
    }
    public SymbolStatementList(SymbolStatement stat,SymbolStatementList stats, codiTresAdreces codi){
        this.stat=stat;
        this.stats=stats;
        System.out.println("soy un statementlist");
    }
}