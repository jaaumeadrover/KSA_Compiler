package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolBoolOp {

    private Operacions bool;
    private TipusSub tipus;

    public SymbolBoolOp(Operacions b){
        this.bool = b;
        this.tipus = TipusSub.BOOLEAN;
        //System.out.println("SOC UN SYMBOLOP");
    }
    public TipusSub getTipus(){
        return tipus;
    }

    public TipusInstruccionsCTA codiTresAdreces(codiTresAdreces codi){
        // generar codi 3 adreces amb els mètodes de la classe
        return codiTresAdreces.transforma(bool);
    }
}