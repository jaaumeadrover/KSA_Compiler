package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolLiteral  {
    private SymbolValor valor;

    public SymbolLiteral(SymbolValor v){
        this.valor=v;
    }

    public SymbolLiteral() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String codiTresAdreces(codiTresAdreces codi){
        if(this.valor!=null){
            return this.valor.codiTresAdreces(codi);
        }
        return null;
    }
}