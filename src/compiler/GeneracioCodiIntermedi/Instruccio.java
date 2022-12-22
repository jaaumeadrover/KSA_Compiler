package GeneracioCodiIntermedi;

public class Instruccio{

    private OperacionsCTA codi;
    private String operadorEsquerra;
    private String operadorDreta;
    private String desti;

    public Instruccio(OperacionsCTA oper, String o1, String o2, String d){
        this.codi=oper;
        this.operadorEsquerra=o1;
        this.operadorDreta=o2;
        this.desti=d;
    }

    //GETTERS
    public OperacionsCTA getOperacions() {
        return this.codi;
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
        this.codi=o;
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
        return "[" + codi.toString() + ", " + operadorEsquerra +
                ", " + operadorDreta + ", " + desti + "]";
    }
}