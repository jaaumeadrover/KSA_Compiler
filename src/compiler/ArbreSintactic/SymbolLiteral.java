package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolLiteral  {
    private String txt;
    private boolean esVar;
    public SymbolLiteral(String str){
        this.txt=str;
        esVar=false;
    }

    public SymbolLiteral(String str,boolean b){
        this.txt=str;
        esVar=true;
    }

    //Mètode per a retornar el text literal/variable
    public String toString(){
        return txt;
    }

    public boolean isVariable(){
        return esVar;
    }

    public String codiTresAdreces(codiTresAdreces codi){
        if(!esVar){
            //copiam string a variable temporal
            String s = codi.addVariable(TipusSub.STRING, "t");
            Operand o = new Operand(txt, OperandsCTA.stringLit);
            codi.generar(TipusInstruccionsCTA.COPIA,o,null, s);
            return s;
        }
        return txt+"_"+codi.getTp().getNumProcActius();
    }

}