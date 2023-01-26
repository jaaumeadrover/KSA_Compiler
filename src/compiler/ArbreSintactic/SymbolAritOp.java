package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolAritOp {
    private Operacions aritOp;
    public SymbolAritOp(Operacions oper){
        this.aritOp=oper;
    }

    public TipusInstruccionsCTA codiTresAdreces(codiTresAdreces codi){
        return codiTresAdreces.transforma(aritOp);
    }
}