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


    private String nomProc;
    private TipusSub tsub;
    private ArrayList<Parametre> parametres;


    // Constructors de la classe
    public Procediment(String nom, TipusSub tsub, ArrayList<Parametre> parametres){

        this.nomProc = nom;
        this.tsub = tsub;
        if(parametres!=null){
            this.parametres = parametres;
        }else{
            this.parametres= new ArrayList<>();
        }
        

    }

    // GETTERS
    public String getNomProc() {
        return this.nomProc;
    }
   
    public TipusSub getTipusSub() {
        return this.tsub;
    }

    public ArrayList<Parametre> getParametres() {
        return this.parametres;
    }

    // SETTERS
    public void setNomProc(String nom) {
        this.nomProc = nom;
    }

    public void setTipusSub(TipusSub ts) {
        this.tsub = ts;
    }

    public void setParametres(ArrayList<Parametre> params) {
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
        return this.nomProc + "; " + parametres.size() + "; " + tsub + "; " + s;
    }

}