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

        String etiqueta1=codi.novaEtiqueta;
        codi.generar(Operador.SKIP,null,null,etiqueta1)

        String expr = this.expr.codiTresAdreces(codi)
        String etiqueta2=codi.novaEtiqueta;
        codi.generar(Operador.IGUALES, expr, Integer.toString(-1), etiqueta2);
        String etiqueta3=codi.novaEtiqueta;

        codi.generar(Operador.GOTO, null, null, etiqueta3);
        codi.generar(Operador.SKIP, null, null, etiqueta2);

        this.statementList.codiTresAdreces(codi)

        //Comprovam la condicio
        codi.generar(Operador.GOTO, null, null, etiqueta1);
        ctd.generar(Operador.SKIP, null, null, etiqueta3);
        return null;


    }

 }