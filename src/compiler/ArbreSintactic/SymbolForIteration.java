package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolForIteration extends SymbolBase{

    private SymbolForInit init;
    private SymbolExpressioSimple expressio;
    private SymbolForPostExpression postexpression;
    private SymbolStatementList states;

    public SymbolForIteration(SymbolForInit forinit, SymbolExpressioSimple expr, SymbolForPostExpression forpost, SymbolStatementList states, codiTresAdreces codi){
        this.init = forinit;
        this.expressio = expr;
        this.postexpression = forpost;
        this.states=states;
        System.out.println("soy un forstatement");
    }
}