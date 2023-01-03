package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolLiteral  {
    private SymbolValor valor;
    private String txt;

    public SymbolLiteral(SymbolValor v){
        this.valor=v;
    }
    public SymbolLiteral(String str){
        this.txt=str;
    }

    public String toString(){
        if(valor!=null){
            return valor.toString();
        }
        return txt;
    }
    public String codiTresAdreces(codiTresAdreces codi){
        if(this.valor!=null){
            return this.valor.codiTresAdreces(codi);
        }
        return null;
    }
}