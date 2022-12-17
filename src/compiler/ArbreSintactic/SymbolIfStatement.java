package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolIfStatement extends SymbolBase{

    private SymbolExpressioSimple expr;
    private SymbolStatementList statesIf;
    private SymbolStatementList statesElse;

    public SymbolIfStatement(SymbolExpressioSimple expr,SymbolStatementList statesIf){
        this.expr=expr;
        this.statesIf=statesIf;
    }
    public SymbolIfStatement(SymbolExpressioSimple expr,SymbolStatementList statesIf,SymbolStatementList statesElse){
        this.expr=expr;
        this.statesIf=statesIf;
        this.statesElse=statesElse;
    }
}