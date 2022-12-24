package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolFuncCap extends SymbolBase{

    private SymbolContCap contcap;
    public SymbolFuncCap(codiTresAdreces codi){

        System.out.println("soy un SymbolFuncCap");
    }
    public SymbolFuncCap(SymbolContCap contcap, codiTresAdreces codi){
        this.contcap=contcap;
        System.out.println("soy un SymbolFuncCap");
    }
}