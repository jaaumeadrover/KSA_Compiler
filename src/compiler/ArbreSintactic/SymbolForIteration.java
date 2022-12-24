package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolForIteration {

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
    public SymbolForIteration(){
        
    }
}