package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolVarDecl extends SymbolBase {

    private Tipus tipus;
    private boolean esConst;
    private  String id;
  public SymbolVarDecl(boolean esConst, Tipus t, String id){
        this.esConst=esConst;
        this.tipus = t;
        this.id = id;
        System.out.println("soy un vardecl");
  }
  private Tipus getTipus(){
      return tipus;
  }
    private boolean esConst(){
        return esConst;
    }

 }