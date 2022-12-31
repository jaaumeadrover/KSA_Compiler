package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSubProgramCall{
    private String id;
    private SymbolSubProgramContCall subProgramContCall;
    private boolean retorna;
    
    public SymbolSubProgramCall(){
        
    }
    public SymbolSubProgramCall(String id,boolean ret){

        this.id = id;
        this.retorna=ret
    }
    public String getId(){
        return this.id;
    }

    public SymbolSubProgramCall(SymbolSubProgramContCall subContCall){

        this.subProgramContCall=subContCall;
    }

    public String codiIntermedi(){
        subProgramContCall.codiIntermedi();
        if(retorna){
            String t = codi.tv.novaVariable();
            ctd.generar(Operador.CALL, t, null, i);

        }else{
            ctd.generar(Operador.CALL, null, null, i);
        }

    }
}