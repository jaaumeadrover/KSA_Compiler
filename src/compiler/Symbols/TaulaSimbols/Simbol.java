

package compiler.Symbols.TaulaSimbols;

//import ArbolSintactico.Tipo;

import java.util.ArrayList;

/**
 *
 * @author Ata2
 */
public class Simbol {

    private String id;
    private TipusSub tiopusSub;
    private Tipus tipus;
    private int fila;
    private int nivell;
    private int dimensionalitat;


    public Simbol(String idAutoIncrement, TipusSub tipoSub, Tipus tipus, int fila, int nivell,int dimensio) {
        this.id = idAutoIncrement;
        this.tiopusSub = tipoSub;
        this.tipus = tipus;
        this.fila = fila;
        this.nivell = nivell;
        this.dimensionalitat=dimensio;
    }

    public boolean equals(Simbol simbol){
        return ( ( this.id==simbol.getIdAutoIncrement() ) && ( this.nivell == simbol.getNivell() ) );
    }

    public int getFila(){
        return fila;
    }

    @Override
    public String toString(){
        return  tiopusSub.toString() + ": " + id + ", tipo: " + tipus + ", nivel: " + nivell;
    }

    public String getIdAutoIncrement() {
        return id;
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
    public int getDimensio(){
        return this.dimensionalitat;
    }
}