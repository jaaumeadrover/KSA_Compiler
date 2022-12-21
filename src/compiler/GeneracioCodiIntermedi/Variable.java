package GeneracioCodiIntermedi;
import  Symbols.TipusSub;

public class Variable{
    private String nom;
    private TipusSub tipusSub;
    private boolean t;
    private int proc;

    public Variable(String n, TipusSub t, boolean b, int p){
        this.nom=n;
        this.tipusSub=t;
        this.t=b;
        this.proc=p;
    }

    //GETTERS
    public String getNom(){
        return this.nom;
    }

    public TipusSub getTipusSub() {
        return this.tipusSub;
    }

    public boolean getT(){
        return this.t;
    }

    public int getProc(){
        return this.proc;
    }

    //SETTERS
    public void setNom(String n){
        this.nom=n;
    }

    public void setTipusSub(TipusSub t){
        this.tipusSub=t;
    }

    public void setT(boolean b){
        this.t=b;
    }

    public void setProc(int p){
        this.proc=p;
    }

    // Mètode toString
    @Override
    public String toString(){
        return "Nom: "+this.nom+"; Tipus: "+this.tipusSub+"; Procediment: "+this.proc;
    }

}