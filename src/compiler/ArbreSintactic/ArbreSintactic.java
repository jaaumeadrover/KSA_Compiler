package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
/**
 AUTORS: ATA2
 CLASSE: ArbreSintactic
 FUNCIONALITAT:
 implementa tota la estructura de l'arbre
 DATA CREACIÓ: 10/12/2023
 */

public class ArbreSintactic {

    public SymbolProgram root;
    public static codiTresAdreces codi;

    // inicialitzam l'arrel de l'abre sintactic
    public void setRoot(SymbolProgram root){
        this.root = root;
    }


    // Mètode amb el que instanciem el codi de tres adreçes
    public codiTresAdreces generarCodiTresAdreces(){
        codi = new codiTresAdreces();
        root.codiTresAdreces(codi);
        return codi;
    }
}