package compiler.GeneracioCodiIntermedi;
import Symbols.TipusSub;

public class Procediments{

    private String nom;
    private int numProcediments;
    private TipusSub tipusSub;
    private ArrayList<Parametre> parametres;

    // Constructor de la classe
    public Procediment(String nom, int numProcediment, TipusSub ts, ArrayList<Params> parametres){
        this.nom = nom;
        this.numProcediments = numProcediment;
        this.tipusSub = ts;
        this.parametres = parametres;
    }

    // GETTERS
    public String getNom() {
        return this.nom;
    }

    public String getNumProcediment() {
        return this.numProcediments;
    }

    public String getTipusSub() {
        return this.tipusSub;
    }

    public String getParametres() {
        return this.parametres;
    }

    // SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumProcediment(int nombreProce) {
        this.numProcediments = nombreProce;
    }

    public void setTipusSub(TipusSub ts) {
        this.tipusSub = ts;
    }

    public void setParams(ArrayList<Params> params) {
        this.parametres = params;
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
        return this.nom + "; " + numProcediments + "; " + valRetorn + "; " + s;
    }

}