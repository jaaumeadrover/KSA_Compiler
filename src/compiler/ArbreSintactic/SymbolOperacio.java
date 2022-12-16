package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolOperacio extends SymbolBase{

    private SymbolOp operador;
    private SymbolExpressioSimple expressioSimple;
    private SymbolVarInit varInit;

    public SymbolOperacio(SymbolOp op,SymbolExpressioSimple expr){
        this.operador=op;
        this.expressioSimple=expr;
        System.out.println("soy un oper");
    }

    public SymbolOperacio(SymbolVarInit ini){
        this.varInit=ini;
        System.out.println("soy un oper");
    }
}