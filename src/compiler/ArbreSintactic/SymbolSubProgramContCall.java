package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;
import java.util.ArrayList;

public class SymbolSubProgramContCall {

    private String id;
    private SymbolValor value;
    private SymbolSubProgramContCall subProgramContCall;
    private ArrayList<SymbolValor> valoresParam = new ArrayList<SymbolValor>();

    public SymbolSubProgramContCall(SymbolValor expr, ArrayList<SymbolValor> values) {
        this.value = expr;
        this.valoresParam = values;

    }

    public String getID() {
        return this.id;
    }

    public SymbolSubProgramContCall(SymbolSubProgramContCall subContCall, SymbolValor expr, ArrayList<SymbolValor> values) {

        this.subProgramContCall = subContCall;
        this.value = expr;
        this.valoresParam = values;

    }

    public ArrayList<SymbolValor> getValoresParam() {
        return valoresParam;
    }

    public void codiTresAdreces(codiTresAdreces codi, String id, int index) {
        Procediment proc = codi.getTp().getProcediment(id);
        //System.out.println("ID PROC: "+id);
        //System.out.println("TP: "+codi.getTp().toString());
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
                //valor = this.valor.codiTresAdreces(codi, true); //valor id[indx]
                //EXTREURE SUBSTRING
                int pos2 = valor.indexOf(']');
                idx = valor.substring(pos1 + 1, pos2 );
                iden = valor.substring(0, pos1);
                o2 = new Operand(idx, OperandsCTA.variable);
            } else {

                o2 = new Operand(valor, OperandsCTA.variable);
                tipus = TipusInstruccionsCTA.PARAMS;
                //iden = this.valor.codiTresAdreces(codi, false); //valor id[indx]
            }

            codi.generar(tipus, o2, null, iden);

            //queden paràmetres
            if (this.subProgramContCall != null) {
                subProgramContCall.codiTresAdreces(codi, id, index++);
            }

        }
    }
