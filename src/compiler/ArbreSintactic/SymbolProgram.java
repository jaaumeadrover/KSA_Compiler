package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolProgram extends SymbolBase{
    
        public SymbolProgram(SymbolDecl decl,SymbolDeclList declList) {
            super("DeclList",null);

        }
        public SymbolProgram(){
            System.out.println("soy un programa");
        }
}