package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolPrintStatement {
    private SymbolStringValue string;

    public SymbolPrintStatement(SymbolStringValue s){
        this.string=s;
    }

    public void codiTresAdreces(codiTresAdreces codi){
        codi.genera(OperacionsCTA.PRINT,s,null,null);
    }
}