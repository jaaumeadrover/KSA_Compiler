package GeneracioCodiIntermedi;
import Symbols.TipusSub;

public class Procediment{

    private String nom;
    private int numProcediment;
    private TipusSub valRetorn;
    private ArrayList<Params> parametres;

    // Constructor de la classe
    public Procediment(String nom, int numProcediment, TipusSub retorn, ArrayList<Params> parametres){
        this.nom = nom;
        this.numProcediment = numProcediment;
        this.valRetorn = retorn;
        this.parametres = parametres;
    }

    // GETTERS
    public String getNom() {
        return nom;
    }

    public String getNumProcediment() {
        return numProcediment;
    }

    public String getValRetorn() {
        return valRetorn;
    }

    public String getParametres() {
        return parametres;
    }

    // SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumProcediment(int nombreProce) {
        this.numProcediment = nombreProce;
    }

    public void setValRetorn(TipusSub valRetorn) {
        this.valRetorn = valRetorn;
    }

    public void setParams(ArrayList<Params> params) {
        this.parametres = params;
    }

    // Mètode toString
    @Override
    public String toString() {
        String s = "\n";
        if (parametros != null) {
            for (int i = 0; i < parametros.size(); i++) {
                s += "\t" + parametros.get(i).toString() + "\n";
            }
        }
        return nombre + " " + numeroProcedimiento + " " + retorno + " " + s;
    }

}