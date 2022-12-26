package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolContCap {
    private SymbolArgType arg;
    private TipusSub t;
    private String id;

    public SymbolContCap(SymbolArgType arg, TipusSub t,String id){
        this.arg=arg;
        this.t=t;
        this.id=id;
    }

    public SymbolContCap(SymbolContCap contcap,SymbolArgType arg, TipusSub t,String id){
        this.arg=arg;
        this.t=t;
        this.id=id;
    }

    public TipusSub getTipusSub() {
        return t;
    }

}