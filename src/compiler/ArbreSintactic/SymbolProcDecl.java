package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolProcDecl extends SymbolBase {

    private String iden;
    private SymbolStatementList stat;

    private SymbolFuncCap funcCap;
    public SymbolProcDecl(String iden, SymbolStatementList stat,
                          SymbolFuncCap funcCap, codiTresAdreces){
        this.iden = iden;
        this.stat = stat;
        this.funcCap = funcCap;
        System.out.println("soy un funcDecl");
    }
}