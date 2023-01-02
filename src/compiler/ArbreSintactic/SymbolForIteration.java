package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolForIteration {

    private SymbolForInit init;
    private SymbolExpressioSimple expressio;
    private SymbolForPostExpression postexpression;
    private SymbolStatementList states;

    public SymbolForIteration(SymbolForInit forinit, SymbolExpressioSimple expr, SymbolForPostExpression forpost, SymbolStatementList states){
        this.init = forinit;
        this.expressio = expr;
        this.postexpression = forpost;
        this.states=states;
    }
    public SymbolForIteration(){
        
    }

    public void codiTresAdreces(codiTresAdreces codi){
        String condicio = this.expr.codiTresAdreces(codi);

        String etiqueta1 = codi.novaEtiqueta();//expresion
        String etiqueta2 = codi.novaEtiqueta();//sentencias
        String etiqueta3 = codi.novaEtiqueta();//acaba

        init.codiTresAdreces(codi);

        codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta1);
        String condicio = this.expr.codiTresAdreces(codi);

        codi.generar(TipusInstruccionsCTA.EQ, condicio, Integer.toString(-1), etiqueta2);
        codi.generar(TipusInstruccionsCTA.GOTO, null, null, etiqueta3);

        codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta2);
        states.codiTresAdreces(codi);

        postexpression.codiTresAdreces(codi);

        codi.generar(TipusInstruccionsCTA.GOTO, null, null, etiqueta1);
        codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta3);

    }
}