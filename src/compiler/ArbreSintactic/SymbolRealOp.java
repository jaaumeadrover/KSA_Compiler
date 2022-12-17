package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolRealOp extends SymbolBase {
    private operacions realOp;

    public SymbolRealOp(operacions op){
        this.realOp = op;
        System.out.println("soy un simbol real op");
    }
}