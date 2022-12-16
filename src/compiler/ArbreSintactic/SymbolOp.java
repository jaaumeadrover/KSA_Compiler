package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;
import Operacions;

public class SymbolOp extends SymbolBase{
    private SymbolBoolOp boolOP;
    private SymbolAritOp aritOp;
    private SymbolRealOp realOP;

    public SymbolOp(SymbolBoolOp oper){
        this.boolOp=oper;
        System.out.println("soy un operador");
    }

    public SymbolOp(SymbolAritOp oper){
        this.aritOp=oper;
        System.out.println("soy un operador");
    }

    public SymbolOp(SymbolRealOp oper){
        this.realOP=oper;
        System.out.println("soy un operador");
    }

}