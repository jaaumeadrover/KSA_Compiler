package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;

public class SymbolBoolOp extends SymbolBase{
    private operacions bool;
    private TipusSub tipus;
    public SymbolBoolOp(operacions b){
        this.bool = b;
        this.tipus = TipusSub.BOOLEAN;
        System.out.println("SOC UN SYMBOLOP");
    }
    public TipusSub getTipus(){
        return tipus;
    }
}