package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolContCap extends SymbolBase {
    private SymbolArgType arg;
    private Tipus t;
    private String id;

    public SymbolContCap(SymbolArgType arg, Tipus t,String id){
        this.arg=arg;
        this.t=t;
        this.id=id;
    }

    public SymbolContCap(SymbolContCap contcap,SymbolArgType arg, Tipus t,String id){
        this.arg=arg;
        this.t=t;
        this.id=id;
    }

    public Tipus getTipus() {
        return t;
    }

}