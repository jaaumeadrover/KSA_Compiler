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
            return this.id;
    }

    /*
    CONSTRUCTOR EL QUAL REP PARÀMETRES I UN STRING S, A MÉS D'UN BOOLEAN QUE ENS INDICA SI RETORNA O NO.
     */
    public SymbolSubProgramCall(SymbolSubProgramContCall subContCall,String s,boolean b,TipusSub t){
        this.subProgramContCall=subContCall;
        this.id=s;
        this.retorna=b;
        this.tipusSub=t;
    }

    public boolean retorna(){
        return retorna;
    }

    public String codiTresAdreces(codiTresAdreces codi){
        String temp=codi.addVariable(this.tipusSub,"t");
        //si hi ha paràmetres, generam el seu codi intermedi.
        if(subProgramContCall!=null){
            //feim respectiu codi de 3 adreces de parametres
            subProgramContCall.codiTresAdreces(codi,id,1);
            //CALL funcio
            //si no hi ha paràmetres
            if(this.retorna()){
                Operand op = new Operand(this.id, OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,op ,null,temp);
                return temp;
            }else{
                Operand o = new Operand(this.id, OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,o,null,null);
            }
        }else{
            //si no hi ha paràmetres
            //si retorna
            if(this.retorna()){
                Operand op = new Operand(this.id, OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,op ,null,temp);
                return temp;
            }else{
                Operand o = new Operand(this.id, OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,o,null,null);
            }
        }
        return null;
    }
}