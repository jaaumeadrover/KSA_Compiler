package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolProcDecl  {

    private String iden;
    private SymbolStatementList stat;

    private SymbolFuncCap funcCap;
    public SymbolProcDecl(String iden, SymbolStatementList stat,
                          SymbolFuncCap funcCap){
        this.iden = iden;
        this.stat = stat;
        this.funcCap = funcCap;
    }
}