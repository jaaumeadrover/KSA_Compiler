package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolCase extends SymbolBase {

    private SymbolExpressioSimple expressioSimple;
    private SymbolStatementList statementList;

    public SymbolCase(SymbolExpressioSimple expr, SymbolStatementList state){
        this.expressioSimple=expr;
        this.statementList=state;
        System.out.println("SOC UN SYMBOLCASE");
    }
}