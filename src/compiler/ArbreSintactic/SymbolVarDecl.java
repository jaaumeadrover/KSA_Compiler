package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolVarDecl extends SymbolBase {

    private Tipus tipus;
    private boolean esConst;
    private  String id;
    private SymbolVarInit simbol;
    
  public SymbolVarDecl(boolean esConst, Tipus t, String id, SymbolVarInit simbol){
        this.esConst=esConst;
        this.tipus = t;
        this.id = id;
        this.simbol = simbol;
        System.out.println("soy un vardecl");
  }
  public Tipus getTipus(){
      return tipus;
  }
  
  public boolean esConst(){
        return esConst;
  }

 }