package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolVarDecl {

    private TipusSub tipus;
    private boolean esConst;
    private boolean isEmpty;
    private String id;
    private SymbolVarInit varInit;

    public SymbolVarDecl() {
        this.isEmpty=true;
    }

    public SymbolVarDecl(boolean esConst, TipusSub t, String id, SymbolVarInit simbol) {
        this.esConst = esConst;
        this.tipus = t;
        this.id = id;
        this.varInit = simbol;

    }

    public TipusSub getTipusSub() {
        return tipus;
    }

    public boolean esConst() {
        return esConst;
    }

    public boolean isEmpty(){
        return this.isEmpty;
    }


    public String codiTresAdreces(codiTresAdreces codi){
        String nom=this.id;
        //fins que no declaram una variable no l'introduim en el codi de tres adreces
        if (this.varInit != null){
            String inicialitzacio = this.varInit.codiTresAdreces(codi);
            Operand o = new Operand(inicialitzacio, OperandsCTA.variable);
            codi.generar(TipusInstruccionsCTA.COPIA, o, null, this.id);
        }
        return null;
   }

}
        
 
