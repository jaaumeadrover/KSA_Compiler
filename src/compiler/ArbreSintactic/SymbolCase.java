package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.GeneracioCodiIntermedi.*;
import compiler.Symbols.TaulaSimbols.TipusSub;

public class SymbolCase  {

    private SymbolExpressioSimple expressioSimple;
    private SymbolStatementList statementList;
    private TipusSub tipusSub;

    public SymbolCase(SymbolExpressioSimple expr, SymbolStatementList state){
        this.expressioSimple=expr;
        this.statementList=state;

        //System.out.println("SOC UN SYMBOLCASE");
    }

    public SymbolExpressioSimple getExpr() {
        return expressioSimple;
    }
}