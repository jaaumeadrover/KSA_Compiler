package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolForIteration extends SymbolBase{

    private SymbolForInit init;
    private SymbolExpressioSimple expressio;
    private SymbolForPostExpression postexpression;
    private SymbolStatementList statementlist;

    public SymbolForIteration(SymbolForInit forinit, SymbolExpressioSimple expr, SymbolForPost forpost){
        this.init = forinit;
        this.expressio = expr
        this.postexpression = forpost;
        System.out.println("soy un forstatement");
    }
}