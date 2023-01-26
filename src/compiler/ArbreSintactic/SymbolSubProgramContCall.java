package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
import java.util.ArrayList;

public class SymbolSubProgramContCall {

    private String id;
    private SymbolValor value;
    private SymbolSubProgramContCall subProgramContCall;

    public SymbolSubProgramContCall(SymbolValor expr) {
        this.value = expr;

    }

    public String getID() {
        return this.id;
    }

    public SymbolSubProgramContCall(SymbolSubProgramContCall subContCall, SymbolValor expr) {
        this.subProgramContCall = subContCall;
        this.value = expr;

    }

    public void codiTresAdreces(codiTresAdreces codi, String id, int index) {
        Procediment proc = codi.getTp().getProcediment(id);
        ArrayList<Parametre> params = proc.getParametres();
        TipusInstruccionsCTA tipus;
        String valor;
        if (value.isIsarray()) {
            valor = this.value.codiTresAdreces(codi, true);
        }else{
            valor=this.value.codiTresAdreces(codi,false);
        }

            String idx = null;
            String iden = null;
            Operand o2 = null;
            //parametre al qual es fa referència
            Parametre param = params.get(params.size() - index);
            int pos1 = valor.indexOf('[');

            //si és un element de l'array---> paramentre compost
            if (pos1 != -1) {
                tipus = TipusInstruccionsCTA.PARAMC;
                //EXTREURE SUBSTRING
                int pos2 = valor.indexOf(']');
                idx = valor.substring(pos1 + 1, pos2 );
                iden = valor.substring(0, pos1);
                o2 = new Operand(idx, OperandsCTA.variable);
                System.out.println("                                    IDEN: "+iden+",valor: "+valor);
                iden=iden+"_"+codi.getTp().getNumProcActius();
                codi.generar(TipusInstruccionsCTA.PRODUCTE, o2, new Operand("4", OperandsCTA.enterLit), idx);
            } else {

                o2 = new Operand(valor, OperandsCTA.variable);
                tipus = TipusInstruccionsCTA.PARAMS;
            }

            codi.generar(tipus, o2, null, iden);

            //queden paràmetres
            if (this.subProgramContCall != null) {
                subProgramContCall.codiTresAdreces(codi, id, index++);
            }

        }
    }
