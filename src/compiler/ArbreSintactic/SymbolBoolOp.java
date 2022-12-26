package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolBoolOp {
    private Operacions bool;
    private TipusSub tipus;
    public SymbolBoolOp(Operacions b){
        this.bool = b;
        this.tipus = TipusSub.BOOLEAN;
        System.out.println("SOC UN SYMBOLOP");
    }
    public TipusSub getTipus(){
        return tipus;
    }
}