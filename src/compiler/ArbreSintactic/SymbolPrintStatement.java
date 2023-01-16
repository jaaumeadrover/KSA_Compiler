package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolPrintStatement {
    private SymbolLiteral s;

    public SymbolPrintStatement(SymbolLiteral s){
        this.s=s;
    }

    public SymbolPrintStatement(){

    }
    public void codiTresAdreces(codiTresAdreces codi){
        //s pot ser variable temporal o variable
        Operand o = new Operand(s.codiTresAdreces(codi), OperandsCTA.variable);

        codi.generar(TipusInstruccionsCTA.PRINT,o,null,null);
    }
}