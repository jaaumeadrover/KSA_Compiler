package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolFuncCap {

    private SymbolContCap contcap;
    public SymbolFuncCap(){

        //System.out.println("soy un SymbolFuncCap");
    }
    public SymbolFuncCap(SymbolContCap contcap){
        this.contcap=contcap;
        //System.out.println("soy un SymbolFuncCap");
    }

    public void codiTresAdreces(codiTresAdreces codi){
        if(contcap != null){
            // Hi ha paràmetres
            contcap.codiTresAdreces(codi);
        }else{
            //d = consulta(ts, id.id)
            // Cas procediment sense paràmetres
            //codi.generar(TipusInstruccionsCTA.CALL,null,null,null);
        }
    }
}