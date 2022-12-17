package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolBoolOp extends SymbolBase{
    private operacions bool;

    public SymbolBoolOp(operacions b){
        this.bool = b;
        System.out.println("SOC UN SYMBOLOP");
    }
}