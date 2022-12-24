package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolProgram extends SymbolBase{

    SymbolDeclList declList;
    SymbolStatementList statementList;

    public SymbolProgram(SymbolDeclList declList, SymbolStatementList statementList) {
        //super("DeclList",null);
        this.declList = declList;
        this.statementList = statementList;
    }
    public SymbolProgram(){
        System.out.println("soy un programa");
    }

    public void codiTresAdreces(codiTresAdreces codi){
        if (declList != null){
            codi.començaDecl();
            //this.declList.codiTresAdreces(codi);
            //codi.finaliztaDecl();
        }
        //this.statementList.codiTresAdreces(codi);
    }
}