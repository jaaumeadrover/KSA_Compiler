package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSubProgramCall{

    private String id;
    private SymbolSubProgramContCall subProgramContCall;
    private TipusSub tipusSub;
    private boolean retorna;
    
    public SymbolSubProgramCall(){
        
    }
    public SymbolSubProgramCall(String id,boolean ret,TipusSub t){

        this.id = id;
        this.retorna=ret;
        this.tipusSub=t;
    }
    public String getID(){
        if(id==null){
            return this.subProgramContCall.getID();
        }else{
            return this.id;
        }
    }

    public SymbolSubProgramCall(SymbolSubProgramContCall subContCall){

        this.tipusSub=t;
        this.subProgramContCall=subContCall;
    }

    public boolean retorna(){
        return retorna;
    }

    public String codiTresAdreces(){
        //si hi ha paràmetres, generam el seu codi intermedi
        if(subProgramContCall!=null){
            subProgramContCall.codiTresAdreces();
        }else{
            //si no hi ha paràmetres
            if(this.retorna()){

                String temp = codi.addVariable(this.tipusSub,"t");
                subProgramCall.codiTresAdreces();
                codi.generar(TipusInstruccionsCTA.CALL,this.subProgramCall.getId(),null,temp);

            }else{
                subProgramCall.codiTresAdreces();
                codi.generar(TipusInstruccionsCTA.CALL,this.subProgramCall.getId(),null,null);
            }
        }



    }
}