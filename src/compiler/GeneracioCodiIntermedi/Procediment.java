package compiler.GeneracioCodiIntermedi;
import  compiler.Symbols.TaulaSimbols.TipusSub;
import java.util.ArrayList;

/*
    CLASSE: Procediment
    AUTOR: ATA2
    FUNCIONALITAT: ens serveix per a poder crear objectes procediment.
    DATA CREACIÓ: 26/12/2022
 */
public class Procediment{

    private int nivell;
    private String nomProc;
    private int ocupacio;
    private int numParams;
    private ArrayList<Variable> parametres;
    private TipusSub tipus;

    // Constructor de la classe
    public Procediment(int nivell, String id, int ocupacio, int nparams){
        this.nivell = nivell;
        this.idProc = id;
        this.ocupacio = ocupacio;
        this.numParams = nparams;
    }

    public Procediment(int nivell, String id int nparams){
        this.nivell = nivell;
        this.idProc = id;
        this.numParams = nparams;
    }

    public Procediment(String id){
        this.idProc = id;
    }

    public Procediment(String id, ArrayList<Variable> params){
        this.idProc = id;
        this.numParams = params;
    }


    // GETTERS
    public String getNomProc() {
        return this.nomProc;
    }

    public int getNivell() {
        return this.nivell;
    }

    public int getNumParametres() {
        return this.numParams;
    }

    public int getOcupacio() {
        return this.ocupacio;
    }

    public TipusSub getTipusSub() {
        return this.tipus;
    }

    public ArrayList<Parametre> getParametres() {
        return this.parametres;
    }

    // SETTERS
    public void setNomProc(String nom) {
        this.nomProc = nom;
    }

    public void setNumParametres(int num) {
        this.numParams = num;
    }

    public void setTipusSub(TipusSub ts) {
        this.tipus = ts;
    }

    public void setParametres(ArrayList<Parametre> params) {
        this.parametres = params;
    }

    public void setNivell(int n) {
        this.nivell = n;
    }

    public void setOcupacio(int o) {
        this.ocupacio = o;
    }

    // Mètode toString
    @Override
    public String toString() {
        String s = "\n";
        if (parametres != null) {
            for (int i = 0; i < parametres.size(); i++) {
                s += "\t" + parametres.get(i).toString() + "\n";
            }
        }
        return this.nomProc + "; " + numParams + "; " + tipus + "; " + s;
    }

}