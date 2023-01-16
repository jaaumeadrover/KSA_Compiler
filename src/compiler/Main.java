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
            if (args.length > 0) {
                input = new FileReader(args[0]);
//                if (!input.endsWith(".ksa")) {
//                    System.err.println("-> el archivo del programa tiene que acabar en .ksa");
//                    System.exit(0);
//                }
            } else {
                //System.out.println("Escriu l'expressió que vols calcular (help; per ajuda):");
                //System.out.print(">>> ");
                ///Users/joanbalaguer/Desktop/Compiladors/Practica/KSA_Compiler/src/TESTING/1.Funcions/prova.txt
                ///home/diegofes/GitHub/KSA_Compiler/src/TESTING/1.Funcions/prova.txt
                //C:\Users\Jaume\Desktop\UIB\Cursos\TERCER\1r quatri\COMPILADORS\PRÀCTICA KSA\KSA_Compiler\src\TESTING\1.Funcions\prova.txt
                //input = new FileReader("/Users/joanbalaguer/Desktop/Compiladors/Practica/KSA_Compiler/src/TESTING/1.Funcions/prova2.txt");
                input = new FileReader("/Users/joanbalaguer/Desktop/Compiladors/Practica/KSA_Compiler/src/TESTING/1.Funcions/prova2.txt");
                //Users\marcc\OneDrive\Escritorio\GitHub\KSA_Compiler
                //input = new InputStreamReader(System.in);
            }
            
            FileWriter writerErrors = new FileWriter("errors.txt");

            Scanner scanner = new Scanner(input);
            SymbolFactory sf = new ComplexSymbolFactory();
            Parser parser = new Parser(scanner, sf);
            parser.parse();
            Semantic sem = parser.getComprovaTipus();
            
            ArrayList<String> errorsSem = sem.geterrorsSemantic();
            //System.out.println("Semantic: "+errorsSem);
            ArrayList<String> errorsSint = parser.geterrorsSintactic();
            //System.out.println("Sintactic: "+errorsSint);

            //Escriu errors al fitxer
            for (int i = 0; i < errorsSint.size(); i++) {
                String element = errorsSint.get(i);
                System.err.println(element);
                writerErrors.write(element+ "\n");
            }
            for (int i = 0; i < errorsSem.size(); i++) {
                String element = errorsSem.get(i);
                System.err.println(element);
                writerErrors.write(element+ "\n");
            }
            writerErrors.close();
            codiTresAdreces codi = null;
            
            if(errorsSem.isEmpty() && errorsSint.isEmpty()) {
                ArbreSintactic arbre = parser.getArbreSintac();
                codi = arbre.generarCodiTresAdreces();
                String codiIntermedi = codi.toString();

                FileWriter codiIntermediFile = new FileWriter("codiIntermedi.txt");

                codiIntermediFile.write(codiIntermedi);
                System.out.println(codiIntermedi);
                codiIntermediFile.close();
                
                // CODI ENSSAMBLADOR
                codi68k c = new codi68k("exe", codi);
                c.generaAssembly();
                       

            }else{
               System.err.println("Programa incompilable per errors"); 
            }
                //System.out.println("TAULA VARIABLES: "+codi.getTv());

        }catch(Exception e) {
            System.err.println("error: "+e);
            e.printStackTrace(System.err);
        }
    }

}
