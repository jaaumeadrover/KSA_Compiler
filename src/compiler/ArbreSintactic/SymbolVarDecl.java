package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolVarDecl {

    private TipusSub tipus;
    private boolean esConst;
    private String id;
    private SymbolVarInit varInit;

    public SymbolVarDecl() {
    }

    public SymbolVarDecl(boolean esConst, TipusSub t, String id, SymbolVarInit simbol) {
        this.esConst = esConst;
        this.tipus = t;
        this.id = id;
        this.varInit = simbol;

    }

    public TipusSub getTipusSub() {
        return tipus;
    }

    public boolean esConst() {
        return esConst;
    }

}
        
 
