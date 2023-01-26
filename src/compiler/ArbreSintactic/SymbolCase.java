package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.GeneracioCodiIntermedi.*;
import compiler.Symbols.TaulaSimbols.TipusSub;

public class SymbolCase  {

    private SymbolExpressioSimple expressioSimple;
    private SymbolStatementList statementList;
    private TipusSub tipusSub;

    public SymbolCase(SymbolExpressioSimple expr, SymbolStatementList state){
        this.expressioSimple=expr;
        this.statementList=state;
    }

    public SymbolExpressioSimple getExpr() {
        return expressioSimple;
    }

    public void codiTresAdreces(codiTresAdreces codi, String r0, String ef){
        String eCont = codi.novaEtiqueta();
        String r1 = expressioSimple.codiTresAdreces(codi);
        Operand op1 = new Operand(r0, OperandsCTA.boolea);
        Operand op2 = new Operand(r1, OperandsCTA.boolea);
        //Si no son iguals botam al següent
        codi.generar(TipusInstruccionsCTA.NE,op1,op2, eCont);
        //Si n executam les sentencies
        statementList.codiTresAdreces(codi);
        codi.generar(TipusInstruccionsCTA.GOTO,null,null,ef);
        codi.generar(TipusInstruccionsCTA.SKIP,null,null,eCont);
    }
}