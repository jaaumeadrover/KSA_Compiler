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

    public TipusInstruccioCTA codiTresAdreces(codi3A codi){
        // generar codi 3 adreces amb els mètodes de la classe
        return codi3A.transforma(realOp);
    }
}