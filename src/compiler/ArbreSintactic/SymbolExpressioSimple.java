package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;

public class SymbolExpressioSimple extends SymbolBase{
    private SymbolExpressioSimple expressioSimple;
    private SymbolOperacio operacio;
    private SymbolValor valor;
    private String iden;
    private SymbolVarInit varinit;
    private TipusSub tipussub;

    public SymbolExpressioSimple(SymbolExpressioSimple expr, SymbolOperacio oper){
        this.expressioSimple = expr;
        this.operacio = oper;
        this.tipussub = operacio.getTipusSub();
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }

    public SymbolExpressioSimple(SymbolValor val, SymbolOperacio oper){
        this.valor=val;
        this.operacio=oper;
        this.tipussub=operacio.getTipusSub();
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }

    public SymbolExpressioSimple(String id, SymbolVarInit varini){
        this.iden=id;
        this.varinit=varinit;
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }
    public TipusSub getTipusSub(){
        return tipussub;
    }
    public SymbolExpressioSimple getExpr(){
        return expressioSimple;
    }
    
    
}