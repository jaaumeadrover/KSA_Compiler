package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolIfStatement extends SymbolBase{

    private SymbolExpressioSimple expr;
    private SymbolStatementList statesIf;
    private SymbolStatementList statesElse;

    public SymbolIfStatement(SymbolExpressioSimple expr,SymbolStatementList statesIf, codiTresAdreces codi){
        this.expr=expr;
        this.statesIf=statesIf;
    }
    public SymbolIfStatement(SymbolExpressioSimple expr,SymbolStatementList statesIf,SymbolStatementList statesElse, codiTresAdreces codi){
        this.expr=expr;
        this.statesIf=statesIf;
        this.statesElse=statesElse;
    }
}