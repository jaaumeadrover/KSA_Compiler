package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;

public class SymbolExpressioSimple extends SymbolBase{
    private SymbolExpressioSimple expressioSimple;
    private SymbolOperacio operacio;
    private SymbolValor valor;
    private String iden;
    private SymbolVarInit varinit;
    private TipusSub tsOperand;
    private TipusSub tsResultat;
    
    /*
    Constructor en el qual hi ha més d'una expressió
    */
    public SymbolExpressioSimple(SymbolExpressioSimple expr, SymbolOperacio oper){
        this.expressioSimple = expr;
        this.operacio = oper;
        this.tsOperand = operacio.getTipusSub();
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }
    
    /*
    Constructor expressio Simple
    */
    public SymbolExpressioSimple(SymbolValor val, SymbolOperacio oper){
        this.expressioSimple = null;
        this.valor=val;
        this.operacio=oper;
        this.tsOperand=operacio.getTipusSub();
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
        //if oper is null
        
        //else 
        //tsResultat=getTsResultat();
    }

    public SymbolExpressioSimple(String id, SymbolVarInit varini){
        this.iden=id;
        this.varinit=varinit;
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }
    public TipusSub tsOperand(){
        return tsOperand;
    }
    public SymbolExpressioSimple getExpr(){
        return expressioSimple;
    }
    
    /*
    tsResultat:
    NULL: si incorrecto
    INT:
    BOOLEAN: correcte per al whileStatement
    STRING: 
    */
    private void setTsResultat(){
        TipusSub x=null;
        
        //Cas 1: expressió sense expressions anteriors
        if(this.expressioSimple==null){
            if(this.valor.getValorInt())
        }else{
            
        }
        
        tsResultat=x;
    }
    
}