package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolArgType extends SymbolBase{

    private boolean esConst;
    public SymbolArgType(boolean esConst){
        this.esConst=esConst;
    }
    public boolean esConst(){
        return esConst;
    }
}