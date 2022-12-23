package compiler.Semantic;

//importar taula de simbols i arbre sintactic

import compiler.ArbreSintactic.*;
import compiler.Symbols.TaulaSimbols.*;



public class Semantic {

    private TaulaSimbols ts;

    public Semantic (TaulaSimbols ts){
        this.ts=ts;
    }

    /*
    Funció per a gestionar les expressions simples.
     */
    public boolean gestExpr(SymbolExpressioSimple expr, TipusSub t){
        
        TipusSub tipoExpr = expr.tsOperand();
        
        if(expr.getTipusSubResultat()==t){
            System.out.println("CORRECTE");
            return true;
        }else{
            System.out.println("INCORRECTE");
            return false;
        }
    }


}