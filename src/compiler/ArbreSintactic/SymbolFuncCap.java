package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolFuncCap extends SymbolBase{

    private SymbolContCap contcap
    public SymbolFuncCap(){

        System.out.println("soy un SymbolFuncCap");
    }
    public SymbolFuncCap(SymbolContCap contcap){
        this.contcap=contcap;
        System.out.println("soy un SymbolFuncCap");
    }
}