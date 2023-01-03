package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolIfStatement {

    private SymbolExpressioSimple expr;
    private SymbolStatementList statesIf;
    private SymbolStatementList statesElse;

    public SymbolIfStatement(SymbolExpressioSimple expr,SymbolStatementList statesIf){
        this.expr=expr;
        this.statesIf=statesIf;
    }
    public SymbolIfStatement(SymbolExpressioSimple expr,SymbolStatementList statesIf,SymbolStatementList statesElse){
        this.expr=expr;
        this.statesIf=statesIf;
        this.statesElse=statesElse;
    }
    
    public SymbolIfStatement(){
        
    }
    public void codiTresAdreces(codiTresAdreces codi) {
        if (this.statesElse==null){
            String condicio = this.expr.codiTresAdreces(codi);

            String etiqueta1 = codi.novaEtiqueta();
            String etiqueta2 = codi.novaEtiqueta();
            
            Operand o = new Operand("-1", OperandsCTA.boolea);
            Operand c = new Operand(condicio, OperandsCTA.boolea);

            codi.generar(TipusInstruccionsCTA.EQ, c, o, etiqueta1);
            codi.generar(TipusInstruccionsCTA.GOTO, null, null, etiqueta2);
            codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta1);

            this.statesIf.codiTresAdreces(codi);
            codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta2);

    }else {
            String condicio = this.expr.codiTresAdreces(codi);

            String etiqueta1 = codi.novaEtiqueta();//sentencias if
            String etiqueta2 = codi.novaEtiqueta();//sentencias else
            String etiqueta3 = codi.novaEtiqueta();//acaba

            Operand o = new Operand("-1", OperandsCTA.boolea);
            Operand c = new Operand(condicio, OperandsCTA.boolea);

            codi.generar(TipusInstruccionsCTA.EQ, c, o, etiqueta1);
            
            codi.generar(TipusInstruccionsCTA.GOTO, null, null, etiqueta2);
            //IF
            codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta1);

            this.statesIf.codiTresAdreces(codi);

            codi.generar(TipusInstruccionsCTA.GOTO, null, null, etiqueta3);

            //ELSE
            codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta2);

            this.statesElse.codiTresAdreces(codi);

            //ACABAMENT
            codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta3);
        }

    }
}