package compiler.GeneracioCodiIntermedi;

public class Instruccio{

    private TipusInstruccionsCTA tipusInstruccio;
    private Operand param1;
    private Operand param2;
    private String desti;
    /*

     */
    public Instruccio(TipusInstruccionsCTA tipus, Operand o1, Operand o2, String d){
        this.tipusInstruccio =tipus;
        this.param1 = o1;
        this.param2 =o2;
        this.desti=d;
    }

    //GETTERS
    public TipusInstruccionsCTA getOperacions() {
        return this.tipusInstruccio;
    }

    public Operand getOperadorEsquerra() {
        return this.param1;
    }

    public Operand getOperadorDreta() {
        return this.param2;
    }

    public String getDesti() {
        return this.desti;
    }

    //SETTERS
    public void setOperacions(TipusInstruccionsCTA o){
        this.tipusInstruccio =o;
    }

    public void setOperadorEsquerra(Operand o){
        this.param1 =o;
    }

    public void setOperadorDreta(Operand o){
        this.param2 =o;
    }

    public void setDesti(String d){
        this.desti=d;
    }


    //Metode toString
    @Override
    public String toString() {
        return "[" + tipusInstruccio.toString() + ", " + param1 +
                ", " + param2 + ", " + desti + "]";
    }
}
