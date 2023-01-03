package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolPrintStatement {
    private SymbolLiteral s;

    public SymbolPrintStatement(SymbolLiteral s){
        this.s=s;
    }

    public void codiTresAdreces(codiTresAdreces codi){
        Operand o = new Operand(s.toString(), OperandsCTA.stringLit);
        codi.generar(TipusInstruccionsCTA.PRINT,o,null,null);
    }
}