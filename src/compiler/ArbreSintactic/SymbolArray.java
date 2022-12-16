package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolArray extends SymbolBase{

    private String ID;
    private SymbolExpressioSimple expressioSimple;

    public SymbolArray(String iden, SymbolExpressioSimple expr){
        this.ID=iden;
        this.expressioSimple=expr;
        System.out.println("SOC UN SYMBOLARRAY");
    }
}