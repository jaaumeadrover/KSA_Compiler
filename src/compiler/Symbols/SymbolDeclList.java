/**
 * Assignatura 21780 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Intel·ligència Artificial i Computació
 *
 * Professor: Pere Palmer
 */
package compiler.Symbols;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

import compiler.sintactic.ParserSym;
/**
 * Classe que implementa la variable DeclList de la gramàtica
 *
 * @author ATA2
 */
public class SymbolDeclList extends SymbolBase {
    /**
     * construeix una variable E a partir del valor de una T i una Ep
     * (què pot ser buida). Els valors es combinen segons quina sigui l'operació
     * que conté valor2 (què és la operació que es troba entre els dos valors
     * @param valorT
     * @param valorEp
     */
    public SymbolDeclList(SymbolDecl decl,SymbolDeclList declList) {
            super("DeclList",null);

    }

    public SymbolDeclList() {
        System.out.println("Soc un Declist"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
