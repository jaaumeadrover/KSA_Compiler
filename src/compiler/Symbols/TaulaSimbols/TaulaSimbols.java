package compiler.Symbols.TaulaSimbols;

import java.util.ArrayList;
import java.util.HashMap;

/**
 AUTORS: ATA2
 CLASSE: TaulaSimbols
 FUNCIONALITAT:
 implementa tota la estructura i funcionalitats de la taula de símbols,
 encarregada d'emmagatzemar variables
 DATA CREACIÓ: 10/12/2023
 */
public class TaulaSimbols {

    private ArrayList<Simbol> ts; //taula de simbols
    private HashMap<Integer, Simbol> ta; //taula d'ambits
    private int nivell;
    private int nivellMax;
    private int punterInici;

    public TaulaSimbols() {
        ts = new ArrayList<>();
        ta = new HashMap<>();
        //variables
        punterInici =0;
        nivellMax =0;
        nivell=0;
    }
    /*
        Funció per afegir el símbol.
        Params: id,tipus variable, tipus subàmbit, posició.
        Return: 0 si no ha funcionat, 1 si funciona
     */
    public int afegeixSimbol(String id, TipusSub tSub,Tipus t,int pos, int dimensio,boolean init){
        if(t==Tipus.FUNC){//si símbol està dins una funció
            if(consultaFunc(id) != null){ //si no existeix la funció retorna 0
                return 0;
            }
            Simbol sym=new Simbol(id,tSub, t, pos,nivell,dimensio,init);
            ta.put(nivell,sym);
            ts.add(punterInici,sym);
            nivell=0;
            punterInici =0;
        }else{
            if(consultaSimbol(id) != null){   //si ja existeix
                return 0;
            }
            if(t != Tipus.PARAM){
                //cas variable/array
                Simbol simbol = new Simbol(id,tSub, t, pos, nivell,dimensio,init);
                if (nivell == 0 && !ta.isEmpty()) {
                    ts.add(ts.indexOf(ta.get(1)), simbol);
                } else {
                    ts.add(simbol);
                }
            }else{
                Simbol simbol = new Simbol(id, tSub, t, pos, nivell,dimensio,init);
                ts.add(punterInici, simbol);
            }
        }
        return 1;
    }

    public void afegeixNivell(){
        nivellMax++;
        nivell=nivellMax;
        punterInici=ts.size();
    }

    public Simbol consultaFunc(String identificador){
        //si la taula d'àmbits buida
        if(ta.isEmpty()){
            return null;
        }

        for(int i=1;i<ta.size()+1;i++){
            Simbol sym=ta.get(i);
            if(sym.getIdAutoIncrement()!=null) {
                if (sym.getIdAutoIncrement().equals(identificador)) {
                    return sym;
                }
            }
        }
        return null;
    }
    /*
    Mètode per a retornar el nombre de parametres que té una func
     */
    public ArrayList<Simbol> nParametresFunc(Simbol func){
        int nivell=func.getNivell();
        ArrayList<Simbol> params = new ArrayList<>();
        for (int i = 1; i < ts.size(); i++) {
            Simbol s=ts.get(i);
            if(s.getNivell()==nivell && s.getTipus().equals(Tipus.PARAM)){
                params.add(s);
            }
        }
        return params;
    }

    /*
    Mètode per a obtenir un Símbol, si no el troba retorna null.
    També ens serveix per a mirar si un Símbol ha estat creat.
    */
    public Simbol consultaSimbol(String identificador){
        //si estam dedins una funció
        if(nivell != 0){
            for (int i = punterInici; i<ts.size();i++){
                Simbol s = ts.get(i);
                if(s.getIdAutoIncrement().equals(identificador)) {
                    return s;
                }
            }
        }
        int max = 0;
        if(ta.isEmpty()){
            max=ts.size();
        }else {
            max = ts.indexOf(ta.get(1));
        }
        for(int i=0;i<max;i++) {
            Simbol s = ts.get(i);
            if(s.getIdAutoIncrement().equals(identificador)) {
                return s;
            }
        }
        return null;
    }

    public Simbol consultaParametre(Simbol f, int n) {
        int i = ts.indexOf(f);
        if (i + n < ts.size()) {
            Simbol s = ts.get(i + n);
            if (s.getTipusSub().equals(Tipus.PARAM) && s.getNivell() == f.getNivell()) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String text = "";
        for (Simbol simbol : ts) {
            if (simbol.getNivell() > 0 && !(simbol.getTipus()==Tipus.FUNC)) {
                text += "\t" + simbol.toString() + "\n";
            } else {
                text += simbol.toString() + "\n";
            }
        }
        return text;
    }
    public int getMida(){
        return ts.size();
    }
}