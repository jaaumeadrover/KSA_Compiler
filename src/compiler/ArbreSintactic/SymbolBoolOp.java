package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolBoolOp extends SymbolBase{
    private operacions bool;
    private Tipus tipus;
    public SymbolBoolOp(operacions b){
        this.bool = b;
        this.tipus = Tipus.BOOLEAN;
        System.out.println("SOC UN SYMBOLOP");
    }
    public Tipus getTipus(){
        return tipus;
    }
}