package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolDefault {
    private SymbolStatementList statementList;
    private boolean esBuit;
    public SymbolDefault(SymbolStatementList state){
        this.statementList=state;
        this.esBuit=false;
    }

    public SymbolDefault(){
        this.esBuit=true;
    }
    public boolean esBuit(){
        return this.esBuit;
    }
    public void codiTresAdreces(codiTresAdreces codi){
        this.statementList.codiTresAdreces(codi);
    }

}