package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolBoolOp extends SymbolBase{
    private OperacionsCTA bool;
    private TipusSub tipus;
    public SymbolBoolOp(operacions b, codiTresAdreces codi){
        this.bool = b;
        this.tipus = TipusSub.BOOLEAN;
        System.out.println("SOC UN SYMBOLOP");
    }
    public TipusSub getTipus(){
        return tipus;
    }
}