package GeneracioCodiIntermedi;
import  Symbols.TipusSub;

public class Paramtre {

    private String nom;
    private TipusSub tipusSub;

    public Parametre(String n, Tipus t){
        this.nom=n;
        this.tipusSub=t;
    }

    //GETTERS
    public String getNom() {
        return this.nom;
    }

    public Tipus getTipusSub(){
        return this.tipusSub;
    }

    //SETTERS
    public void setNom(String n){
        this.nom=n;
    }

    public void setTipusSub(Tipus t){
        this.tipusSub=t;
    }

    // Mètode toString
    @Override
    public String toString(){
        return "Tipus: "+this.tipusSub+" --> "+this.nom
    }


}