package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolType extends SymbolBase {
    TipusSub tipusSub;

    public SymbolType(TipusSub tipus, codiTresAdreces codi){
        System.out.println("soy un type de tipo"+tipus);
        this.tipusSub = tipus;
   }

   public TipusSub getTipusSub(){
        return tipusSub;
   }

 } 