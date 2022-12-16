package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolFuncDecl extends SymbolBase {

 private String iden;
 private Tipus t;
 private SymbolStatementList stat;
 private SymbolReturn rtn;

 private SymbolFuncCap funcCap;
 public SymbolFuncDecl(Tipus t, String iden, SymbolStatementList stat,
                       SymbolReturn rtn,SymbolFuncCap funcCap){
   this.iden = iden;
   this.t = t;
   this.stat = stat;
   this.rtn = rtn;
   this.funcCap = funcCap;
  System.out.println("soy un funcDecl");
 }


 }