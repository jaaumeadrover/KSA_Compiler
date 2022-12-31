package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolAritOp {
    private Operacions aritOp;
    public SymbolAritOp(Operacions oper){
        this.aritOp=oper;
        //System.out.println("SOC UN SYMBOLARITOP");
    }

    public TipusInstruccioCTA codiTresAdreces(codi3A codi){
        // generar codi 3 adreces amb els mètodes de la classe
        return codi3A.transforma(bool);
    }
}