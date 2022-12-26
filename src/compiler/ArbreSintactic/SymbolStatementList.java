package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolStatementList  {

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

    public void codiTresAdreces(codiTresAdreces codi){
        this.stat.codiTresAdreces();
        if (stats != null){
            this.stats.codiTresAdreces();
        }
    }
}