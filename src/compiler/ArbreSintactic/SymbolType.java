package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolType extends SymbolBase {
    Tipus tipus;

    public SymbolType(Tipus tipus){
        System.out.println("soy un type de tipo"+tipus);
        this.tipus = tipus;
   }

   public Tipus getTipus(){
        return tipus;
   }

 }