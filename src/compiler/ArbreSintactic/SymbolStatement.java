package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolStatement {

    private SymbolVarDecl vard;
    private SymbolExpressioSimple expr;
    private SymbolForIteration f;
    private SymbolWhileStatement w;
    private SymbolInputStatement input;
    private SymbolPrintStatement print;
    private SymbolIfStatement i;
    private SymbolSwitchCase s;

    public SymbolStatement(codiTresAdreces codi){

        System.out.println("soy un statement");
    }
    
    public SymbolStatement(SymbolVarDecl vard, codiTresAdreces codi){
        this.vard = vard;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolExpressioSimple expr, codiTresAdreces codi){
        this.expr=expr;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolForIteration f, codiTresAdreces codi){
        this.f=f;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolWhileStatement w, codiTresAdreces codi){
        this.w=w;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolInputStatement input, codiTresAdreces codi){
        this.input=input;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolPrintStatement print, codiTresAdreces codi){
        this.print=print;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolIfStatement i, codiTresAdreces codi){
        this.i=i;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolSwitchCase s, codiTresAdreces codi){
        this.s=s;
        System.out.println("soy un statement");
    }
}