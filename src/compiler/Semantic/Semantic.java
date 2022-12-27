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
        System.out.println("TIPUS EXPRESSIO: "+expr.getTipusSubResultat());
        
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
        //expresioReturn.setTsResultat();
        TipusSub tipoExpr = expresioReturn.getTipusSubResultat();
        System.out.println("TIPUS FUNCIÓ: "+tfunc+", TIPUS EXPR: "+tipoExpr);

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
        Tipus tipus = id.getTipus();
        
        if(varinit.esBuit()){
            //se declara pero no se inicialitza
            if(tipus==Tipus.CONST){
                //Es una constant
                //ERROR
                System.out.println("La constant s'ha d'assignar quan es declara");
                return false;
            }else{
                //Es una variable
                return true;
            }
            
        }else{
            // Es declara i s'inicialitza
            //Es array
            if(varinit.isIsarray()){
                //S'inicia un array
                SymbolArrayInit arrayInit = varinit.getArray();
                SymbolExpressioSimple exprArray = arrayInit.getExpr();
                TipusSub arraytsub = arrayInit.getT();
                //exprArray.setTsResultat();
                TipusSub exprTsub = exprArray.getTipusSubResultat();
                if(!(tipus.equals(Tipus.ARRAY))){
                    return false;
                }
                //expressió incorrecta
                if(exprTsub==null){
                    return false;
                }else{
                    //tamany array correcte? [i]
                    if(exprTsub.equals(TipusSub.INT)){
                        if(arraytsub.equals(tipusSub)){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }
            //No es array
            }else{
                SymbolExpressioSimple expr = varinit.getExpr();
                //expr.setTsResultat();
                //Comprobar si es null
                TipusSub exprTsub = expr.getTipusSubResultat();
                if(exprTsub==null){
                    System.out.println("Expressió incorrecte a la declaració");
                    return false;
                }
                //S'inicia una variable o constant
                if(exprTsub.equals(tipusSub)){
                    //CORRECTE
                    return true;
                }else{
                    //ERROR
                    System.out.println("El tipus de l'assignació no es el mateix que el de la variable");
                    return false;
                }

            }
        }
    }
    public boolean gestAsig(SymbolValor valor, SymbolOperacio operacio){

        String iden = valor.getIden();
        SymbolVarInit varinit = operacio.getVarInit();

        Simbol id = ts.consulta(iden);
        TipusSub tipusSub = id.getTipusSub();
        Tipus tipus = id.getTipus();

        if(tipus.equals(Tipus.CONST)){
            System.out.println("Error al modificar una constant");
            return false;
        }
        //Es array
        if(varinit.isIsarray()){
            if(!(tipus.equals(Tipus.ARRAY))){
                System.out.println("LA variable no és un array!!!");
                return false;
            }

            //S'inicia un array
            SymbolArrayInit arrayInit = varinit.getArray();
            SymbolExpressioSimple exprArray = arrayInit.getExpr();
            TipusSub arraytsub = arrayInit.getT();
            //exprArray.setTsResultat();
            TipusSub exprTsub = exprArray.getTipusSubResultat();

            //expressió incorrecta
            if(exprTsub==null){
                return false;
            }else{
                //tamany array correcte? [i]
                if(exprTsub.equals(TipusSub.INT)){
                    if(arraytsub.equals(tipusSub)){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
            //No es array
        }else{
            
            SymbolExpressioSimple expr = varinit.getExpr();
            System.out.println("varinit: ");
            //expr.setTsResultat();
            //Comprobar si es null
            if(!varinit.esBuit()){
            TipusSub exprTsub = expr.getTipusSubResultat();
            if(exprTsub==null){
                System.out.println("Expressio incorrecte a la assignació");
                return false;
            }
            //S'inicia una variable o constant
            if(exprTsub.equals(tipusSub)){
                //CORRECTE
                return true;
            }else{
                //ERROR
                System.out.println("El tipus de l'assignació no es el mateix que el de la variable");
                return false;
            }
            }
            return true;
        
    }
    }
    public boolean AsigExpr(SymbolOperacio operacio){
        SymbolVarInit varinit = operacio.getVarInit();
        if (varinit == null){
            return true;
        }else{
            return false;
        }
    }
    public boolean gestSwitch(SymbolExpressioSimple expr, SymbolLCases lcases){
        TipusSub tsub = lcases.getCase().getExpr().getTipusSubResultat();
        while(tsub == lcases.getCase().getExpr().getTipusSubResultat() && lcases!=null){
            lcases = lcases.getLCases();
        }
        if(tsub != lcases.getCase().getExpr().getTipusSubResultat()){
            System.out.println("No tots els cases tenen el mateix Tipus");
            return false;
        }else{
            if(expr.getTipusSubResultat()==tsub){
                return true;
            }else{
                System.out.println("El switch no es del mateix tipus que la resta de CASES");
                return false;
            }
        }

    }
    /*
    Mètode per a gestionar els possibles errors del bucle for,
    incloent una correcta inicialització,expressio intermitja i postExpression.
     */
    public boolean gestForLoop(SymbolForInit init,SymbolExpressioSimple expr,SymbolForPostExpression forPost){
            String msg_Errors[]={"invàlida declaració.","la expressió intermitja ha de ser una condicio!","expressió final incorrecta"};
            int errors[]={0,0,0};
            boolean hiHaError=false;
            //Corregir inicialització
            if(init.getVarDecl().isEmpty()){
                errors[0]=1;
                hiHaError=true;
            }
            //Corregir Expressió intermitja
            if(expr.getTipusSubResultat()!=TipusSub.BOOLEAN){
                errors[1]=1;
                hiHaError=true;
            }
            //Corregir postExpr
            if(forPost.getExpressioSimple().getTipusSubResultat()==null){
                errors[2]=1;
                hiHaError=true;
            }
            for (int i = 0; i < 3; i++) {
                if(errors[i]==1){
                    System.out.println("Error al for: "+msg_Errors[i]);
                }
            }
            return hiHaError;
    }
    public boolean gestIdArray(String iden){
        Simbol id = ts.consulta(iden);
        TipusSub tipusSub = id.getTipusSub();
        Tipus tipus = id.getTipus();
        if(tipus.equals(Tipus.ARRAY)){
            System.out.println("Estas assignant un array com una variable");
            return false;
        }
        return true;
    }
}