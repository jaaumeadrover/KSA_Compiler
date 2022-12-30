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

    public void codiTresAdreces(codi3A codi){
        if(contcap != null){
            contcap.codiTresAdreces(codi);
        }
    }
}