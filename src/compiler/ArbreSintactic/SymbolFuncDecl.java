package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;

public class SymbolFuncDecl extends SymbolBase {

 private String iden;
 private TipusSub t;
 private SymbolStatementList stat;
 private SymbolReturn rtn;

 private SymbolFuncCap funcCap;
 
  public SymbolFuncDecl(){
      
  System.out.println("soy un funcDecl");
 }
  
 public SymbolFuncDecl(TipusSub t, String iden, SymbolStatementList stat,
                       SymbolReturn rtn,SymbolFuncCap funcCap, codiTresAdreces codi){
   this.iden = iden;
   this.t = t;
   this.stat = stat;
   this.rtn = rtn;
   this.funcCap = funcCap;
  System.out.println("soy un funcDecl");
 }


 }