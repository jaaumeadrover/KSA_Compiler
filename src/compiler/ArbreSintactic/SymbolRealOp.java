package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolRealOp  {
    private Operacions realOp;
    private TipusSub tipus;
    public SymbolRealOp(Operacions op, codiTresAdreces codi){
        this.realOp = op;
        this.tipus = TipusSub.BOOLEAN;
        System.out.println("soy un simbol real op");
    }

    public TipusSub getTipus() {
        return tipus;
    }
}