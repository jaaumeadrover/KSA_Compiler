package compiler.GeneracioCodiIntermedi;

public class Instruccio{

    private TipusInstruccionsCTA tipusInstruccio;
    private Operand param1;
    private Operand param2;
    private Operand desti;

    public Instruccio(TipusInstruccionsCTA tipus, String o1, String o2, String d){
        this.tipusInstruccio =tipus;
        this.param1 =o1;
        this.param2 =o2;
        this.desti=d;
    }

    //GETTERS
    public OperacionsCTA getOperacions() {
        return this.tipoInstruccio;
    }

    public String getOperadorEsquerra() {
        return this.param1;
    }

    public String getOperadorDreta() {
        return this.param2;
    }

    public String getDesti() {
        return this.desti;
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

    //Metode toStrong
    @Override
    public String toString() {
        return "[" + tipoInstruccio.toString() + ", " + param1 +
                ", " + param2 + ", " + desti + "]";
    }
}