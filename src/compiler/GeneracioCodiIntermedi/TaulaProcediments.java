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
    
    public int getTaulaSize(){
        return this.TP.size();
    }

    public void afegirProc(Procediment proc){
        TP.add(proc);
        numProc++;
        System.out.println("HE AFEGIT PROCEDIMENT:"+proc);
        this.numProcActius=numProc;
    }

    public Procediment eliminarProc(){
        Procediment proc=TP.get(this.numProc-1);
        TP.remove(this.numProc-1);
        numProc--;
        return proc;
    }

    public Procediment getProcediment(String nom){
        for (int i = 0; i < TP.size(); i++) {

            if(TP.get(i).getNomProc().equals(nom)){
                return TP.get(i);
            }
        }

        return null;
    }
    public void tancaProcediment(){
        this.numProcActius=0;
    }

    public int getNumProcActius(){
        return numProcActius;
    }
    
    
    
    public String toString(){
        String s="";
        for (int i = 0; i < TP.size(); i++) {
            s+=TP.get(i);
        }
        return s;
    }
}