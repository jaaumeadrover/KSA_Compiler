package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolArgType extends SymbolBase{

    private boolean esConst;
    public SymbolArgType(boolean esConst, codiTresAdreces codi){
        this.esConst=esConst;
    }
    public boolean esConst(){
        return esConst;
    }
}