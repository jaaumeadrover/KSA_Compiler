package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolDecl  {

    public SymbolDecl(codiTresAdreces codi){
        System.out.println("SOC UN SYMBOLDECL");
    }

    public void codiTresAdreces(codiTresAdreces codi){
        this.declaracio.codiTresAdreces();
        if (llistaDecl != null){
            this.llistaDecl.codiTresAdreces();
        }
    }
}