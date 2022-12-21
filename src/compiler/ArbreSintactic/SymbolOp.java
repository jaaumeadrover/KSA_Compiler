package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.TipusSub;

public class SymbolOp extends SymbolBase{
    private SymbolBoolOp boolOP;
    private SymbolAritOp aritOp;
    private SymbolRealOp realOP;
    public TipusSub tipus;

    public SymbolOp(SymbolBoolOp oper){
        this.boolOP=oper;
        System.out.println("soy un operador");
        this.tipus = TipusSub.BOOLEAN;
    }

    public SymbolOp(SymbolAritOp oper){
        this.aritOp=oper;
        System.out.println("soy un operador");
        this.tipus = TipusSub.INT;
    }

    public SymbolOp(SymbolRealOp oper){
        this.realOP=oper;
        System.out.println("soy un operador");
        this.tipus = TipusSub.BOOLEAN;
    }
    public TipusSub getTipusSub(){
        return tipus;
    }

}