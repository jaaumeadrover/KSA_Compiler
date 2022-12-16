package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;
import Operacions;

public class SymbolRealOp extends SymbolBase {
    private Operacions realOp;

    public SymbolRealOp(Operacions op){
        this.RealOp = op;
        System.out.println("soy un simbol real op");
    }
}