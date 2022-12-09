

package TablaSimbolos;

import ArbolSintactico.Tipo;
/**
 *
 * @author Ata2
 */
public class Simbol {

    private String idAutoIncrement;
    private Tipo tipus;
    private int nivell;
    private TipoSub tiopusSub;
    private int fila;

    public Simbolo(String id, Tipo tipo, int nivell, TipoSub tipoSub, int fila) {
        this.idAutoIncrement = id;
        this.tipus = tipo;
        this.nivell = nivell;
        this.tiopusSub = tipoSub;
        this.fila = fila;
    }

    public boolean equals(Simbolo s){
        return this.idAutoIncrement.equals(s.getId()) && this.nivell == s.getNivel();
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

    public Tipo getTipus() {
        return tipus;
    }

    public int getNivell() {
        return nivell;
    }

    public TipoSub getTiopusSub() {
        return tiopusSub;
    }
}