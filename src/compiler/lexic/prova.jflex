// El codi que es copiarà tal qual al document. A l'inici

import java.io.*;

%%
// Declaracions

digit     = [0-9]
nodigit   = [^0-9]
digits    = {digit}+
nodigits  = {nodigit}+


%public             // Per indicar que la classe és pública
%class Prova        // El nom de la classe
%int                // El tipus dels tokens identificats

// El següent codi es copiarà també, dins de la classe. És a dir, si es posa res ha de ser en el format adient: mètodes, atributs, etc.
%{
	public static void main(String []args) {
		if (args.length < 1) {
			System.err.println("Indica un fitxer amb les dades d'entrada");
			System.exit(0);
		}
		try {
			FileReader in = new FileReader(args[0]);

			Prova parser = new Prova(in);
			parser.yylex();   // <- El mètode d'invocació per començar a parsejar el document
		} catch (FileNotFoundException e) {
			System.err.println("El fitxer d'entrada '"+args[0]+"' no existeix");
		} catch (IOException e) {
			System.err.println("Error processant el fitxer d'entrada");
		}
	}
%}

%%

// Regles/accions

{digits}		{ System.out.println("He llegit el valor "+yytext()); }
{nodigits}		{ /* Res a fer */ }