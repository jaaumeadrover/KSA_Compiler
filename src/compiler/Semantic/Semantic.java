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
    Tenim 3 casos diferents:
    
    1. El tipusSub de la expr és NULL: la expressió conté errors
    2. El tipusSub de la expr és diferent a t: s'esperava un valor int per exemple i es retorna boolean.
    3.El tipusSub de la expr
     */
    public boolean gestExpr(SymbolExpressioSimple expr, TipusSub t){
        
        TipusSub tipoExpr = expr.tsOperador();
        
        if(expr.getTipusSubResultat()==t){
            System.out.println("CORRECTE");
            return true;
        }else{
            System.out.println("INCORRECTE");
            return false;
        }
    }
    
    public boolean gestFunc(TipusSub tfunc, SymbolReturn exprRtn){
        SymbolExpressioSimple expresioReturn = exprRtn.getExpr();
        expresioReturn.setTsResultat();
        TipusSub tipoExpr = expresioReturn.getTipusSubResultat();

        if(tipoExpr==tfunc){
            System.out.println("CORRECTE");
            return true;
        }else{
            System.out.println("INCORRECTE");
            return false;
        }
    }
    public boolean gestAsigDecl(String iden, SymbolVarInit varinit){
        Simbol id = ts.consulta(iden);
        TipusSub tipusSub = id.getTipusSub();
        Tipus tipus = id.getTipus()
        if(varinit.esBuit()){
            //se declara pero no se inicialitza
            if(tipus==Tipus.CONST){
                //Es una constant
                //ERROR
            }else{
                //Es una variable

            }
        }else{

            // Es declara i s'inicialitza
            if(varinit.isIsarray()){
                //S'inicia un array
            }else{
                SymbolExpressioSimple expr = varinit.getExpr()
                //S'inicia una variable o constant
                if(tipus==Tipus.CONST){
                    //Es una constant
                }else{
                    //Es una variable

                }

            }
        }
    }

}