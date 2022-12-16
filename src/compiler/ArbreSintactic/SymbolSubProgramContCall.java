package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolSubProgramContCall extends SymbolBase {

    private String id;
    private SymbolExpressioSimple expressioSimple;
    private SymbolSubProgramContCall subProgramContCall;

    public SymbolSubProgramContCall(String ID, SymbolExpressioSimple expr){
        this.id=ID;
        this.expressioSimple=expr;
        System.out.println("soy un subprogramcontcall");
    }

    public SymbolSubProgramContCall(SymbolSubProgramContCall subContCall){
        this.subProgramContCall=subContCall;
        System.out.println("soy un subprogramcontcall");
    }
}