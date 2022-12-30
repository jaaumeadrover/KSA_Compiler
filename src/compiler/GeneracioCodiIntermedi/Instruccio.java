package compiler.GeneracioCodiIntermedi;

public class Instruccio{

    private TipusInstruccionsCTA tipusInstruccio;
    private Operand param1;
    private Operand param2;
    private Operand desti;
    private boolean temporal;

    /*

     */
    public Instruccio(TipusInstruccionsCTA tipus, String o1, String o2, String d, boolean t){
        this.tipusInstruccio =tipus;
        this.param1 = new Operand(o1, );
        this.param2 =o2;
        this.desti=d;
        this.temporal=t;
    }

    //GETTERS
    public OperacionsCTA getOperacions() {
        return this.tipoInstruccio;
    }

    public Operand getOperadorEsquerra() {
        return this.param1;
    }

    public Operand getOperadorDreta() {
        return this.param2;
    }

    public Operand getDesti() {
        return this.desti;
    }

    public boolean getTemporal(){
        return this.temporal;
    }

    //SETTERS
    public void setOperacions(OperacionsCTA o){
        this.tipoInstruccio =o;
    }

    public void setOperadorEsquerra(String o){
        this.param1 =o;
    }

    public void setOperadorDreta(String o){
        this.param2 =o;
    }

    public void setDesti(String d){
        this.desti=d;
    }

    public void setTemporal(boolean t){
        this.temporal=t;
    }

    //Metode toStrong
    @Override
    public String toString() {
        return "[" + tipoInstruccio.toString() + ", " + param1 +
                ", " + param2 + ", " + desti + "]";
    }
}