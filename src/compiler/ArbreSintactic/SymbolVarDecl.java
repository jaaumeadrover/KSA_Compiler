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
        this.isEmpty = true;
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

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public String codiTresAdreces(codiTresAdreces codi) {
        String nom = this.id;
        String inicialitzacio = this.varInit.codiTresAdreces(codi);
        //fins que no declaram una variable no l'introduim en el codi de tres adreces
        if (this.varInit != null) {
            if (varInit.isIsarray()) {
                //procedure, dim?
                int dim=varInit.getArray().getInteger();
                int proc=codi.getTp().getNumProcActius();
                codi.addVariableArray(tipus, nom,proc,dim);
            }else {
                if (esConst) {
                    this.varInit.getExpr().getValor();
                    int n = 0;
                    if (varInit.getExpr().getValor().toString().equals("false")) {
                        n = 0;
                    } else if (varInit.getExpr().getValor().toString().equals("true")) {
                        n = -1;
                    } else {
                        n = Integer.parseInt(this.varInit.getExpr().getValor().toString());
                    }
                    nom = codi.addVariable(tipus, nom, n);
                } else {
                    nom = codi.addVariable(tipus, nom);
                }
            }
            if(inicialitzacio!=null) {
                Operand o = new Operand(inicialitzacio, OperandsCTA.variable);
                codi.generar(TipusInstruccionsCTA.COPIA, o, null,nom);
            }
        }
        return null;
    }

}
