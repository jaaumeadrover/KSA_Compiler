package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
public class SymbolVarInit  {

    private boolean isarray,isEmpty;
    private SymbolExpressioSimple expr;
    private SymbolArrayInit arrayinit;

  public SymbolVarInit(SymbolExpressioSimple expr){

      this.isEmpty=false;
      this.isarray = false;
      this.expr = expr;
  }
    public SymbolVarInit(SymbolArrayInit arrayinit){
        this.isEmpty=false;
        this.isarray = true;
        this.arrayinit = arrayinit;
    }
    public SymbolVarInit(boolean b){

        this.isEmpty=true;
    }
    public boolean isIsarray(){
      return isarray;
    }
    public boolean esBuit(){
        return isEmpty;
    }

    public SymbolExpressioSimple getExpr() {
        return expr;
    }

    public SymbolArrayInit getArray() {
        return arrayinit;
    }


    public String codiTresAdreces(codiTresAdreces codi){
      // Un inicialitzaci&oacute; de variable pot ser un array o una Expressi&oacute; Simple
      if (isarray == true){
         return this.arrayinit.codiTresAdreces(codi);
      }else if (isEmpty == true){
         return null;
      }else{
         return this.expr.codiTresAdreces(codi);
      }
    }


}