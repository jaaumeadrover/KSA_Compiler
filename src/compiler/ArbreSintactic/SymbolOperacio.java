package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolOperacio extends SymbolBase{

    private SymbolOp operador;
    private SymbolExpressioSimple expressioSimple;
    private SymbolVarInit simbol;
    
    public SymbolOperacio(SymbolVarInit simbol){
        this.simbol = simbol;
        System.out.println("soy un oper");
    }
    
    public SymbolOperacio(SymbolOp op,SymbolExpressioSimple expr){
        this.operador=op;
        this.expressioSimple=expr;
        System.out.println("soy un oper");
    }
}