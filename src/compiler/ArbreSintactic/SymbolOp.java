package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
/*
Classe SymbolOperació:
    ATRIBUTS:
    Símbols
    tipusOperador=['B','C','R']
 */
public class SymbolOp extends SymbolBase{
    private SymbolBoolOp boolOP;
    private SymbolAritOp aritOp;
    private SymbolRealOp realOP;
    private char tipusOperador;


    public SymbolOp(SymbolBoolOp oper, codiTresAdreces codi){
        this.boolOP=oper;
        System.out.println("soy un operador");
        this.tipusOperador='B';
    }

    public SymbolOp(SymbolAritOp oper, codiTresAdreces codi){
        this.aritOp=oper;
        System.out.println("soy un operador");
        this.tipusOperador='A';
    }

    public SymbolOp(SymbolRealOp oper, codiTresAdreces codi){
        this.realOP=oper;
        System.out.println("soy un operador");
        this.tipusOperador='R';
    }
    public char getTipusOperador(){
        return tipusOperador;
    }

}