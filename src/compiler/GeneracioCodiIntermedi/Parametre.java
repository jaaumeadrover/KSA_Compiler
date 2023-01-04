package compiler.GeneracioCodiIntermedi;
import  compiler.Symbols.TaulaSimbols.TipusSub;
import  compiler.Symbols.TaulaSimbols.Tipus;

public class Parametre {

    private String nom;
    private TipusSub tipusSub;
    private Tipus tipus;

    public Parametre(String n, TipusSub t, Tipus tipus){
        this.nom=n;
        this.tipusSub=t;
        this.tipus= tipus;
    }

    //GETTERS
    public String getNom() {
        return this.nom;
    }

    public TipusSub getTipusSub(){
        return this.tipusSub;
    }

    public Tipus getTipus(){return this.tipus;}

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