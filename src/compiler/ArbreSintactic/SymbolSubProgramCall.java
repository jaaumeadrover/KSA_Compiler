package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSubProgramCall{
    private String id;
    private SymbolSubProgramContCall subProgramContCall;

    public SymbolSubProgramCall(String id){
        this.id = id;
    }

    public SymbolSubProgramCall(SymbolSubProgramContCall subContCall){
        this.subProgramContCall=subContCall;
    }
}