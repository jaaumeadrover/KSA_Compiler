package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;

public class SymbolOperacio extends SymbolBase{

    private SymbolOp operador;
    private SymbolExpressioSimple expressioSimple;
    private SymbolVarInit simbol;
    private TipusSub tipus;
    
    public SymbolOperacio(SymbolVarInit simbol){
        this.simbol = simbol;
        System.out.println("soy un oper");
    }
    
    public SymbolOperacio(SymbolOp op,SymbolExpressioSimple expr){
        this.operador=op;
        this.expressioSimple=expr;
        this.tipus=operador.getTipusSub();
        System.out.println("soy un oper");
    }
    public TipusSub getTipusSub(){
        return tipus;
    }

   
}