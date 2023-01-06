package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;
import compiler.GeneracioCodiIntermedi.*;
import java.util.ArrayList;

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

    public void codiTresAdreces(codiTresAdreces codi){
       //generam codi Intermedi de un id --> és el mateix string de iden
        if(funcCap.hihaParam()){
            ArrayList<Parametre> parametres = funcCap.codiTresAdreces(codi);
            codi.getTp().afegirProc(new Procediment(iden,t,parametres));
            String etiqueta1=codi.novaEtiqueta(iden);
            codi.generar(TipusInstruccionsCTA.SKIP,null,null,etiqueta1);
            codi.generar(TipusInstruccionsCTA.PMB,null,null,iden);
            if(stat!=null){
                stat.codiTresAdreces(codi);
            }
            String rtn_str=rtn.codiTresAdreces(codi);
            codi.generar(TipusInstruccionsCTA.RTN,null,null,rtn_str);

            codi.getTp().tancaProcediment();

        }else{
            codi.getTp().afegirProc(new Procediment(iden,t,null));
            String etiqueta1=codi.novaEtiqueta(iden);
            codi.generar(TipusInstruccionsCTA.SKIP,null,null,etiqueta1);
            codi.generar(TipusInstruccionsCTA.PMB,null,null,iden);
            if(stat!=null){
                stat.codiTresAdreces(codi);
            }
            String rtn_str=rtn.codiTresAdreces(codi);
            codi.generar(TipusInstruccionsCTA.RTN,null,null,rtn_str);

            codi.getTp().tancaProcediment();

        }


    }


 }