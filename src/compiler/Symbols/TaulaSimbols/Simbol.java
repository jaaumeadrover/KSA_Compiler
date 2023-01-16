

package compiler.Symbols.TaulaSimbols;

//import ArbolSintactico.Tipo;

import java.util.ArrayList;

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
    private int dimensionalitat;
    private ArrayList<TipusSub> tipusSubParam = new ArrayList<TipusSub>();
    private ArrayList<Tipus> tipusParam = new ArrayList<Tipus>();

    public Simbol(String idAutoIncrement, TipusSub tipoSub, Tipus tipus, int fila, int nivell,int dimensio) {
        this.idAutoIncrement = idAutoIncrement;
        this.tiopusSub = tipoSub;
        this.tipus = tipus;
        this.fila = fila;
        this.nivell = nivell;
        this.dimensionalitat=dimensio;
    }
    public Simbol(String idAutoIncrement, TipusSub tipoSub, Tipus tipus, int fila, int nivell,int dimensio,ArrayList<TipusSub> tsubparam,ArrayList<Tipus> tipusParam) {
        this.idAutoIncrement = idAutoIncrement;
        this.tiopusSub = tipoSub;
        this.tipus = tipus;
        this.fila = fila;
        this.nivell = nivell;
        this.dimensionalitat=dimensio;
        this.tipusSubParam=tsubparam;
        this.tipusParam=tipusParam;
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

    public ArrayList<TipusSub> getTipusSubParam(){
        return this.tipusSubParam;
    }

    public ArrayList<Tipus> getTipusParam(){
        return this.tipusParam;
    }
}