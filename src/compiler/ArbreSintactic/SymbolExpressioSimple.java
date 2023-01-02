package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolExpressioSimple {
    private SymbolOperacio operacio;
    private SymbolValor valor;
    private TipusSub tsResultat;

    /*
    Constructor buit en cas d'errors
    */
    public SymbolExpressioSimple() {
    }

    /*
    Constructor que rep per paràemtres valor i operació
    */
    public SymbolExpressioSimple(SymbolValor val, SymbolOperacio oper) {
        this.valor = val;
        this.operacio = oper;
        
        this.tsResultat=valor.getTipusSub();
        
        if(oper.isEmpty()){
            this.tsResultat=valor.getTipusSub();
        }else{
            setTsResultat();
        }
    }
    //obtenir tipus resultat si operació no es buit
   private void setTsResultat(){
       if(this.tsResultat==this.operacio.getTipusSub()){
           if(this.operacio.getOperador().getTipusOperador()!='A'){
               this.tsResultat=TipusSub.BOOLEAN;
           }else{
               this.tsResultat=TipusSub.INT;
           }
       }else{
           this.tsResultat=null;
       }
   }
    public SymbolValor getValor(){
        return this.valor;
    }

    public SymbolOperacio getOperacio() {
        return operacio;
    }
    public TipusSub getTipusSubResultat(){
        return this.tsResultat;
    }
    
    @Override
    public String toString(){
        if(this.valor!=null){
             return this.valor.toString();
        }else{
            return "";
        }
    }

    /*
    Aquest mètode retorna un String que sirà utilitzat en altres classes com per exemple SymbolValor.
     */
    public String codiTresAdreces(codiTresAdreces codi){

        //Cas assignació [copy,b,null,a]
        if(this.operacio.isAssignacio()){
            String valor=this.valor.codiTresAdreces(codi);
            String oper=this.operacio.getExpr().codiTresAdreces(codi);
            Operand o1 = new Operand(oper, OperandsCTA.variable); // PER REVISAR

            codi.generar(TipusInstruccionsCTA.COPIA,o1,null,valor);
            return null;
        }else{
            //Cas no assignació


            String valor=this.valor.codiTresAdreces(codi);
            Operand val = new Operand(valor, OperandsCTA.variable); // PER REVISAR
            
            //si no es té operador i és un valor simple
            if(this.operacio.isEmpty()){
                //retornam la variable temporal
                return valor;
            }else{
                //es té un operador i s'ha de generar codi
                TipusInstruccionsCTA tipus=this.operacio.getOperador().codiTresAdreces(codi);
                String op2=this.operacio.getExpr().codiTresAdreces(codi);
                Operand o2 = new Operand(op2, OperandsCTA.variable); // PER REVISAR

                //crear variable temporal
                String temp = codi.addVariable(TipusSub.INT,"t"); // PER REVISAR

                codi.generar(tipus,val,o2,temp);
                return temp;

            }
        }
//
//        String value = this.valor.codiTresAdreces(codi);
//        if(operacio != null){
//            TipusInstruccionsCTA t = operacio.codiTresAdreces(codi);
//
//        }else{
//            return value;
//        }
    }



}
