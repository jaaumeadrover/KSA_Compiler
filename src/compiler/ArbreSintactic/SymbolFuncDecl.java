package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolFuncDecl  {

 private String iden;
 private TipusSub t;
 private SymbolStatementList stat;
 private SymbolReturn rtn;

 private SymbolFuncCap funcCap;
 
  public SymbolFuncDecl(){

 }
  
 public SymbolFuncDecl(TipusSub t, String iden, SymbolStatementList stat,
                       SymbolReturn rtn,SymbolFuncCap funcCap){
   this.iden = iden;
   this.t = t;
   this.stat = stat;
   this.rtn = rtn;
   this.funcCap = funcCap;
 }

    public String codiIntermedi(){
        //generam codi Intermedi de un id --> és el mateix string de iden
        //TaulaProcediments.newProc(iden,type.getTipusSub) afegim procediment a la taula de procediments ->(nom,tipusRetorn)

        //if(hiHaParametres()){
            //cream llistaParam=arrayList<>()
            //generam codiIntermedi de SymbolFuncCap(parametre llistaParam)
            //TaulaProcediments.getProcediment(iden).setParametres(llistaParam)
        //}

        //codi3Adreces.generar(Operador.SKIP,null,null,iden);
        //codi3Adreces.generar(Operador.PMB,null,null,iden);
        //STATEMENTLIST.codi3Adreces()
        //codi3Adreces.generar(Operador.RTN,null,null,null);

        //Codi3Adreces.TaulaProc.tancaProcediment();

        return null;

    }


 }