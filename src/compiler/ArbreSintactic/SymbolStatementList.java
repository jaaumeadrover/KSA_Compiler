package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolStatementList  {

    private SymbolStatement stat;
    private SymbolStatementList stats;

    public SymbolStatementList(){

    }

    public SymbolStatementList(SymbolStatement stat,SymbolStatementList stats){
        this.stat=stat;
        this.stats=stats;

    }

    public void codiTresAdreces(codiTresAdreces codi){
        if (stat != null){
            this.stat.codiTresAdreces(codi);
        }
        //mentre quedin instruccions
        if (stats != null){
            this.stats.codiTresAdreces(codi);
        }
    }
}