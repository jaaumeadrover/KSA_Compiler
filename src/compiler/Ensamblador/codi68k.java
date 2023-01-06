package compiler.Ensamblador;

import compiler.GeneracioCodiIntermedi.Instruccio;
import compiler.GeneracioCodiIntermedi.Variable;
import compiler.GeneracioCodiIntermedi.codiTresAdreces;
import java.io.IOException;
import java.util.ArrayList;

public class codi68k {

    private Writer f;
    ArrayList<String> parametres;
    codiTresAdreces codi;

    public codi68k(String file,codiTresAdreces codi) throws IOException{
        f= new Writer(file);
        this.parametres=new ArrayList<>();
        this.codi=codi;
    }
    public void generaAssembly() throws IOException{
        f.escriureFitxer("ORG $700");
        for (int i = 0; i < codi.getTv().getSize(); i++) {
            if(codi.getTv().get(i).getConstant()){
                this.generaConstant(codi.getTv().get(i));
            }else{
                this.generaVariable(codi.getTv().get(i));
            }
        }
        f.escriureFitxer("ORG $1000");
        f.escriureFitxer("START");
        
        //GETSIZE DE Tv?
        for(int i = 0; i<codi.getSize();i++){
            generaInstruccio(codi.Instruccions().get(i));
        }
        f.escriureFitxer("JMP FINAL");
        f.escriureFitxer("FINAL");
        f.escriureFitxer("END START");
    }

    public void generaConstant(Variable constant) throws IOException{
        f.escriureFitxer(constant.getNom() + " EQU  " + constant.getValor());
    }
    public void generaVariable(Variable var) throws IOException{
        f.escriureFitxer(var.getNom() + " DS.L 1");
    }

    private void generaInstruccio(Instruccio get) {
    }


}