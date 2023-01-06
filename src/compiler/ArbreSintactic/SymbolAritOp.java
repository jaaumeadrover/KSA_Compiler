package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolAritOp {
    private Operacions aritOp;
    public SymbolAritOp(Operacions oper){
        this.aritOp=oper;
    }

    public TipusInstruccionsCTA codiTresAdreces(codiTresAdreces codi){
        // generar codi 3 adreces amb els mètodes de la classe
        return codiTresAdreces.transforma(aritOp);
    }
}