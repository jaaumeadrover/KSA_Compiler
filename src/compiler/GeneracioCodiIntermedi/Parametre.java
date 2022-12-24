package compiler.GeneracioCodiIntermedi;
import  compiler.Symbols.TaulaSimbols.TipusSub;

public class Parametre {

    private String nom;
    private TipusSub tipusSub;

    public Parametre(String n, TipusSub t){
        this.nom=n;
        this.tipusSub=t;
    }

    //GETTERS
    public String getNom() {
        return this.nom;
    }

    public TipusSub getTipusSub(){
        return this.tipusSub;
    }

    //SETTERS
    public void setNom(String n){
        this.nom=n;
    }

    public void setTipusSub(TipusSub t){
        this.tipusSub=t;
    }

    // Mètode toString
    @Override
    public String toString(){
        return "TipusSub: "+this.tipusSub+" --> "+this.nom;
    }


}