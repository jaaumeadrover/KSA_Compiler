package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
import java.util.ArrayList;

public class SymbolSubProgramContCall{

    private String id;
    private SymbolValor valor;
    private SymbolSubProgramContCall subProgramContCall;

    public SymbolSubProgramContCall(SymbolValor expr){
        this.valor=expr;
    }

    public String getID(){
        return this.id;
    }

    public SymbolSubProgramContCall(SymbolSubProgramContCall subContCall, SymbolValor expr){
        this.subProgramContCall=subContCall;
        this.valor=expr;
    }

    public void codiTresAdreces(codiTresAdreces codi, String id,int index){
        Procediment proc=codi.getTp().getProcediment(id);
        ArrayList<Parametre> params= proc.getParametres();
        TipusInstruccionsCTA tipus;
        String valor=null;
        String idx=null;
        String iden=null;
        Operand o2=null;
        //parametre al qual es fa referència
        Parametre param =params.get(params.size()-index);
        int pos1=valor.indexOf('[');

        //si és un element de l'array---> paramentre compost
        if(pos1!=-1){
            tipus=TipusInstruccionsCTA.PARAMC;
            valor=this.valor.codiTresAdreces(codi,true); //valor id[indx]
            //EXTREURE SUBSTRING
            int pos2=valor.indexOf(']');
            idx=valor.substring(pos1+1,pos2+1);
            iden=valor.substring(0,pos1);
            o2 = new Operand(idx, OperandsCTA.variable);
        }else{
            tipus=TipusInstruccionsCTA.PARAMS;
            valor=this.valor.codiTresAdreces(codi,false); //valor id[indx]
        }

        codi.generar(tipus,o2,null,iden);
        
        //queden paràmetres
        if(this.subProgramContCall!=null){
            subProgramContCall.codiTresAdreces(codi, id, index++);
        }


    }
}