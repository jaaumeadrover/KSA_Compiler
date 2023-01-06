package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolProgram {

    SymbolDeclList declList;
    SymbolStatementList statementList;

    public SymbolProgram(SymbolDeclList declList, SymbolStatementList statementList) {
        this.declList = declList;
        this.statementList = statementList;
    }
    public SymbolProgram(){

    }

    public void codiTresAdreces(codiTresAdreces codi){
        if (declList != null){
            codi.començaDecl();
            this.declList.codiTresAdreces(codi);
            codi.acabaDecl();
        }
        this.statementList.codiTresAdreces(codi);

    }
}