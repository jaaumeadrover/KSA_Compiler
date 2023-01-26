package compiler.ArbreSintactic;
import compiler.GeneracioCodiIntermedi.*;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;

public class SymbolArrayInit {
    private TipusSub t;
    private int integer;

    public SymbolArrayInit(TipusSub t, String integer){
        this.t = t;
        this.integer = Integer.parseInt(integer);
    }

    public int getInteger() {
        return integer;
    }

    public TipusSub getT() {
        return t;
    }

    public String codiTresAdreces(codiTresAdreces codi){
        return null;
    }
}