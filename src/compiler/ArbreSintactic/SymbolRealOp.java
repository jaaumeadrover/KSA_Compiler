package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolRealOp extends SymbolBase {
    private operacions realOp;
    private Tipus tipus;
    public SymbolRealOp(operacions op){
        this.realOp = op;
        this.tipus = Tipus.BOOLEAN;
        System.out.println("soy un simbol real op");
    }

    public Tipus getTipus() {
        return tipus;
    }
}