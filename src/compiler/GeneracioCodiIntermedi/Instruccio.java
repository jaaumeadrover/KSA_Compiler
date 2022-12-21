package GeneracioCodiIntermedi;

public class Instruccio{

    private OperacionsCTA operacions;
    private String operadorEsquerra;
    private String operadorDreta;
    private String desti;

    public Instruccio(OperacionsCTA oper, String o1, String o2, String d){
        this.operacions=oper;
        this.operadorEsquerra=o1;
        this.operadorDreta=o2;
        this.desti=d;
    }

    //GETTERS
    public OperacionsCTA getOperacions() {
        return this.operacions;
    }

    public String getOperadorEsquerra() {
        return this.operadorEsquerra;
    }

    public String getOperadorDreta() {
        return this.operadorDreta;
    }

    public String getDesti() {
        return this.desti;
    }

    //SETTERS
    public void setOperacions(OperacionsCTA o){
        this.operacions=o;
    }

    public void setOperadorEsquerra(String o){
        this.operadorEsquerra=o;
    }

    public void setOperadorDreta(String o){
        this.operadorDreta=o;
    }

    public void setDesti(String d){
        this.desti=d;
    }

    //Metode toStrong
    @Override
    public String toString() {
        return "[" + operacions.toString() + ", " + operadorEsquerra +
                ", " + operadorDreta + ", " + desti + "]";
    }
}