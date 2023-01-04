package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
import java.util.ArrayList;

public class SymbolFuncCap {

    private SymbolContCap contcap;
    private boolean hihaParam;

    public SymbolFuncCap(boolean param){
        this.hihaParam=param;
        //System.out.println("soy un SymbolFuncCap");
    }
    public SymbolFuncCap(SymbolContCap contcap,boolean param){
        this.contcap=contcap;
        this.hihaParam=param;
        //System.out.println("soy un SymbolFuncCap");
    }

    public boolean hihaParam() {
        return hihaParam;
    }

    public ArrayList<Parametre> codiTresAdreces(codiTresAdreces codi){
        if(hihaParam){
            // Hi ha paràmetres
            contcap.codiTresAdreces(codi);
            return codi.getParametres();
        }
        return null;
    }
}