package compiler.GeneracioCodiIntermedi;
import  compiler.Symbols.TaulaSimbols.TipusSub;

/*
    CLASSE: Variable
    AUTOR: ATA2
    FUNCIONALITAT: ens serveix per a crear objectes que representaran variables.
    DATA CREACIÓ: 26/12/2022
 */

public class Variable{

    private static int contadorVars = 0;

    private int idV;
    private String nom;
    private TipusSub tipusSub;
    private int procedure;
    private boolean constant = false;
    private int valor;


    public Variable(String s, TipusSub tipusSub, int procedure){
        this.idV = contadorVars;
        contadorVars++;
        this.nom = s;
        this.tipusSub = tipusSub;
        this.procedure = procedure;
    }

    public Variable(tipusSub tipus, int procedure){
        this.tipusSub = tipus;
        this.procedure = procedure;
    }

    //GETTERS
    public int getId(){
        return this.idV;
    }

    public String getNom(){
        return this.nom;
    }

    public TipusSub getTipusSub() {
        return this.tipusSub;
    }

    public int getProcedure(){
        return this.procedure;
    }

    public boolean getConstant(){
        return this.constant;
    }

    public int getValor(){
        return this.valor;
    }

    //SETTERS
    public void setNom(String n){
        this.nom=n;
    }

    public void setId(int id) {
        this.idV = id;
    }

    public void setTipusSub(TipusSub ts) {
        this.tipusSub = ts;
    }

    public void setProcedure(int p) {
        this.procedure = p;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setConstant(boolean constant) {
        this.constant = constant;
    }

    // Mètode toString
    @Override
    public String toString(){
        return "Nom: "+this.nom+"; Tipus: "+this.tipusSub+"; Procediment: "+this.procedure;
    }

}