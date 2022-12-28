package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolInputStatement  {

    public SymbolInputStatement(){

    }

    public String codiTresAdreces(codiTresAdreces codi){
        String s=codi.novaVariable(TipusSub.NULL,null);
        codi.genera(OperacionsCTA.INPUT,null,null,s);
        return s;
    }
}