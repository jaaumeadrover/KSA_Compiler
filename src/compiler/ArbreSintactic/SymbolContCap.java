package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolContCap {
    private TipusSub t;
    private String id;

    public SymbolContCap(TipusSub t,String id){
        this.t=t;
        this.id=id;
    }

    public SymbolContCap(SymbolContCap contcap, TipusSub t,String id){
        this.t=t;
        this.id=id;
    }

    public TipusSub getTipusSub() {
        return t;
    }

    public void codiTresAdreces(codiTresAdreces codi){
        // Generar codi tres adreces amb els mètodes

    }

}