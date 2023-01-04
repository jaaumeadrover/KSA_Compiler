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
        //System.out.println("SOC UN SYMBOLDECL");
    }

    public void codiTresAdreces(codiTresAdreces codi){
        System.out.println("        HOLA DECL");
        //si és variable
        if (varDecl != null){
            System.out.println("        DECL DE VAR");
            varDecl.codiTresAdreces(codi);
        //si és funció
        }else if (funcDecl != null){
            System.out.println("        DECL DE FUNCIÓ");
            funcDecl.codiTresAdreces(codi);
        //si és procediment
        } else if (procDecl != null) {
            System.out.println("        DECL DE PROC");
            procDecl.codiTresAdreces(codi);
        }
    }
}