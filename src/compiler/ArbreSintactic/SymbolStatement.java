package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolStatement extends SymbolBase {

    private SymbolVarDecl vard;
    private SymbolExpressioSimple expr;
    private SymbolForIteration f;
    private SymbolWhileStatement w;
    private SymbolInputStatement input;
    private SymbolPrintStatement print;
    private SymbolIfStatement i;
    private SymbolSwitchCase s;

    public SymbolStatement(){

        System.out.println("soy un statement");
    }
    
    public SymbolStatement(SymbolVarDecl vard){
        this.vard = vard;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolExpressioSimple expr){
        this.expr=expr;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolForIteration f){
        this.f=f;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolWhileStatement w){
        this.w=w;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolInputStatement input){
        this.input=input;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolPrintStatement print){
        this.print=print;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolIfStatement i){
        this.i=i;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolSwitchCase s){
        this.s=s;
        System.out.println("soy un statement");
    }
}