package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolWhileStatement {
    private SymbolExpressioSimple expressio;
    private SymbolStatementList statementList;

   public SymbolWhileStatement(SymbolExpressioSimple expr, SymbolStatementList state){
        this.expressio = expr;
        this.statementList = state;

   }
    public SymbolWhileStatement(){

    }

    public String codiTresAdreces(codiTresAdreces codi){

        String etiqueta1=codi.novaEtiqueta();
        codi.generar(TipusInstruccionsCTA.SKIP,null,null,etiqueta1);

        String expr = this.expressio.codiTresAdreces(codi);
        String etiqueta2=codi.novaEtiqueta();
        
        Operand o = new Operand("-1", OperandsCTA.boolea);
        Operand e = new Operand(expr, OperandsCTA.boolea);
        codi.generar(TipusInstruccionsCTA.EQ, e, o, etiqueta2);
        
        String etiqueta3=codi.novaEtiqueta();

        codi.generar(TipusInstruccionsCTA.GOTO, null, null, etiqueta3);
        codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta2);

        this.statementList.codiTresAdreces(codi);

        //Comprovam la condicio
        codi.generar(TipusInstruccionsCTA.GOTO, null, null, etiqueta1);
        codi.generar(TipusInstruccionsCTA.SKIP, null, null, etiqueta3);
        return null;


    }

 }