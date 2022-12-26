package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolArgType {

    private boolean esConst;
    public SymbolArgType(boolean esConst){
        this.esConst=esConst;
    }
    public boolean esConst(){
        return esConst;
    }
}