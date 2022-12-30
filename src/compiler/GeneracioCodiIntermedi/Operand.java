package compiler.GeneracioCodiIntermedi;
/*
    CLASSE: Operand
    AUTOR: ATA2
    FUNCIONALITAT: ens serveix per a crear objectes que representaran operands.
    DATA CREACIÓ: 26/12/2022
 */
public class Operand {
    private OperandsCTA tipus;
    private String operand;

    public Operand(String op, OperandsCTA t) {
        this.operand = op;
        this.tipus = t;
    }

    //GETTERS
    public OperandsCTA getTipus() {
        return this.tipus;
    }

    public String getOperand() {
        return this.operand;
    }

    //SETTERS

    public void setTipus(OperandsCTA op){
        this.tipus=op;
    }

    public void setOperand(String op){
        this.operand=op;
    }

    @Override
    public String toString() {
        return this.operand;
    }

}