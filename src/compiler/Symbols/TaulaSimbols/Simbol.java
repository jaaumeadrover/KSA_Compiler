

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
    private boolean init;


    public Simbol(String idAutoIncrement, TipusSub tipoSub, Tipus tipus, int fila, int nivell,int dimensio,boolean init) {
        this.id = idAutoIncrement;
        this.tiopusSub = tipoSub;
        this.tipus = tipus;
        this.fila = fila;
        this.nivell = nivell;
        this.dimensionalitat=dimensio;
        this.init=init;
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
    
    public boolean esInit(){
        return this.init;
    }

    public void inicialitza(){
        this.init=true;
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