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
    private SymbolAritOp aritOp;
    private SymbolRealOp realOP;
    private char tipusOperador;


    public SymbolOp(SymbolBoolOp oper){
        this.boolOP=oper;
        System.out.println("soy un operador");
        this.tipusOperador='B';
    }

    public SymbolOp(SymbolAritOp oper){
        this.aritOp=oper;
        System.out.println("soy un operador");
        this.tipusOperador='A';
    }

    public SymbolOp(SymbolRealOp oper){
        this.realOP=oper;
        System.out.println("soy un operador");
        this.tipusOperador='R';
    }
    public char getTipusOperador(){
        return tipusOperador;
    }

}