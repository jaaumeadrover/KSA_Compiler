package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolProgram {

    SymbolDeclList declList;
    SymbolStatementList statementList;

    public SymbolProgram(SymbolDeclList declList, SymbolStatementList statementList) {
        //super("DeclList",null);
        this.declList = declList;
        this.statementList = statementList;
    }
    public SymbolProgram(){

    }

    public void codiTresAdreces(codiTresAdreces codi){
        if (declList != null){
            codi.començaDecl();
            this.declList.codiTresAdreces(codi);
            codi.finaliztaDecl();
        }
        this.statementList.codiTresAdreces(codi);

    }
}