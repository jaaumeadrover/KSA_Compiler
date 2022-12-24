package compiler.ArbreSintactic;


public class ArbreSintactic {

    public SymbolProgram root;
    public static codiTresAdreces codi;

    // inicialitzam l'arrel de l'abre sintactic
    pyblic void setRoot(SymbolProgram root){
        this.root = root;
    }


    // Mètode amb el que instanciem el codi de tres adreçes
    public codiTresAdreces generarCodiTresAdreces(){
        codi = new codiTresAdreces();
        root.codiTresAdreces();
        return codi;
    }
}