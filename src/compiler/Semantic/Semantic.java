package compiler.Semantic;

//importar taula de simbols i arbre sintactic

import compiler.ArbreSintactic.*;
import compiler.Symbols.TaulaSimbols.*;
import java.util.ArrayList;


public class Semantic {
    private TaulaSimbols ts;
    public ArrayList<String> errors;

    public Semantic (TaulaSimbols ts){
        this.ts=ts;
        this.errors = new ArrayList<>();
    }

    /*
    Funció per a gestionar les expressions simples.
    Tenim 3 casos diferents:
    
    1. El tipusSub de la expr és NULL: la expressió conté errors (ERROS DE QUÈ??? LEXICS??)
    2. El tipusSub de la expr és diferent a t: s'esperava un valor int per exemple i es retorna boolean.
    3. El tipusSub de la expr és el mateix que t.
    
    */
   public boolean gestExpr(SymbolExpressioSimple expr, TipusSub t,int posicio){
        
        TipusSub tipoExpr = expr.getTipusSubResultat();
        System.out.println("TIPUS EXPRESSIO: "+expr.getTipusSubResultat());
        
        // Cas 1: tipoExpr exp == NULL
        if(tipoExpr == null){
            System.out.println("ERROR");
            return false;
        // Cas 2: tipoExpr != t
        }else if(tipoExpr != t){
            System.out.println("EROR: S'esperava trobar un "+ t.toString() +" i s'ha trobat un " + tipoExpr.toString());
            //Ho afeigm al parser per identificar quin tipus de senténcia es incorrecte
            return false;
        // Cas 3: tipoExpr == t
        }
        System.out.println("CORRECTE");
        return true;
        
    }
   public boolean isExprCorrecta(SymbolExpressioSimple expr,int posicio){
       return expr.getTipusSubResultat()!=null;
   }
    
    public boolean gestFunc(TipusSub tfunc, SymbolReturn exprRtn){
        SymbolExpressioSimple expresioReturn = exprRtn.getExpr();
        //expresioReturn.setTsResultat();
        TipusSub tipoExpr = expresioReturn.getTipusSubResultat();
        //System.out.println("TIPUS FUNCIÓ: "+tfunc+", TIPUS EXPR: "+tipoExpr);

        if(tipoExpr==tfunc){
            //System.out.println("CORRECTE");
            return true;
        }else{
            //System.out.println("INCORRECTE");
            errors.add("ERROR Semántic, la funció no retorna un "+tfunc.toString());
            return false;
        }
    }
    public boolean gestAsigDecl(String iden, SymbolVarInit varinit,int posicio){
        Simbol id = ts.consulta(iden);
        TipusSub tipusSub = id.getTipusSub();
        Tipus tipus = id.getTipus();
        
        if(varinit.esBuit()){
            //se declara pero no se inicialitza
            if(tipus==Tipus.CONST){
                //Es una constant
                //ERROR
                //System.out.println("La constant s'ha d'assignar quan es declara");
                errors.add("ERROR Semántic, la constant s'ha d'assignar quan es declara. Linea: "+posicio);
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
                    errors.add("ERROR Semántic, Aixó no es un array");
                    return false;
                }
                //expressió incorrecta
                if(exprTsub==null){
                    errors.add("ERROR Semántic, aquesta expressió es incorrecte");
                    return false;
                }else{
                    //tamany array correcte? [i]
                    if(exprTsub.equals(TipusSub.INT)){
                        if(arraytsub.equals(tipusSub)){
                            return true;
                        }else{
                            errors.add("ERROR Semántic, el tipus de l'array no coincideix");
                            return false;
                        }
                    }else{
                        errors.add("ERROR Semántic, El tamany de l'array ha de ser un integer");
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
                    errors.add("ERROR Semántic, l'expressió de la declaració és incorrecte. Linea: "+posicio);
                    return false;
                }
                //S'inicia una variable o constant
                if(exprTsub.equals(tipusSub)){
                    //CORRECTE
                    return true;
                }else{
                    //ERROR
                    errors.add("ERROR Semántic, El tipus de la declaració no és correcte");
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
            errors.add("ERROR Semántic, la constant no es pot modificar");
            return false;
        }
        //Es array
        if(varinit.isIsarray()){
            if(!(tipus.equals(Tipus.ARRAY))){
                errors.add("ERROR Semántic, la variable no es un array !!");
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
                errors.add("ERROR Semántic, l'expressió no es correcte");
                return false;
            }else{
                //tamany array correcte? [i]
                if(exprTsub.equals(TipusSub.INT)){
                    if(arraytsub.equals(tipusSub)){
                        return true;
                    }else{
                        errors.add("ERROR Semántic, el tipus no coincideix");
                        return false;
                    }
                }else{
                    errors.add("ERROR Semántic, el tamany de l'array ha de ser un integer");
                    return false;
                }
            }
            //No es array
        }else{
            
            SymbolExpressioSimple expr = varinit.getExpr();
            //System.out.println("varinit: ");
            //expr.setTsResultat();
            //Comprobar si es null
            if(!varinit.esBuit()){
            TipusSub exprTsub = expr.getTipusSubResultat();
            if(exprTsub==null){
                errors.add("ERROR Semántic, l'expressió no es correcte");
                return false;
            }
            //S'inicia una variable o constant
            if(exprTsub.equals(tipusSub)){
                //CORRECTE
                return true;
            }else{
                //ERROR
                //System.out.println("El tipus de l'assignació no es el mateix que el de la variable");
                errors.add("ERROR Semántic, el tipus no coincideix");
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
            System.out.println("cases incorrecte");
            errors.add("ERROR Semántic, els cases no tenen el mateix tipus");
            return false;
        }else{
            if(expr.getTipusSubResultat()==tsub){
                return true;
            }else{
                System.out.println("switch incorrecte");
                errors.add("ERROR Semántic, el tipus del switch no coincideix amb els cases");
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
            boolean hiHaError=false;
            //Corregir inicialització
            if(init.getVarDecl().isEmpty()){
                errors.add("ERROR Semántic, al for "+msg_Errors[0]);
                hiHaError=true;
            }
            //Corregir Expressió intermitja
            if(expr.getTipusSubResultat()!=TipusSub.BOOLEAN){
                errors.add("ERROR Semántic, al for "+msg_Errors[1]);
                hiHaError=true;
            }
            //Corregir postExpr
            if(forPost.getExpressioSimple().getTipusSubResultat()==null){
                errors.add("ERROR Semántic, al for "+msg_Errors[2]);
                hiHaError=true;
            }

            return !hiHaError;
    }
    public boolean gestIdArray(String iden){
        Simbol id = ts.consulta(iden);
        TipusSub tipusSub = id.getTipusSub();
        Tipus tipus = id.getTipus();
        if(tipus.equals(Tipus.ARRAY)){
            //System.out.println("Estas assignant un array com una variable");
            errors.add("ERROR Semántic, Estas assignant un array com una variable ");
            return false;
        }
        return true;
    }

    public void addError(String error){
        errors.add(error);
    }

    public ArrayList<String> geterrorsSemantic() {
        return errors;
    }
}