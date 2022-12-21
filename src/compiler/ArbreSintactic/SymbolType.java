package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolType extends SymbolBase {
    Tipus tipusSub;

    public SymbolType(TipusSub tipus){
        System.out.println("soy un type de tipo"+tipus);
        this.tipus = tipus;
   }

   public Tipus getTipusSub(){
        return tipus;
   }

 }