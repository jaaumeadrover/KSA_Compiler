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
       //String etiqueta1=codi.novaEtiqueta;
        //codi.generar(Operador.SKIP,null,null,etiqueta1)

        //String expr=this.expr.codiTresAdreces(codi) --> treim el codi 3A de la condicio del bucle
        //String etiqueta2=codi.novaEtiqueta;
        //codi.generar(Operador.IGUALES, expr, Integer.toString(-1), etiqueta2);
        //String etiqueta3=codi.novaEtiqueta;

        //codi.generar(Operador.GOTO, null, null, etiqueta3);
        //codi.generar(Operador.SKIP, null, null, etiqueta2);

        //this.state.codiTresAdreces(codi) --> treim el codi 3A de les sentencies dins del bucle

        // Salto para comprobar la condicion.
        //codi.generar(Operador.GOTO, null, null, etiqueta1);
        //ctd.generar(Operador.SKIP, null, null, etiqueta3);
        return null;


    }

 }