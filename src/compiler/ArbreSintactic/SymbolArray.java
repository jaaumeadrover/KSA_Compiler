package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;


public class SymbolArray {

    private String ID;
    private SymbolExpressioSimple expressioSimple;

    public SymbolArray(String iden, SymbolExpressioSimple expr, codiTresAdreces codi){
        this.ID=iden;
        this.expressioSimple=expr;
        System.out.println("SOC UN SYMBOLARRAY");
    }
}