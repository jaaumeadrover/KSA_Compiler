package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolLiteral  {
    private String txt;
    private boolean esVar;
    public SymbolLiteral(String str){
        this.txt=str;
        esVar=false;
    }

    public SymbolLiteral(String str,boolean b){
        this.txt=str;
        esVar=true;
    }
    public String toString(){
        return txt;
    }
    public String codiTresAdreces(codiTresAdreces codi){
        /*
        if(this.valor!=null){
            return this.valor.codiTresAdreces(codi, false);
        }
        return null;
        */
        return txt;
    }
}