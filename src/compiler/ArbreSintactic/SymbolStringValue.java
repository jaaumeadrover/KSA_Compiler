package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolStringValue {
    private SymbolStringValue string;
    private SymbolLiteral literal;

    public SymbolStringValue(SymbolStringValue s, SymbolLiteral l){
        this.string = s;
        this.literal=l;
    }

    public SymbolStringValue() {

    }

    public String codiTresAdreces(codiTresAdreces codi) {
        String s=literal.codiTresAdreces(codi);
        if(this.string!=null){
            s+=this.string,codiTresAdreces(codi);
        }
        return s;
    }
}