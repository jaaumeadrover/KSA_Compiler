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

    public String codiTresAdreces(codiTresAdreces codi){
//        //generam codi Intermedi de un id --> és el mateix string de iden
//        TaulaProcediments.afegirProc(iden,type.getTipusSub)
//
//        //if(hiHaParametres()){
//            //cream llistaParam=arrayList<>()
//            //generam codiIntermedi de SymbolFuncCap(parametre llistaParam)
//            //TaulaProcediments.getProcediment(iden).setParametres(llistaParam)
//        //}
//        String etiqueta1=codi.novaEtiqueta(iden);
//        codi3Adreces.generar(TuipusInstruccionsCTA.SKIP,null,null,etiqueta1);
//        codi3Adreces.generar(TuipusInstruccionsCTA.PMB,null,null,iden);
//        if(stat!=null){
//            stat.codi3Adreces();
//        }
//        String rtn_str=rtn.codiIntermedi();
//        codi3Adreces.generar(Operador.RTN,null,null,rtn_str);
//
//        Codi3Adreces.TaulaProc.tancaProcediment();

        return null;

    }


 }