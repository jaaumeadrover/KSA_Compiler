package compiler.Semantic;

//importar taula de simbols i arbre sintactic
import compiler.ArbreSintactic.*;
import compiler.Symbols.TaulaSimbols.*;
import java.util.ArrayList;

public class Semantic {

    private TaulaSimbols ts;
    public ArrayList<String> errors;

    public Semantic(TaulaSimbols ts) {
        this.ts = ts;
        this.errors = new ArrayList<>();
    }

    /*
    Funció per a gestionar les expressions simples. 
    Tenim 3 casos diferents:
    
    1. El tipusSub de la expr és NULL: la expressió conté errors (ERROS DE QUÈ??? LEXICS??)
    2. El tipusSub de la expr és diferent a t: s'esperava un valor int per exemple i es retorna boolean.
    3. El tipusSub de la expr és el mateix que t.
     */
    public boolean gestExpr(SymbolExpressioSimple expr, TipusSub t, int posicio) {

        TipusSub tipoExpr = expr.getTipusSubResultat();

        // Cas 1: tipoExpr exp == NULL
        if (tipoExpr == null) {

            errors.add("ERROR Semàntic: Expressió incorrecte. Linea: " + (posicio+1));
            return false;
            // Cas 2: tipoExpr != t
        } else if (tipoExpr != t) {
            errors.add("ERROR Semàntic: S'esperava trobar un " + t.toString() + " i s'ha trobat un " + tipoExpr.toString());
            //Ho afeigm al parser per identificar quin tipus de senténcia es incorrecte
            return false;
            // Cas 3: tipoExpr == t
        }
        return true;

    }
    
    public boolean gestPrint(SymbolLiteral literal){
        Simbol s = ts.consultaSimbol(literal.toString());
        TipusSub tipusSub = s.getTipusSub();
        Tipus tipus = s.getTipus();
        if(tipusSub!=TipusSub.STRING){
            return false;
        }else{
            return true;
        }
    }
    /*
    Mètode per a comprovar que una crida amb paràmetres és correcta
     */
    public boolean paramCall(Simbol func, ArrayList<SymbolValor> values,int linea){
        ArrayList<Simbol> llista=ts.nParametresFunc(func);//llista paràmetres que conté la funció
        boolean error=false;

        //Missatge de paràmetres esperats
        String msg="";
        for(int i=llista.size()-1;i>=0;i--){
            msg+="{";
            if(llista.get(i).getDimensio()>1){
                msg+="array:";
            }
            msg+=llista.get(i).getTipusSub()+"} ";
        }
        //Missatge de paràmetres inserits
        String msg2="";
        for(int i=0;i<values.size();i++){
            msg2+="{";
            if(values.get(i).isIsarray()){
                msg2+="array:";
            }
            msg2+=values.get(i).getTipusSub()+"} ";
        }


        //si s'han introduit el mateix nombre de paràmetresQ
        if(values.size()==llista.size()){
            for (int i = 0; i <llista.size(); i++) {
                if(values.get(values.size()-1-i).getTipusSub()!=llista.get(i).getTipusSub()){
                    error=true;
                }
            }
            //si s'ha trobat error
            if(error){
                errors.add("ERROR Semàntic: s'esperen: "+msg+", s'ha trobat "+msg2+". Línea: "+linea);
            }
            return !error;
        }else{//ja hi ha error
            errors.add("ERROR Semàntic: La funció "+func.getIdAutoIncrement()+" té "+values.size()+" paràmetres i en necessita "+llista.size()+". " +
                       "S'esperen: "+msg+". S'ha trobat "+msg2+". Línea: "+linea);
            return false;
        }
    }

    public boolean isExprCorrecta(SymbolExpressioSimple expr, int posicio) {
        return expr.getTipusSubResultat() != null;
    }

    public boolean gestFunc(TipusSub tfunc, SymbolReturn exprRtn) {
        SymbolExpressioSimple expresioReturn = exprRtn.getExpr();
        TipusSub tipoExpr = expresioReturn.getTipusSubResultat();

        if (tipoExpr == tfunc) {
            return true;
        } else {
            errors.add("ERROR Semántic, la funció no retorna un " + tfunc.toString());
            return false;
        }
    }

    public boolean gestAsigDecl(String iden, SymbolVarInit varinit, int posicio) {
        Simbol id = ts.consultaSimbol(iden);
        TipusSub tipusSub = id.getTipusSub();
        Tipus tipus = id.getTipus();

        if (varinit.esBuit()) {
            //se declara pero no se inicialitza
            if (tipus == Tipus.CONST) {//Es una constant
                //ERROR
                errors.add("ERROR Semántic, la constant s'ha d'assignar quan es declara. Linea: " + (posicio+1));
                return false;
            } else {//Es una variable
                return true;
            }

        } else {// Es declara i s'inicialitza
            if (varinit.isIsarray()) {//Es array
                SymbolArrayInit arrayInit = varinit.getArray();
                TipusSub arraytsub = arrayInit.getT();
                return true;
            } else {//No es array
                SymbolExpressioSimple expr = varinit.getExpr();
                //Comprobar si es null
                TipusSub exprTsub = expr.getTipusSubResultat();
                if (exprTsub == null) {
                    errors.add("ERROR Semántic, l'expressió de la declaració és incorrecte. Linea: " + (posicio+1));
                    return false;
                }
                //S'inicia una variable o constant
                if (exprTsub.equals(tipusSub)) {
                    //CORRECTE
                    if(tipus == Tipus.CONST){
                        if((varinit.getExpr().getOperacio().isEmpty())&&
                                ((varinit.getExpr().getValor().getIndex() == 3) || (varinit.getExpr().getValor().getIndex() == 4))){
                            return true;
                        }else{
                            errors.add("ERROR Semántic, La constant "+iden+ " s'ha d'inicialitzar amb un valor enter BOOLEAN o INT. Linia: "+(posicio+1));
                            return false;
                        }
                    }else{
                        return true;
                    }
                } else {
                    //ERROR
                    errors.add("ERROR Semántic, El tipus de la declaració no és correcte. Linia: "+(posicio+1));
                    return false;
                }

            }
        }
        
    }
    //Funció per a comprovar la correctessa d'una assignació
    public boolean gestAsig(SymbolValor valor, SymbolOperacio operacio) {

        String iden = valor.getIden();
        SymbolVarInit varinit = operacio.getVarInit();

        Simbol id = ts.consultaSimbol(iden);
        TipusSub tipusSub = id.getTipusSub();
        Tipus tipus = id.getTipus();

        if (tipus.equals(Tipus.CONST)) {
            errors.add("ERROR Semántic, la constant no es pot modificar");
            return false;
        }
        //Es array
        if (varinit.isIsarray()) {
            //s'esta assignant un array a una variable que no ho es
            if (!(tipus.equals(Tipus.ARRAY))) {
                errors.add("ERROR Semántic, la variable no es un array !!");
                return false;
            }
            return true;
        } else {//No es array
            SymbolExpressioSimple expr = varinit.getExpr();
            //Comprobar si es null
            if (!varinit.esBuit()) {
                TipusSub exprTsub = expr.getTipusSubResultat();
                if (exprTsub == null) {
                    errors.add("ERROR Semántic, l'expressió no es correcte");
                    return false;
                }
                //S'inicia una variable o constant
                if (exprTsub.equals(tipusSub)) {
                    //CORRECTE
                    return true;
                } else {
                    //ERROR
                    errors.add("ERROR Semántic, el tipus no coincideix");
                    return false;
                }
            }
            return true;

        }
    }



    public boolean AsigExpr(SymbolOperacio operacio) {
        SymbolVarInit varinit = operacio.getVarInit();
        if (varinit == null) {
            return true;
        } else {
            return false;
        }
    }
    

    public boolean gestSwitch(SymbolExpressioSimple expr, SymbolLCases lcases) {
        TipusSub tsub = expr.getTipusSubResultat();
        boolean b = false;

        while (tsub == lcases.getCase().getExpr().getTipusSubResultat() && lcases != null) {
            lcases = lcases.getLCases();
            if (lcases.getCase() == null) {
                break;
            }
        }
        
        if (lcases.getCase() != null) {
            if (tsub != lcases.getCase().getExpr().getTipusSubResultat()) {
                errors.add("ERROR Semántic, el tipus del case no coincideix amb l'expressió inicial.");
                return false;
            }
            return true;
        } else {
            return true;
        }

    }

    /*
    Mètode per a gestionar els possibles errors del bucle for,
    incloent una correcta inicialització,expressio intermitja i postExpression.
     */
    public boolean gestForLoop(SymbolForInit init, SymbolExpressioSimple expr, SymbolForPostExpression forPost) {
        String msg_Errors[] = {"declaració obligatoria", "la expressió intermitja ha de ser una condicio!", "expressió final incorrecta"};
        boolean hiHaError = false;
        //Corregir inicialització
        if (init==null) {
            errors.add("ERROR Semántic, al for " + msg_Errors[0]);
            hiHaError = true;
        }
        
        //Corregir Expressió intermitja
        if (expr.getTipusSubResultat() != TipusSub.BOOLEAN) {
            errors.add("ERROR Semántic, al for " + msg_Errors[1]);
            hiHaError = true;
        }
        //Corregir postExpr
        if (forPost.getExpressioSimple().getTipusSubResultat() == null) {
            errors.add("ERROR Semántic, al for " + msg_Errors[2]);
            hiHaError = true;
        }

        return !hiHaError;
    }

    /*
    Passam identificador valor variable i true si és un element de un array i no un nom en si
     */
    public boolean gestIdArray(String iden, boolean b) {
        Simbol id = ts.consultaSimbol(iden);
        if (id != null) {
            TipusSub tipusSub = id.getTipusSub();
            Tipus tipus = id.getTipus();
            //si la variable és un array i no un element, no es pot assignar
            if (tipus.equals(Tipus.ARRAY) && !b) {
                errors.add("ERROR Semántic, Estas assignant un array com una variable ");
                return false;
            }
            return true;
        } else {
            errors.add("ERROR Semántic, no existeix la variable: " + iden);
            return false;
        }
    }
    public boolean gestArray(String iden,int pos){
        Simbol id = ts.consultaSimbol(iden);
        if((id.getDimensio()==0)){
            errors.add("ERROR Semántic, la variable "+iden+" no es un array, llavors no es indexable. Linea " +(pos+1));
            return false;
        }else{
            return true;
        }
    }

    public void addError(String error) {
        errors.add(error);
    }

    public ArrayList<String> geterrorsSemantic() {
        return errors;
    }

    public TaulaSimbols getTs() {
        return ts;
    }
}
