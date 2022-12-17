package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolSubProgramCall extends SymbolBase {
    private String id;
    private SymbolSubProgramContCall subProgramContCall;

    public SymbolSubProgramCall(String id){
        this.id = id;
        System.out.println("soy un subprogramcall");
    }

    public SymbolSubProgramCall(SymbolSubProgramContCall subContCall){
        this.subProgramContCall=subContCall;
        System.out.println("soy un subprogramcall");
    }
}