package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
import java.util.ArrayList;

public class SymbolProcDecl  {

    private String iden;
    private SymbolStatementList stat;
    private SymbolFuncCap funcCap;

    public SymbolProcDecl(String iden, SymbolStatementList stat,
                          SymbolFuncCap funcCap){
        this.iden = iden;
        this.stat = stat;
        this.funcCap = funcCap;
    }

    public SymbolProcDecl() {

    }

    public void codiTresAdreces(codiTresAdreces codi){

        if(funcCap.hihaParam()){

            ArrayList<Parametre> parametres = funcCap.codiTresAdreces(codi);
            codi.getTp().afegirProc(new Procediment(iden,null,parametres));
            String etiqueta1=codi.novaEtiqueta(iden);
            codi.generar(TipusInstruccionsCTA.SKIP,null,null,etiqueta1);
            codi.generar(TipusInstruccionsCTA.PMB,null,null,iden);
            if(stat!=null){
                stat.codiTresAdreces(codi);
            }
            codi.getTp().tancaProcediment();
        }else{

            codi.getTp().afegirProc(new Procediment(iden,null,null));
            String etiqueta1=codi.novaEtiqueta(iden);
            codi.generar(TipusInstruccionsCTA.SKIP,null,null,etiqueta1);
            codi.generar(TipusInstruccionsCTA.PMB,null,null,iden);
            if(stat!=null) {
                stat.codiTresAdreces(codi);
            }
            codi.generar(TipusInstruccionsCTA.RTN,null,null,null);
            codi.getTp().tancaProcediment();
        }
    }
}