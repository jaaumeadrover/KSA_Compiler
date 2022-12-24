package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSubProgramCall{
    private String id;
    private SymbolSubProgramContCall subProgramContCall;

    public SymbolSubProgramCall(String id, codiTresAdreces codi){
        this.id = id;
        System.out.println("soy un subprogramcall");
    }

    public SymbolSubProgramCall(SymbolSubProgramContCall subContCall, codiTresAdreces codi){
        this.subProgramContCall=subContCall;
        System.out.println("soy un subprogramcall");
    }
}