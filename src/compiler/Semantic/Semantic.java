package compiler.Semantic;

//importar taula de simbols i arbre sintactic

import compiler.ArbreSintactic.*;
import compiler.Symbols.TaulaSimbols.*;



public class Semantic {

    private TaulaSimbols ts;

    public Semantic (TaulaSimbols ts){
        this.ts=ts;
    }
    
    public boolean gestExpr(SymbolExpressioSimple expr, TipusSub t){
        
        TipusSub tipoExpr = expr.tsOperand();
        
        if(tipoExpr.equals(t)){
           if(expr.getExpr()!=null){
               gestExpr(expr.getExpr(),t);
           } 
           else{
               return true;
           }
        }else{
            return false;
        }
        return false;
    }


}