package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolRealOp extends SymbolBase {
    private operacions realOp;
    private TipusSub tipus;
    public SymbolRealOp(operacions op, codiTresAdreces codi){
        this.realOp = op;
        this.tipus = TipusSub.BOOLEAN;
        System.out.println("soy un simbol real op");
    }

    public TipusSub getTipus() {
        return tipus;
    }
}