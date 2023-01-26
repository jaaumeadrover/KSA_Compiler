package compiler.ArbreSintactic;

import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolValor {

    private String iden;
    private SymbolArray array;
    private int inte;
    private boolean b;
    private int paramc =0;
    private boolean esBuit;
    private boolean isarray=false;
    private SymbolSubProgramCall subProgramCall;
    private SymbolExpressioSimple exprSimple;
    private int index;
    private TipusSub tipusSub;
    private SymbolInputStatement statement;
    private String str;


    public boolean isIsarray() {
        return isarray;
    }

    /*
        Cas valor equival a una variable i cal consultar el id en la taula de símbols
         */
    public SymbolValor(String id, TipusSub tipusSub) {
        this.iden = id;
        this.tipusSub = tipusSub;
        this.index = 1;
    }

    /*
    Constructor en el cas de tenir un string
     */
    public SymbolValor(String str,boolean b){
        this.str=str;
        this.tipusSub = TipusSub.STRING;
        this.index = 9;
    }
    /*
    Cas valor en el qual tenim un array
     */
    public SymbolValor(SymbolArray arr, TipusSub tipusSub) {
        this.array = arr;
        this.iden = arr.getID();
        this.tipusSub = tipusSub;
        this.isarray=true;
        this.b=b;
        this.index = 2;
    }

    /*
    Cas valor el qual és un integer directe.
     */
    public SymbolValor(Object integer) {
        inte = Integer.parseInt(integer.toString()); //passam text a int
        iden = integer.toString();
        tipusSub = TipusSub.INT;
        this.index = 3;
    }

    /*
    Cas valor equival a un booleam
     */
    public SymbolValor(String b) {
        if (b.equals("true")) {
            this.b = true;
            this.iden = "true";
        } else {
            this.b = false;
            this.iden = "false";
        }
        this.tipusSub = TipusSub.BOOLEAN;
        this.index = 4;
    }

    /*
    Cas valor el qual equival a una cridada del subprograma.
     */
    public SymbolValor(SymbolSubProgramCall subProgCall, TipusSub tipus) {
        this.subProgramCall = subProgCall;
        this.tipusSub = tipus;
        this.index = 5;
    }

    /*
    Producció on es posa not (ExprSimple)
     */
    public SymbolValor(SymbolExpressioSimple expr, boolean b) {
        this.exprSimple = expr;
        this.tipusSub = TipusSub.BOOLEAN;
        if (this.exprSimple.getTipusSubResultat() == this.tipusSub) {
            this.tipusSub = TipusSub.BOOLEAN;
        } else {
            this.tipusSub = TipusSub.NULL;
        }
        this.index = 6;

    }

    //Constructor en la produccio lparen exprSimple rparen
    public SymbolValor(SymbolExpressioSimple expr) {
        this.exprSimple = expr;
        this.tipusSub = expr.getTipusSubResultat();
        this.index = 7;
    }

    public SymbolValor(SymbolInputStatement s) {
        this.statement = s;
        this.tipusSub = TipusSub.STRING;
        this.index = 8;
    }

    public SymbolValor() {
        this.esBuit = true;
        this.index = 10;
    }

    public int getValorInt() {
        return inte;
    }

    public SymbolSubProgramCall getSubProgCall() {
        return this.subProgramCall;
    }

    public TipusSub getTipusSub() {
        return tipusSub;
    }

    public String getIden() {
        return iden;
    }

    public boolean esBuit() {
        return this.esBuit;
    }
    
    public Tipus getTipus(){
        if(array != null){
            return Tipus.ARRAY;
        }else{
            return Tipus.VAR;
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += "id: " + iden + ",valor: " + "";

        return iden;
    }

    public boolean isComplex() {
        return this.index == 2;
    }

    public boolean isArrayElement() {
        return this.array != null;
    }

    public int getIndex() {
        return this.index;
    }
    
    //Si paràmetre b és true, retornam el valor sense variable temporal
    public String codiTresAdreces(codiTresAdreces codi, boolean b) {
        switch (index) {
            case 1:
                //CAS VARIABLE


                return iden+"_"+codi.getTp().getNumProcActius();


            case 2:
                //GAS ARRAYS
                //variable_temporal=(x-1)*(MidaTipusArray);
                //NOVA VARIABLE TEMPORAL
                String temp2 = codi.addVariable(TipusSub.INT, "t");
                String temp = this.array.codiTresAdreces(codi);
                if (b == true) {
                    //retornam iden[index]
                    return iden + "[" + temp + "]";
                }
                Operand o = new Operand(temp, OperandsCTA.variable);
                //restam 1 a l'index
                //TEMP RETORNA VARIABLE i -> hauria d ser variable TEMPORAL
                //multiplicam l'índex
                codi.generar(TipusInstruccionsCTA.PRODUCTE, o, new Operand("4", OperandsCTA.enterLit), temp);
                //cas indexat  ->  s'utilitza per assignar valor a altra variable.
                //temp2=iden[temp]
                codi.generar(TipusInstruccionsCTA.INDVAL, new Operand(this.iden+"_"+codi.getTp().getNumProcActius(), OperandsCTA.variable), o, temp2);
                return temp2;//retornam variable temporal

            case 3:
                //generar codi de un integer
                //guardar en variable temporal int
                String temp1 = codi.addVariable(TipusSub.INT, "t");

                Operand enter = new Operand(iden, OperandsCTA.enterLit);

                codi.generar(TipusInstruccionsCTA.COPIA, enter, null, temp1);
                if(b){
                    return String.valueOf(inte);
                }
                return temp1;
            case 4:
                //generar codi de un boolean
                String val = "";
                if (this.b) {
                    val = String.valueOf(-1);

                } else {
                    val = String.valueOf(0);

                }
                String temp5 = codi.addVariable(TipusSub.BOOLEAN, "t");
                Operand o3 = new Operand(val, OperandsCTA.boolea);
                codi.generar(TipusInstruccionsCTA.COPIA, o3, null, temp5);
                return temp5;
            case 5:
                //generar crida a subprogram
                return subProgramCall.codiTresAdreces(codi);
            case 6:
                //generar instruccio not del boolean
                String operand1 = this.exprSimple.codiTresAdreces(codi);
                Operand o5 = new Operand(operand1, OperandsCTA.boolea);
                String s = codi.addVariable(TipusSub.BOOLEAN, "t");
                codi.generar(TipusInstruccionsCTA.NOT, o5, null, s);
                return s;

            //
            case 7:
                //generar codi de una expressió simple
                String valorExpr = this.exprSimple.codiTresAdreces(codi);
                return valorExpr;
            case 8:
                // genram codi de
                String t = this.statement.codiTresAdreces(codi);
                return t;
            //CAS STRING
            case 9:
                return this.str;
            //constructor buit
            case 10:

        }
        return null;
    }
}
