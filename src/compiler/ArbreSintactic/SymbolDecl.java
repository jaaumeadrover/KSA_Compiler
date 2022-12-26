package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolDecl  {
    private SymbolVarDecl varDecl;
    private SymbolFuncDecl funcDecl;
    private SymbolProcDecl procDecl;

    public SymbolDecl(SymbolVarDecl varDecl, SymbolFuncDecl funcDecl, SymbolProcDecl procDecl){
        this.varDecl = varDecl;
        this.funcDecl = funcDecl;
        this.procDecl = procDecl;
        System.out.println("SOC UN SYMBOLDECL");
    }

//    public void codiTresAdreces(codiTresAdreces codi){
//        if (varDecl != null){
//            varDecl.codiTresAdreces(codi);
//        }else if (funcDecl != null){
//            funcDecl.codiTresAdreces(codi);
//        } else if (procDecl != null) {
//            proDecl.codiTresAdreces(codi);
//        }
//    }
}