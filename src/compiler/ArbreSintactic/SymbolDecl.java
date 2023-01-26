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
    }

    public void codiTresAdreces(codiTresAdreces codi){
        //si és variable
        if (varDecl != null){
            varDecl.codiTresAdreces(codi);
        //si és funció
        }else if (funcDecl != null){
            funcDecl.codiTresAdreces(codi);
        //si és procediment
        } else if (procDecl != null) {
            procDecl.codiTresAdreces(codi);
        }
    }
}