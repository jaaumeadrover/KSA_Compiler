

package compiler.Symbols.TaulaSimbols;

//import ArbolSintactico.Tipo;
/**
 *
 * @author Ata2
 */
public class Simbol {

    private String idAutoIncrement;
    private TipusSub tiopusSub;
    private Tipus tipus;
    private int fila;
    private int nivell;

    public Simbol(String idAutoIncrement, TipusSub tipoSub, Tipus tipus, int fila, int nivell) {
        this.idAutoIncrement = idAutoIncrement;
        this.tiopusSub = tipoSub;
        this.tipus = tipus;
        this.fila = fila;
        this.nivell = nivell;
    }

    public boolean equals(Simbol simbol){
        return ( ( this.idAutoIncrement==simbol.getIdAutoIncrement() ) && ( this.nivell == simbol.getNivell() ) );
    }

    public int getFila(){
        return fila;
    }

    @Override
    public String toString(){
        return  tiopusSub.toString() + ": " + idAutoIncrement + ", tipo: " + tipus + ", nivel: " + nivell;
    }

    public String getIdAutoIncrement() {
        return idAutoIncrement;
    }

    public Tipus getTipus() {
        return tipus;
    }

    public int getNivell() {
        return nivell;
    }

    public TipusSub getTipusSub() {
        return tiopusSub;
    }
}