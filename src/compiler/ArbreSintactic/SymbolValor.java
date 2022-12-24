package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class SymbolValor extends SymbolBase {

    private String iden;
    private SymbolArray array;
    private int inte;
    private boolean b;
    private SymbolSubProgramCall subProgramCall;
    private SymbolExpressioSimple exprSimple;

    private TipusSub tipusSub;


    /*
    Cas valor equival a una variable i cal consultar el id en la taula de símbols
     */
   public SymbolValor(String id, TipusSub tipusSub, codiTresAdreces codi){
        this.iden=id;
        this.tipusSub=tipusSub;
       System.out.println("Soy un valor de una variable");
   }
    /*
    Cas valor en el qual tenim un array
     */
    public SymbolValor(SymbolArray arr, codiTresAdreces codi){
        this.array=arr;
        System.out.println("Soy un valor");
    }

    /*
    Cas valor el qual és un integer directe.
     */
    public SymbolValor(Object integer, codiTresAdreces codi){
        System.out.println("Soy un valor integer");
        System.out.println("hola: "+integer.toString());
        inte=Integer.parseInt(integer.toString()); //passam text a int
        System.out.println("Integer: "+inte);
        tipusSub=TipusSub.INT;
    }
    /*
    Cas valor equival a un booleam
     */
    public SymbolValor(boolean b, codiTresAdreces codi){
        this.b = b;
        this.tipusSub=TipusSub.BOOLEAN;
        System.out.println("Soy un valor");
    }

    /*
    Cas valor el qual equival a una cridada del subprograma
     */
    public SymbolValor(SymbolSubProgramCall subProgCall, codiTresAdreces codi){
        this.subProgramCall=subProgCall;
        System.out.println("Soy un valor");
    }

    public SymbolValor(SymbolExpressioSimple expr, codiTresAdreces codi){
        System.out.println("Holaa soc un valor boolean");
        this.exprSimple=expr;
        this.tipusSub=TipusSub.BOOLEAN;
    }
    
    public int getValorInt(){
        return inte;
    }

    public TipusSub getTipusSub() {
        return tipusSub;
    }
}