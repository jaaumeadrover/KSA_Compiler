package compiler.GeneracioCodiIntermedi;

import java.util.ArrayList;

public class TaulaProcediments{
    private ArrayList<Procediment> TP;
    private int numProc;
    private int numProcActius;

    public TaulaProcediments(){
        this.TP=new ArrayList();
        this.numProc=0;
        this.numProcActius=0;
    }

    public int getNumProc(){
        return this.numProc;
    }

    public ArrayList<Procediment> getTaulaProc(){
        return this.TP;
    }

    public void afegirProc(Procediment proc){
        TP.add(proc);
        numProc++;
        this.numProcActius=numProc;
    }

    public Procediment eliminarProc(){
        Procediment proc=TP.get(this.numProc-1);
        TP.remove(this.numProc-1);
        np--;
        return proc;
    }

    public Procediment getProcediment(String nom){
        for (int i = 0; i < TP.size(); i++) {
            if(TP.get(i).nomProc==nom){
                return TP.get(i);
            }
        }

        return null;
    }
    public void tancaProcediment(){
        this.numProcActius=0;
    }
}