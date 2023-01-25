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
            //Afegim procediment amb parametres buits(per a tenir parametres amb numProc!=0)
            codi.getTp().afegirProc(new Procediment(iden,null,null));
            //Generam el codi de 3 adreces i el guardam a un arrayList
            ArrayList<Parametre> parametres = funcCap.codiTresAdreces(codi);
            //Afegir paràmetres al procediment
            codi.getTp().getProcediment(iden).setParametres(parametres);
            String etiqueta1=codi.novaEtiqueta(iden);
            codi.generar(TipusInstruccionsCTA.SKIP,null,null,etiqueta1);
            codi.generar(TipusInstruccionsCTA.PMB,null,null,iden);
            if(stat!=null){
                stat.codiTresAdreces(codi);
            }
            Operand o2 = new Operand(iden, OperandsCTA.procediment);
            codi.generar(TipusInstruccionsCTA.RTN,o2,null,null);
            codi.getTp().tancaProcediment();
        }else{

            codi.getTp().afegirProc(new Procediment(iden,null,null));
            String etiqueta1=codi.novaEtiqueta(iden);
            codi.generar(TipusInstruccionsCTA.SKIP,null,null,etiqueta1);
            codi.generar(TipusInstruccionsCTA.PMB,null,null,iden);
            if(stat!=null) {
                stat.codiTresAdreces(codi);
            }
            Operand o2 = new Operand(iden, OperandsCTA.procediment);
            codi.generar(TipusInstruccionsCTA.RTN,o2,null,null);
            codi.getTp().tancaProcediment();
        }
    }
}