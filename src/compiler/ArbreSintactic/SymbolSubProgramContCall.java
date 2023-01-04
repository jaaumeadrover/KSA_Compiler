package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

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

    public void codiTresAdreces(codiTresAdreces codi, String id){

        //queden paràmetres
        if(this.subProgramContCall!=null){
            String t = valor.codiTresAdreces(codi,false);
            if(valor.isComplex()){
                codi.gener
            }else{
                codi.genera
            }
            subProgramContCall.codiTresAdreces();
        }else{
            //si no hi ha paràmetres
            if(this.retorna()){
                String temp = codi.addVariable(this.tipusSub,"t");
                Operand op = new Operand(this.subProgramContCall.getID(), OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,op ,null,temp);
                return temp;
            }else{

                Operand o = new Operand(this.subProgramCall.getID(), OperandsCTA.procediment);
                codi.generar(TipusInstruccionsCTA.CALL,o,null,null);
            }

        }


    }
}