package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
import java.util.ArrayList;

public class SymbolFuncCap {

    private SymbolContCap contcap;
    private boolean hihaParam;

    public SymbolFuncCap(boolean param){
        this.hihaParam=param;
    }
    public SymbolFuncCap(SymbolContCap contcap,boolean param){
        this.contcap=contcap;
        this.hihaParam=param;
    }

    public boolean hihaParam() {
        return hihaParam;
    }

    public ArrayList<Parametre> codiTresAdreces(codiTresAdreces codi){
        if(hihaParam){
            // Hi ha paràmetres
            codi.StartParam();
            contcap.codiTresAdreces(codi);
            return codi.getParametres();
        }
        return null;
    }
}