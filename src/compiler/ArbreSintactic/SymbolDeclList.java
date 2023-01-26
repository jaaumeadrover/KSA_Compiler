/**
 * Assignatura 21780 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Intel·ligència Artificial i Computació
 *
 * Professor: Pere Palmer
 */
package compiler.ArbreSintactic;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import compiler.sintactic.ParserSym;
import compiler.GeneracioCodiIntermedi.*;
import compiler.Symbols.TaulaSimbols.Tipus;
/**
 * Classe que implementa la variable DeclList de la gramàtica
 *
 * @author ATA2
 */
public class SymbolDeclList  {
   SymbolDecl declaracio;
   SymbolDeclList llistaDecl;

    public SymbolDeclList(SymbolDecl decl,SymbolDeclList declList) {
            this.llistaDecl=declList;
            this.declaracio=decl;
    }

    public SymbolDeclList() {
    }

    public void codiTresAdreces(codiTresAdreces codi){
        //mentre quedin declaracions a generar
        if (llistaDecl != null){
            this.llistaDecl.codiTresAdreces(codi);
        }
        if (declaracio != null){
            this.declaracio.codiTresAdreces(codi);
        }
    }

}
