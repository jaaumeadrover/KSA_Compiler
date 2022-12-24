package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolAritOp extends SymbolBase{
    private OperacionsCTA aritOp;
    public SymbolAritOp(OperacionsCTA oper, codiTresAdreces codi){
        this.aritOp=oper;
        System.out.println("SOC UN SYMBOLARITOP");
    }
}