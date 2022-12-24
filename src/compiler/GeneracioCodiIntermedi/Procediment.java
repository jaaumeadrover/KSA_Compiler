package compiler.GeneracioCodiIntermedi;
import  compiler.Symbols.TaulaSimbols.TipusSub;
import java.util.ArrayList;

public class Procediment{

    private String nom;
    private int numProcediments;
    private TipusSub tipusSub;
    private ArrayList<Parametre> parametres;

    // Constructor de la classe
    public Procediment(String nom, int numProcediment, TipusSub ts, ArrayList<Parametre> parametres){
        this.nom = nom;
        this.numProcediments = numProcediment;
        this.tipusSub = ts;
        this.parametres = parametres;
    }

    // GETTERS
    public String getNom() {
        return this.nom;
    }

    public int getNumProcediment() {
        return this.numProcediments;
    }

    public TipusSub getTipusSub() {
        return this.tipusSub;
    }

    public ArrayList<Parametre> getParametres() {
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

    public void setParams(ArrayList<Parametre> params) {
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
        return this.nom + "; " + numProcediments + "; " + tipusSub + "; " + s;
    }

}