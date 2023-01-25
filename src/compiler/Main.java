/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compiler;

import compiler.ArbreSintactic.ArbreSintactic;
import compiler.Ensamblador.codi68k;
import compiler.GeneracioCodiIntermedi.codiTresAdreces;
import compiler.Semantic.Semantic;
import compiler.sintactic.Parser;
import compiler.lexic.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;

/**
 *
 * @author Jaume
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Reader input = null;

        try {
            input = new FileReader("C:\\Users\\Jaume\\Desktop\\UIB\\Cursos\\TERCER\\1r quatri\\COMPILADORS\\PRÀCTICA KSA\\KSA_Compiler\\src\\TESTING\\1.Funcions\\1.Funcions\\bubbleSortError.ksa");

            FileWriter writerErrors = new FileWriter("errors.txt");

            // LÈXIC
            Scanner scanner = new Scanner(input);
            scanner.initFitxer();
            SymbolFactory sf = new ComplexSymbolFactory();

            // SINTACTIC
            Parser parser = new Parser(scanner, sf);
            parser.parse();
            scanner.tancaFitxer();

            // SEMANTIC
            Semantic sem = parser.getComprovaTipus();
            ArrayList<String> errorsSem = sem.geterrorsSemantic();
            ArrayList<String> errorsSint = parser.geterrorsSintactic();

            // TAULA SIMBOLS
            String ts = sem.getTs().toString();
            FileWriter fitxerTaulaSimbols = new FileWriter("TaulaSimbols.txt");
            fitxerTaulaSimbols.write(ts);
            fitxerTaulaSimbols.close();

            // CODI TRES ADRECES
            codiTresAdreces codi = null;
            if(errorsSem.isEmpty() && errorsSint.isEmpty()) {
                //Si no hi ha errors generam el codi
                ArbreSintactic arbre = parser.getArbreSintac();
                codi = arbre.generarCodiTresAdreces();

                String TaulaVars = codi.getTv().toString();
                FileWriter fitxerTV = new FileWriter("TaulaVariables.txt");
                fitxerTV.write(TaulaVars);
                fitxerTV.close();

                String TaulaProcediments = codi.getTp().toString();
                FileWriter fitxerTP = new FileWriter("TaulaProcediments.txt");
                fitxerTP.write(TaulaProcediments);
                fitxerTP.close();

                String codiIntermedi = codi.toString();
                FileWriter codiIntermediFile = new FileWriter("codiIntermedi.txt");
                codiIntermediFile.write(codiIntermedi);
                System.out.println(codiIntermedi);
                codiIntermediFile.close();

                // CODI ENSSAMBLADOR
                codi68k c = new codi68k("exe", codi);
                c.generaAssembly();

            }else{
                //Escriu errors al fitxer
                if(!errorsSint.isEmpty()) {
                    for (int i = 0; i < errorsSint.size(); i++) {
                        String element = errorsSint.get(i);
                        if (i == 0) {
                            System.err.println(element);
                        }

                        writerErrors.write(element + "\n");
                    }
                }
                if(!errorsSem.isEmpty()) {
                    for (int i = 0; i < errorsSem.size(); i++) {
                        String element = errorsSem.get(i);
                        if (i == 0) {
                            System.err.println(element);
                        }
                        writerErrors.write(element + "\n");
                    }
                }
                writerErrors.close();
               System.err.println("Programa incompilable per errors"); 
            }

        }catch(Exception e) {
            System.err.println("error: "+e);
            e.printStackTrace(System.err);
        }
    }

}
