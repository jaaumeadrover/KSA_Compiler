package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;
import Operacions;

public class SymbolBoolOp extends SymbolBase{
    private Operacions bool;

    public SymbolBoolOp(Operacions b){
        this.bool = b;
        System.out.println("SOC UN SYMBOLOP");
    }
}