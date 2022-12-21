package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolVarInit extends SymbolBase {

    private boolean isarray;
    private SymbolExpressioSimple expr;
    private SymbolArrayInit array;

  public SymbolVarInit(SymbolExpressioSimple expr){

      this.isarray = false;
      this.expr = expr;
  }
    public SymbolVarInit(SymbolArrayInit array){

        this.isarray = true;
        this.array = array;
    }
    public SymbolVarInit(){

    }
    public boolean isIsarray(){
      return isarray;
    }

 }