package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolStatement {

    private SymbolVarDecl vard;
    private SymbolExpressioSimple expr;
    private
    private SymbolForIteration f;
    private SymbolWhileStatement w;
    private SymbolInputStatement input;
    private SymbolPrintStatement print;
    private SymbolIfStatement i;
    private SymbolSwitchCase s;

    private int index;

    public SymbolStatement(){
        this.index=0;
    }
    
    public SymbolStatement(SymbolVarDecl vard){
        this.vard = vard;
        this.index=1;
    }
    public SymbolStatement(SymbolExpressioSimple expr){
        this.expr=expr;
        this.index=2;
    }
    public SymbolStatement(SymbolForIteration f){
        this.f=f;
        this.index=3;
    }
    public SymbolStatement(SymbolWhileStatement w){
        this.w=w;
        this.index=4;
    }
    public SymbolStatement(SymbolInputStatement input){
        this.input=input;
        this.index=5;
    }
    public SymbolStatement(SymbolPrintStatement print){
        this.print=print;
        this.index=6;
    }
    public SymbolStatement(SymbolIfStatement i){
        this.i=i;
        this.index=7;
    }
    public SymbolStatement(SymbolSwitchCase s){
        this.s=s;
        this.index=8;
    }

    public void codiTresAdreces(codiTresAdreces codi) {
        switch (index) {
            case 0:
                break;
            case 1:
                this.vard.codiTresAdreces(codi);
            case 2:
                this.expr.codiTresAdreces(codi);
            case 3:
                this.f.codiTresAdreces(codi);
            case 4:
                this.w.codiTresAdreces(codi);
            case 5:
                this.input.codiTresAdreces(codi);
            case 6:
                this.print.codiTresAdreces(codi);
            case 7:
                this.i.codiTresAdreces(codi);
            case 8:
                this.s.codiTresAdreces(codi);
        }
    }
}