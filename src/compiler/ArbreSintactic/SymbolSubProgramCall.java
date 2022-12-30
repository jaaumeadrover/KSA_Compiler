package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSubProgramCall{
    private String id;
    private SymbolSubProgramContCall subProgramContCall;
    
    public SymbolSubProgramCall(){
        
    }
    public SymbolSubProgramCall(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }

    public SymbolSubProgramCall(SymbolSubProgramContCall subContCall){
        this.subProgramContCall=subContCall;
    }
}