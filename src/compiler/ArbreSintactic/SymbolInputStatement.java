package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolInputStatement  {

    public SymbolInputStatement(){

    }
    public String codiTresAdreces(codiTresAdreces codi){
        String s=codi.addVariable(TipusSub.STRING,"t");
        codi.generar(TipusInstruccionsCTA.INPUT,null,null,s);
        return s;
    }
}