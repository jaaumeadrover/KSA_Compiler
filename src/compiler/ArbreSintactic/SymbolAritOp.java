package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolAritOp extends SymbolBase{
    private operacions aritOp;
    public SymbolAritOp(operacions oper, codiTresAdreces codi){
        this.aritOp=oper;
        System.out.println("SOC UN SYMBOLARITOP");
    }
}