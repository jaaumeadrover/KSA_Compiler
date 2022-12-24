package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolWhileStatement {
    private SymbolExpressioSimple expressio;
    private SymbolStatementList statementList;

   public SymbolWhileStatement(SymbolExpressioSimple expr, SymbolStatementList state, codiTresAdreces codi){
        this.expressio = expr;
        this.statementList = state;
        System.out.println("Esto es un while");
   }
    public SymbolWhileStatement(){

    }

 }