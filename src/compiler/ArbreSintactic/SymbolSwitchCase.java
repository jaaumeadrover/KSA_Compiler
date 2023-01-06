package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolSwitchCase {
    private SymbolExpressioSimple expressioSimple;
    private SymbolLCases lcases;
    private SymbolDefault defa;

    public SymbolSwitchCase(SymbolExpressioSimple expr, SymbolLCases lcases, SymbolDefault defa){
        this.expressioSimple = expr;
        this.lcases = lcases;
        this.defa=defa;
    }
    public SymbolSwitchCase(){

    }
    

    public void codiTresAdreces(codiTresAdreces codi){
        String evalua = expressioSimple.codiTresAdreces(codi);
        String ef=codi.novaEtiqueta();
        if(lcases!=null){
            lcases.codiTresAdreces(codi,evalua, ef);
        }
        if(!defa.esBuit()){
            defa.codiTresAdreces(codi);
        }
        codi.generar(TipusInstruccionsCTA.SKIP, null, null, ef);

    }


}