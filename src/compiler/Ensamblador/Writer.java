package compiler.Ensamblador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

 public class Writer {
    private BufferedWriter f;

    public Writer(String s) throws IOException{
            f=new BufferedWriter(new FileWriter(s+".68K"));
    }
    
    public void escriureFitxer(String txt) throws IOException{
            f.write(txt);
            f.newLine();
    }
    public void tancaFitxer() throws IOException{
            f.close();
    }
}