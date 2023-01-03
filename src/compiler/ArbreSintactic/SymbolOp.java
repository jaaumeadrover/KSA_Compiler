package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
/*
Classe SymbolOperació:
    ATRIBUTS:
    Símbols
    tipusOperador=['B','C','R']
 */
public class SymbolOp {
    private SymbolBoolOp boolOP;
    private SymbolAritOp aritOP;
    private SymbolRealOp realOP;
    private char tipusOperador;


    public SymbolOp(SymbolBoolOp oper){
        this.boolOP=oper;
        this.tipusOperador='B';
    }

    public SymbolOp(SymbolAritOp oper){
        this.aritOP=oper;
        this.tipusOperador='A';
    }

    public SymbolOp(SymbolRealOp oper){
        this.realOP=oper;
        this.tipusOperador='R';
    }
    public char getTipusOperador(){
        return tipusOperador;
    }

    public TipusInstruccionsCTA codiTresAdreces(codiTresAdreces codi){
        if(boolOP != null){
            return boolOP.codiTresAdreces(codi);
        }else if(aritOP != null){
            return aritOP.codiTresAdreces(codi);
        }else if(realOP != null){
            return realOP.codiTresAdreces(codi);
        }
        return null;
    }

}