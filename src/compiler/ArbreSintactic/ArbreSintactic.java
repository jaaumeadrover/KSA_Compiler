package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;


public class ArbreSintactic {

    public SymbolProgram root;
    public static codi3A codi;

    // inicialitzam l'arrel de l'abre sintactic
    public void setRoot(SymbolProgram root){
        this.root = root;
    }


    // Mètode amb el que instanciem el codi de tres adreçes
    public codi3A generarCodiTresAdreces(){
        codi = new codi3A();
        //root.codiTresAdreces();
        return codi;
    }
}