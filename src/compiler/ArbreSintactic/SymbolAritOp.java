package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;
import Operacions;

public class SymbolAritOp extends SymbolBase{
    private Operacions aritOp;
    public SymbolAritOp(Operacions oper){
        this.aritOp=oper;
        System.out.println("SOC UN SYMBOLARITOP");
    }
}