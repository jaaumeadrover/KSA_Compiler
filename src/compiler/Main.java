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
            //EN cas d'utilitzar windows utilitzar aquesta nomenclatura per al path --> EXEMPLE: "C:\\Users\\Jaume\\Desktop\\UIB\\Cursos\\TERCER\\1r quatri\\COMPILADORS\\PRÀCTICA KSA\\KSA_Compiler\\src\\TESTING\\1.Funcions\\1.Funcions\\Error4.ksa"
            if(args.length>0){
                try {
                    input = new FileReader(args[0]);
                    //input=new FileReader("C:\\Users\\Jaume\\Desktop\\UIB\\Cursos\\TERCER\\1r quatri\\COMPILADORS\\PRÀCTICA KSA\\KSA_Compiler\\src\\compiler\\prova.ksa");
                }catch(Exception e){
                    System.err.println("El fitxer "+args[0]+" té un error "+e);
                }
            }else{
                System.err.println("Insereix un nom de fitxer");
                System.exit(0);
            }
            //input = new FileReader("//Users/joanbalaguer/Desktop/Compiladors/Practica/KSA_Compiler/src/TESTING/1.Funcions/bubbleSort.ksa");

            FileWriter writerErrors = new FileWriter(args[0]+"_errors.txt");




            // LÈXIC
            Scanner scanner = new Scanner(input);
            scanner.initFitxer(writerErrors);
            SymbolFactory sf = new ComplexSymbolFactory();

            System.out.println("h");
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
            FileWriter fitxerTaulaSimbols = new FileWriter(args[0]+"_TaulaSimbols.txt");
            fitxerTaulaSimbols.write(ts);
            fitxerTaulaSimbols.close();

            // CODI TRES ADRECES
            codiTresAdreces codi = null;
            if(errorsSem.isEmpty() && errorsSint.isEmpty()) {
                //Si no hi ha errors generam el codi
                ArbreSintactic arbre = parser.getArbreSintac();
                codi = arbre.generarCodiTresAdreces();

                String TaulaVars = codi.getTv().toString();
                FileWriter fitxerTV = new FileWriter(args[0]+"_TaulaVariables.txt");
                fitxerTV.write(TaulaVars);
                fitxerTV.close();

                String TaulaProcediments = codi.getTp().toString();
                FileWriter fitxerTP = new FileWriter(args[0]+"_TaulaProcediments.txt");
                fitxerTP.write(TaulaProcediments);
                fitxerTP.close();

                String codiIntermedi = codi.toString();
                FileWriter codiIntermediFile = new FileWriter(args[0]+"_codiIntermedi.txt");
                codiIntermediFile.write(codiIntermedi);

                codiIntermediFile.close();

                // CODI ENSSAMBLADOR
                codi68k c = new codi68k(args[0]+"_exe", codi);
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
