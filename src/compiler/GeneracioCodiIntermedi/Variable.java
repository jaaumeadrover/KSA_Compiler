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
    private int dimensio;
    private String value;

    /*
    Constructor per afegir variable simple
    */
    public Variable(String s, TipusSub tipusSub,  int procedure){
        this.idV = contadorVars;
        contadorVars++;
        this.nom = s;
        this.tipusSub = tipusSub;
        this.procedure = procedure;
        dimensio=1;
    }

    /*
    Constructor variables temporals 
    */
    public Variable(TipusSub tipus, int procedure){
        this.tipusSub = tipus;
        this.procedure = procedure;
    }
    
    /*
    Constructor per una constant, afegim valor
    */
    public Variable(String s, TipusSub tipusSub, int valor,int procedure){
        this.idV = contadorVars;
        contadorVars++;
        this.nom = s;
        this.tipusSub = tipusSub;
        this.procedure = procedure;
        this.valor=valor;
        this.constant=true;
        dimensio=1;
    }
    
    /*
    Constructor per un array, afegim dimensió
    */
    public Variable( TipusSub tipusSub,String s, int procedure,int dimensio){
        this.idV = contadorVars;
        contadorVars++;
        this.nom = s;
        this.tipusSub = tipusSub;
        this.procedure = procedure;
        this.dimensio=dimensio;
    }
    
    //Constructor per a strings
    public Variable(String s, TipusSub tipusSub,int procedure,String value){
        this.idV = contadorVars;
        contadorVars++;
        this.nom = s;
        this.tipusSub = tipusSub;
        this.procedure = procedure;
        this.value=value;
        this.constant=true;
        dimensio=1;
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
    public int getDimensio(){return this.dimensio;}

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
        return this.nom;
    }

}