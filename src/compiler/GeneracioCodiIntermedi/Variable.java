package GeneracioCodiIntermedi;
import  Symbols.TipusSub;

public class Variable{
    // Utilizam un string
    private String nom;
    private TipusSub tipusSub;
    // Boolean per saber si la variable es temporal (s'utilitza per poder obtenir
    // el codi de tres adreces de forma correcta)
    private boolean temp;
    private int proc;

    public Variable(String n, TipusSub t, boolean b, int p){
        this.nom=n;
        this.tipusSub=t;
        this.temp =b;
        this.proc=p;
    }

    //GETTERS
    public String getNom(){
        return this.nom;
    }

    public TipusSub getTipusSub() {
        return this.tipusSub;
    }

    public boolean getTemp(){
        return this.temp;
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
        this.temp =b;
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