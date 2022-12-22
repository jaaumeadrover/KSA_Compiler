package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class SymbolValor extends SymbolBase {

    private String iden;
    private SymbolArray array;
    private int inte;
    private boolean b;
    private SymbolSubProgramCall subProgramCall;
    private TipusSub tipusSub;
    /*
    Caso variable
     */
   public SymbolValor(String id, TipusSub tipusSub){
        this.iden=id;
        this.tipusSub=tipusSub;
       System.out.println("Soy un valor");
   }
/*
caso array
 */
    public SymbolValor(SymbolArray arr){
        this.array=arr;
        System.out.println("Soy un valor");
    }
    /*
    caso integer
     */
    public SymbolValor(Object integer){
        System.out.println("Soy un valor integer");
        inte=Integer.parseInt(integer.toString()); //passam text a int
        System.out.println("Integer: "+inte);
        tipusSub=TipusSub.INT;
    }

    public SymbolValor(boolean b){
        this.b = b;
        this.tipusSub=TipusSub.BOOLEAN;
        System.out.println("Soy un valor");
    }

    public SymbolValor(SymbolSubProgramCall subProgCall){
        this.subProgramCall=subProgCall;
        System.out.println("Soy un valor");
    }

    public SymbolValor(SymbolExpressioSimple expr){
        this.tipusSub=TipusSub.BOOLEAN;
    }
    
    public int getValorInt(){
        return inte;
    }

 }