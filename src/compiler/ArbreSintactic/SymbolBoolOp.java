package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolBoolOp {

    private Operacions bool;
    private TipusSub tipus;

    public SymbolBoolOp(Operacions b){
        this.bool = b;
        this.tipus = TipusSub.BOOLEAN;
    }
    public TipusSub getTipus(){
        return tipus;
    }

    public TipusInstruccionsCTA codiTresAdreces(codiTresAdreces codi){
        return codiTresAdreces.transforma(bool);
    }
}