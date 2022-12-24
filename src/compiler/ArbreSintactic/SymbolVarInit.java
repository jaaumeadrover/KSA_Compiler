package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
public class SymbolVarInit  {

    private boolean isarray,isEmpty;
    private SymbolExpressioSimple expr;
    private SymbolArrayInit array;

  public SymbolVarInit(SymbolExpressioSimple expr, codiTresAdreces codi){
      this.isEmpty=false;
      this.isarray = false;
      this.expr = expr;
  }
    public SymbolVarInit(SymbolArrayInit array, codiTresAdreces codi){
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