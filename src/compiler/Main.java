/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compiler;

import compiler.sintactic.Parser;
import compiler.lexic.Scanner;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
            } else {
                //System.out.println("Escriu l'expressió que vols calcular (help; per ajuda):");
                //System.out.print(">>> ");
                input = new FileReader("C:\\Users\\Jaume\\Desktop\\UIB\\Cursos\\TERCER\\1r quatri\\COMPILADORS\\PRÀCTICA KSA\\KSA_Compiler\\src\\compiler\\prova.txt");
                //input = new InputStreamReader(System.in);
            }

            Scanner scanner = new Scanner(input);
            SymbolFactory sf = new ComplexSymbolFactory();
            Parser parser = new Parser(scanner, sf);
            parser.parse();
        } catch(Exception e) {
            System.err.println("error: "+e);
            e.printStackTrace(System.err);
        }
    }

}
