/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaulaSimbols;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jaume
 */
public class TaulaSimbols {

    private ArrayList<Simbol> ts; //taula de simbols
    private HashMap<Integer, Simbol> ta; //taula d'ambits
    private int nivell;
    private int nivellMax;
    private int punterInici;

    public TaulaSimbols(TaulaSimbols prev) {
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
    public int afegeixSimbol(String id,Tipus t,TipusSub tSub,int pos){

            if(tSub==TipusSub.FUNC){//si símbol està dins una funció
                if(getFuncio(id) != null){ //si no existeix la funció retorna 0
                    return 0;
                }
                Simbol sym=new Simbol(id,t,nivell,tSub,pos);
                ta.put(nivell,sym);
                ts.add(punterInici,sym);
                nivell=0;
                punterInici =0;
            }else{
                if(consulta(id) != null){   //si ja existeix
                    return 0;
                }
                if(tSub != TipusSub.PARAM){
                    Simbol simbol = new Simbol(id, tipus, nivell, tSub, pos);
                    if (nivell == 0 && !ta.isEmpty()) {
                        ts.add(ts.indexOf(ta.get(1)), simbol);
                    } else {
                        ts.add(simbol);
                    }
                }else{
                    Simbol simbol = new Simbol(id, t, nivell, tSub, pos);
                    ts.add(punterInici, s);
                }
            }

    }

    public void afegeixNivell(){
        nivells++;
        nivell=nivells;
        punterInici=ts.size();

    }
}

    public Simbol consultaFunc(String identificador){
        //si la taula d'àmbits no buida
        if(ta.isEmpty()){
            return null;
        }

        for(int i=0;i<ta.size();i++){
            Simbol sym=ta.get(i);
            if(sym.id==identificador){
                return sym;
            }
        }
    }
    public Simbol consulta(String identificador){
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

    public Simbol getSimbol(String id) {
        if (nivell != 0) {
            for (int i = punterInici; i < ts.size(); i++) {
                Simbol s = ts.get(i);
                if (s.getId().equals(id)) {
                    return s;
                }
            }
        }
        int max = 0;
        if (ta.isEmpty()) {
            max = ts.size();
        } else {
            max = ts.indexOf(ta.get(1));
        }
        for (int i = 0; i < max; i++) {
            Simbol s = ts.get(i);
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public Simbol consultaParametre(Simbol f, int n) {
        int i = ts.indexOf(f);
        if (i + n < ts.size()) {
            Simbol s = ts.get(i + n);
            if (s.getTipoSub().equals(TipoSub.PARAMETRO) && s.getNivel() == f.getNivel()) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String text = "";
        for (Simbol simbol : ts) {
            if (simbol.getNivel() > 0 && !(simbol.getTipoSub()==TipusSub.FUNCION)) {
                text += "\t" + simbol.toString() + "\n";
            } else {
                text += simbol.toString() + "\n";
            }
        }
        return txt;
    }






}