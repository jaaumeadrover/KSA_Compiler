package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolVarInit extends SymbolBase {

    private boolean isarray;
    private SymbolExpressioSimple expr;
    private SymbolInitArray array;
  public SymbolVarInit(SymbolExpressioSimple expr){

      this.isarray = false;
      this.expr = expr;
  }
    public SymbolVarInit(SymbolInitArray array){

        this.isarray = true;
        this.array = array;
    }
    public SymbolVarInit(){

    }

 }