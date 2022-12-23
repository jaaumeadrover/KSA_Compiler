package GeneracioCodiIntermedi;
import ArbreSintactic.operacions;
import  Symbols.TipusSub;
import java.util.ArrayList;

public class codiTresAdreces{

    // Arraylist que emmagatzemarà el codi de 3 adreçes que anem generant
    private  ArrayList<Instruccio> codi;

    // Arraylist que emmagatzemarà les constants del codi per tal de poder accedir a elles
    // durant el procés de generació de codi intermedi
    private  ArrayList<Instruccio> constants;

    // Valor boolean que ens permet saber si es tracta d'una declaració, o no
    private boolean declaracio = false;

    // Arraylist en el qual anirem montant els subprogrames
    private  ArrayList<Parametre> parametres;

    // Taula de variables per emmagatzemar descripcions
    private  ArrayList<Variable> TV;
    // nombre de variables existents
    private int nv=0;

    // Taula de procediments per emmagatzemar dades descriptives dels procediments
    private  ArrayList<Procediment> TP;
    // comptador per saber quants de procediments es tenen
    private int np=0;
    // Nombre de procediment actius
    private int npActius = 0;

    //comptador d'estiquetes existents
    private int etiquetes=0;

    //Afegim una nova etiqueta i tornam el seu nom (les etiquetes començen per 0)
    public String novaEtiqueta() {
        String novaEtiqueta =  "e" + ne;
        this.ne++;
        return this.novaEtiqueta
    }

    // Inicialitzam l'arraylist que contindrà el còdi de tres adreçes
    public void crearLlistaCodi(){
        this.codi = new ArrayList<>();
    }

    // Mètode per obtenir el codi de 3 adreçes
    public ArrayList<Instruccio> getCodi() {
        return this.codi;
    }

    // Mètode per donar valor a l'arraylist codi
    public void setCodi(ArrayList<Instruccio> c) {
        this.codi = c;
    }

    // Inicialitzam l'arraylist que contindrà el còdi de tres adreçes
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < codi.size(); i++) {
            s += codi.get(i).toString() + "\n";
        }
        return s;
    }
}

    // Inicialitzam la llista de constants
    public void crearLlistaConstants(){
        this.constants = new ArrayList<>();
    }

    /*
        MÈTODES RELACIONATS AMB LES VARIABLES
     */

    // Cream una nova llista de variables desde zero
    public void crearLlistaVariables() {
        this.TV = new ArrayList<>();
    }

    // Afegeix una nova varioble a la taula de variables
    public String novaVariable(String nom,TipusSub t) {
        // Nom diferent de null = variable no temporal
        if (nom != null) {
            int i = 0;
            while (i < tv.size()) {
                Variable v = tv.get(i);
                // Cas on variable dins TV
                if (v.getNom().equals(nom) && v.getProc() == npActius) {
                    return nom + "_" + npActius;
                }
                i++;

            }
        } else {
            // Afegim a la taula de variables una nova varible temporal,
            // ja que com no té nom, no ha estat declarada i sabem que ha estat
            // creada per a la realització del codi de tres adreces
            Variable novaVar=new Variable("temporal" + nv, t, npActius, true)
            tv.add(novaVar);
            return "t" + nv;
            nv++;
        }
        // Si la nova variable no es temporal (tenia nom) i no es trobava
        // a la taula de variables (no ha fet el return del while) l'afegim a la
        // taula de variables amb el seu nom
        Variable novaVar=new Variable(nom, t, npActius, false)
        tv.add(novaVar);
        return nom + "_" + npActius;
    }

    // Retorna el nom d'una variable afegint l'àmbit en el que l'utilitzam
    public String getNomVariable(String nom) {
        // Anem iterant sobre tots els elements de la taula de variables
        for (i = 0 ;i < tv.size(); i++){
            Variable v = tv.get(i);
            // Cas on la variable no té processos actius (no s'està utilitzant)
            if (v.getNom().equals(nom) && v.getProc() == 0) {
                if(v.getTemporal()){
                    return nom;
                }
                return nom + "_" + 0;

            // Cas on la variable té processos actius (s'està utilitzant)
            }else if (v.getId().equals(nom) && v.getProc() == npActius){
                if(v.getTemporal()){
                    // si la variable és temporal tan sols retornam el nom
                    return nom;
                }
                //si no és temporal retornam el nom amb el número de processos actius
                return nom + "_" + npActius;
            }
        }

        // si no hem trobat la variabe a la taula de variables retornam 'null'
        return null;
    }

    // Mètode que ens permet borrar la variable de la taula de variables amb el nom que
    // ens passen per paràmetre
    public void eliminaVariable(String nom) {
        for (int i = 0; i < tv.size(); i++) {
            if (tv.get(i).getNom().equals(nom)) {
                tv.remove(i);
                break;
            }

        }
    }

    //???????
    // Devuelve la variable solicitada.
    public Variable getVariable(String nom) {
        int div = nom.lastIndexOf("_");
        if (div >= 0) {
                        int capa = Integer.parseInt(nom.substring(div + 1));
            String n = nom.substring(0, div);
            for (int i = 0; i < tv.size(); i++) {
                if (tv.get(i).nom.equals(n) && tv.get(i).procedimiento == capa) {
                    return tv.get(i);
                }
            }
        } else {
            for (int i = 0; i < tv.size(); i++) {
                if (tv.get(i).id.equals(nom)) {
                    return tv.get(i);
                }
            }
        }
        return null;
    }

    //?????
    public Variable getVariable(String nom) {
        for (int i = 0; i < tv.size(); i++) {
            if (tv.get(i).nom.equals(id)) {
                return tv.get(i);
            }
        }
        return null;
    }

    // Retorna la taula de variables
    public ArrayList<Variable> getTaulaVariables() {
        return this.TV;
    }

    // Mètode per mostrar per pantalla la taula de variables
    public String () {
        String s = "";
        for (int i = 0; i < tv.size(); i++) {
            s += tv.get(i).toString() + "\n";
        }
        return s;
    }


    /*
        MÈTODES RELACIONATS AMB ELS PROCEDIMENTS
    */

    // Cream una nova llista de Procediments desde zero
    public void crearLlistaProcediments() {
        parametres = new ArrayList<>();
    }

    // Crea un nou procediment
    public void crearProcediment(String nom, TipusSub ts) {
        // augmentam comptador de procediments
        np++;
        // el numero de procediments serà igual al numero de procesos actius
        npa = np;
        // cream el nou procediment sense parametres (atribut 'null' del constructor)
        Procediment proc=new Procediment(nom, npa, ts, null);
        // afegim el nou procediment
        tp.add(proc);
    }

    // Mètode que retorna el procediment amb el nom indicat per paràmetre
    public Procedimiento getPro(String nom) {
        for (int i = 0; i < tp.size(); i++) {
            if (tp.get(i).geNom().equals(nom)) {
                return tp.get(i);
            }
        }
        return null;
    }

    // Mètode que retorna el tipusSubjacent del que retorna
    public TipusSub getTipusSubProc(String nom) {
        // cercam el procediment a la taula de procediments
        for (int i = 0; i < tp.size(); i++) {
            if (tp.get(i).getNom()==nom) {
                // si trobam el procediment, retornam el seu TipusSub
                return tp.get(i).getTipusSub();
            }
        }
        // si no trobam el procediment no retornam res
        return null;
    }

    // Mètode per passar la taula de procediments a string
    public String TaulaProcedimentsToString() {
        String s = "";
        for (int i = 0; i < tp.size(); i++) {
            s += tp.get(i).toString() + "\n";
        }
        return s;
    }

    // Tancam un procediment posant el nombre de procesos actius a 0
    public void closeProcedimiento() {
        npa = 0;
    }



    /*
        MÈTODES RELACIONATS AMB ELS PARÀMETRES
    */

    // Cream una nova llista de paràmetres desde zero
    public void crearLlistaParametres() {
        parametres = new ArrayList<>();
    }

    // Afegim un nou paràmetre a la llista de paràmetres
    public void afegirParametre(String nom, TipusSub ts) {
        // cream el nou paràmetre
        Parametro p = new Parametro(nom, ts);
        // cream una nova variable mitjançant el mètode creat anteriorment
        novaVariable(nom, ts);
        // afegim el paràmetre
        param.add(p);
    }

    // Afegim paràmetres al procediment
    public void afegirParamProcediment(ArrayList<Parametre> params) {
        Procedimient proc = tp.get(tp.size() - 1);
        // afegim els parametres al procediment
        proc.parametres = params;
    }

    // Retorna la llista de paràmetres.
    public ArrayList<Parametro> getLlistaParametres() {
        return parametres;
    }

    /*
        MÈTODES RELACIONATS AMB LA ESPECIFICACIÓ DE CODI INTERMEDI
    */

    // Genera l'instrucció en 3 direccions
    public void genera(OperacionsCTA o, String oper1, String oper2, String desti) {
        if (declaracio && npa == 0) {
            constants.add(new Instruccion(o, oper1, oper2, desti));
        } else {
            codi.add(new Instruccion(o, oper1, oper2, desti));
        }
    }

    // Inicia la declaracio del programa
    public void començaDecl() {
        genera(OperacionsCTA.GOTO, null, null, "run");
        this.declaracio = true;
    }

    // FFin de declaraciones del programa.
    public void finalitzaDecl() {
        this.declaracio = false;
        genera(OperacionsCTA.SKIP, null, null, "run");
        codi.addAll(constante);
        constants.clear();
    }

    // Tradueix l'operador de l'abre sintactic a la seva corresponent instrucció
    // al codi de 3 direccions
    public OperacionsCTA getOperacionsCTA(Operacions oper) {
        switch (oper) {
            case ADD:
                return OperacionsCTA.SUMA;
            case SUB:
                return OperacionsCTA.RESTA;
            case MUL:
                return OperacionsCTA.PRODUCTE;
            case DIV:
                return OperacionsCTA.DIVISIO;
            case MOD:
                return OperacionsCTA.MODUL;
            case EQ:
                return OperacionsCTA.EQ;
            case BG:
                return OperacionsCTA.GT;
            case SM:
                return OperacionsCTA.LT;
            case BGEQ:
                return OperacionsCTA.GE;
            case SMEQ:
                return OperacionsCTA.LE;
            case OR:
                return OperacionsCTA.OR;
            case AND:
                return OperacionsCTA.AND;
            case NEG:
                return OperacionsCTA.NOT;
            default:
                return null;
        }
    }

    // Retorna el tipusSub d'operació
    public TipusSub getTipusSubOperacio(OperacionsCTA operacio) {
        if (operacio == OperacionsCTA.MULT || operacio == OperacionsCTA.DIV || operacio == OperacionsCTA.SUMA || operacio == OperacionsCTA.RESTA) {
            return TipusSub.INT;
        } else {
            return TipusSub.BOOLEAN;
        }
        // POSSIBILITAT D'AFEGIR CASUISTICA DE STRINGS
    }


}