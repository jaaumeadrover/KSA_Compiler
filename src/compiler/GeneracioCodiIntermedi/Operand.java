



/*
    CLASSE: Operand
    AUTOR: ATA2
    FUNCIONALITAT: ens serveix per a crear objectes que representaran operands.
    DATA CREACIÓ: 26/12/2022
 */
public class Operand {

    public OperandsCTA tipus;
    public String operand;

    public Operand(String s, OperandsCTA t) {
        this.operando = s;
        this.tipus = t;
    }

    /*
    public Operand(String s, TiposOperandoC3A t, boolean temp) {
        this.operand = s;
        this.type = t;
    }
    */

    @Override
    public String toString() {
//        String s = "OP{"+operando+" tipo: "+type.toString()+"}";
//        return s;
        return operando;
    }

}