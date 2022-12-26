package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolWhileStatement {
    private SymbolExpressioSimple expressio;
    private SymbolStatementList statementList;

   public SymbolWhileStatement(SymbolExpressioSimple expr, SymbolStatementList state){
        this.expressio = expr;
        this.statementList = state;

   }
    public SymbolWhileStatement(){

    }

 }