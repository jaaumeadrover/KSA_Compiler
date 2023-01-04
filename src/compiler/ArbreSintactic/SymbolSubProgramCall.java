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

    public SymbolSubProgramCall(SymbolSubProgramContCall subContCall,String s){
        this.subProgramContCall=subContCall;
        this.id=s;
    }

    public boolean retorna(){
        return retorna;
    }

    public String codiTresAdreces(codiTresAdreces codi){
        //si hi ha paràmetres, generam el seu codi intermedi
        if(subProgramContCall!=null){
            //feim respectiu codi de 3 adreces de parametres
            subProgramContCall.codiTresAdreces(codi,id);
            //CALL funcio
        }else{
            //si no hi ha paràmetres
            if(this.retorna()){
                String temp = codi.addVariable(this.tipusSub,"t");
                Operand op = new Operand(this.subProgramContCall.getID(), OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,op ,null,temp);
                return temp;
            }else{

                Operand o = new Operand(this.subProgramContCall.getID(), OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,o,null,null);
            }
        }
        return null;
    }
}