package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;


public class SymbolArray {

    private String ID;
    private SymbolExpressioSimple expressioSimple;

    public SymbolArray(String iden, SymbolExpressioSimple expr){
        this.ID=iden;
        this.expressioSimple=expr;
        //System.out.println("SOC UN SYMBOLARRAY");
    }

    public String getID() {
        return ID;
    }

    /*
    public String codiTresAdreces
     */

    public String codiTresAdreces(codiTresAdreces codi){
        //generam codi3Adreces de expressio simple i emmagatzemam la variable temporal
        String temp=expressioSimple.codiTresAdreces(codi);
        String temp2=codi.addVariable(TipusSub.INT, "t");
        codi.generar(TipusInstruccionsCTA.COPIA,new Operand(temp,OperandsCTA.variable),null,temp2);
        return temp2;
    }
}