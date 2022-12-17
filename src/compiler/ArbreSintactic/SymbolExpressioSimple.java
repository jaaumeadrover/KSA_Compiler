package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolExpressioSimple extends SymbolBase{
    private SymbolExpressioSimple expressioSimple;
    private SymbolOperacio operacio;
    private SymbolValor valor;
    private String iden;
    private SymbolVarInit varinit;

    public SymbolExpressioSimple(SymbolExpressioSimple expr, SymbolOperacio oper){
        this.expressioSimple = expr;
        this.operacio = oper;
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }

    public SymbolExpressioSimple(SymbolValor val, SymbolOperacio oper){
        this.valor=val;
        this.operacio=oper;
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }

    public SymbolExpressioSimple(String id, SymbolVarInit varini){
        this.iden=id;
        this.varinit=varinit;
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }
}