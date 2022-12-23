package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolVarInit extends SymbolBase {

    private boolean isarray,isEmpty;
    private SymbolExpressioSimple expr;
    private SymbolArrayInit array;

  public SymbolVarInit(SymbolExpressioSimple expr){
      this.isEmpty=false;
      this.isarray = false;
      this.expr = expr;
  }
    public SymbolVarInit(SymbolArrayInit array){
        this.isEmpty=false;
        this.isarray = true;
        this.array = array;
    }
    public SymbolVarInit(boolean b){
        System.out.println("creat varinit");
        this.isEmpty=true;
    }
    public boolean isIsarray(){
      return isarray;
    }
    public boolean esBuit(){
        return isEmpty;
    }

 }