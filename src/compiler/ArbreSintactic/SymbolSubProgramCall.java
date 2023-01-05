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
        //si hi ha paràmetres, generam el seu codi intermedi.
        if(subProgramContCall!=null){
            System.out.println("            HI HA PARÀMETRES");
            //feim respectiu codi de 3 adreces de parametres
            subProgramContCall.codiTresAdreces(codi,id,1);
            //CALL funcio
            //si no hi ha paràmetres
            if(this.retorna()){
                System.out.println("                            RETORNA");
                String temp = codi.addVariable(this.tipusSub,"t");
                Operand op = new Operand(this.id, OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,op ,null,temp);
                return temp;
            }else{
                System.out.println("                            NO RETORNA");
                Operand o = new Operand(this.id, OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,o,null,null);
            }
        }else{
            System.out.println("            NO HI HA PARÀMETRES");
            //si no hi ha paràmetres
            if(this.retorna()){
                String temp = codi.addVariable(this.tipusSub,"t");
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