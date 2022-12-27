package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;


public class SymbolArray {

    private String ID;
    private SymbolExpressioSimple expressioSimple;

    public SymbolArray(String iden, SymbolExpressioSimple expr){
        this.ID=iden;
        this.expressioSimple=expr;
        //System.out.println("SOC UN SYMBOLARRAY");
    }

    public String getID() {
        return ID;
    }

    /*
    public String codiTresAdreces
     */
}