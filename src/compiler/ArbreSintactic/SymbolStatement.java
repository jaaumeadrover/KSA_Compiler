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

    public SymbolStatement(codiTresAdreces){

        System.out.println("soy un statement");
    }
    
    public SymbolStatement(SymbolVarDecl vard, codiTresAdreces){
        this.vard = vard;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolExpressioSimple , codiTresAdreces){
        this.expr=expr;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolForIteration f, codiTresAdreces){
        this.f=f;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolWhileStatement w, codiTresAdreces){
        this.w=w;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolInputStatement input, codiTresAdreces){
        this.input=input;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolPrintStatement print, codiTresAdreces){
        this.print=print;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolIfStatement i, codiTresAdreces){
        this.i=i;
        System.out.println("soy un statement");
    }
    public SymbolStatement(SymbolSwitchCase s, codiTresAdreces){
        this.s=s;
        System.out.println("soy un statement");
    }
}