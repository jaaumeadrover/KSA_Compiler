package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolIfStatement {

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
      public SymbolIfStatement( codiTresAdreces codi){
        
    }
}