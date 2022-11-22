/**
 * Assignatura 21780 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Intel·ligència Artificial i Computació
 *
 * Professor: Pere Palmer
 */
package compiler.sintactic.Symbols;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

import compiler.sintactic.ParserSym;
/**
 * Classe que implementa la variable DeclList de la gramàtica
 *
 * @author ATA2
 */
public class SymbolInitArray extends SymbolBase {
    /**
     * construeix una variable E a partir del valor de una T i una Ep
     * (què pot ser buida). Els valors es combinen segons quina sigui l'operació
     * que conté valor2 (què és la operació que es troba entre els dos valors
     * @param valorT
     * @param valorEp
     */
    public SymbolInitArray(SymbolDecl decl,SymbolDeclList declList) {
        super("DeclList",null);
    }

    /**
     * construeix una variable E a partir del valor que es rep. Servei pes
     * cassos d'assignació
     * @param valor el valor que té
     */
    public SymbolInitArray(SymbolDecl decl) {
        super("E", valor); // Crear instància amb el valor rebut
    }


    /**
     * construeix una variable Ep buida (en una derivació a lambda)
     */
    public SymbolInitArray() {
        super(); // Crear instància amb un valor fals
    }
}
