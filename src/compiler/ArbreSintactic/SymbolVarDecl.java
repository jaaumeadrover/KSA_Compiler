package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;

public class SymbolVarDecl extends SymbolBase {

    private TipusSub tipus;
    private boolean esConst;
    private  String id;
    private SymbolVarInit simbol;
    
  public SymbolVarDecl(boolean esConst, TipusSub t, String id, SymbolVarInit simbol){
        this.esConst=esConst;
        this.tipus = t;
        this.id = id;
        this.simbol = simbol;
        System.out.println("soy un vardecl");
  }
  public TipusSub getTipusSub(){
      return tipus;
  }
  
  public boolean esConst(){
        return esConst;
  }

 }