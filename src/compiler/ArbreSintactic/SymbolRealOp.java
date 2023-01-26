package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolRealOp  {
    private Operacions realOp;
    private TipusSub tipus;

    public SymbolRealOp(Operacions op){
        this.realOp = op;
        this.tipus = TipusSub.BOOLEAN;
    }

    public TipusSub getTipus() {
        return tipus;
    }

    public TipusInstruccionsCTA codiTresAdreces(codiTresAdreces codi){
        return codiTresAdreces.transforma(realOp);
    }
}