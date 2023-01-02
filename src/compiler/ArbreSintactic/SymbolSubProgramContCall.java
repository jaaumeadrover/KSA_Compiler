package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSubProgramContCall{

    private String id;
    private SymbolValor expressioSimple;
    private SymbolSubProgramContCall subProgramContCall;

    public SymbolSubProgramContCall(String ID, SymbolValor expr){
        this.id=ID;
        this.expressioSimple=expr;
    }

    public String getID(){
        return this.id;
    }
    public SymbolSubProgramContCall(SymbolSubProgramContCall subContCall, SymbolValor expr){
        this.subProgramContCall=subContCall;
        this.expressioSimple=expr;
    }

    public void codiTresAdreces(codiTresAdreces codi){
        String id = this.expressioSimple.codiTresAdreces(codi);
//        Simbol s = ts.consulta(id);
//        if(s!=null){
//            if(s.getTipus().equals(Tipus.ARRAY)){
//                codi.genera(TipusInstruccionsCTA.PARAMC,null,null,id);
//            }else{
//                codi.genera(TipusInstruccionsCTA.PARAMS,null,null,id);
//            }
//        }else {
//            codi.genera(TipusInstruccionsCTA.PARAMS,null,null,id);
//        }
        if(this.subProgramContCall!=null){
            subProgramContCall.codiTresAdreces();
        }
    }
}