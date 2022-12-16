package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolWhileStatement extends SymbolBase {
    private SysmbolExpressioSimple expressio;
    private SymbolStatementList statementList;

   public SymbolWhileStatement(SysmbolExpressioSimple expr, SymbolStatementList state){
        this.expressio = expr;
        this.statementList = state;
        System.out.println("Esto es un while");
   }

 }