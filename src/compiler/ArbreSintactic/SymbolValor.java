package compiler.Symbols;
import compiler.Symbols.TaulaSimbols.Tipus;

public class SymbolValor extends SymbolBase {

    private String iden;
    private SymbolArray array;
    private int inte;
    private boolean b;
    private SymbolSubProgramCall subProgramCall;

   public SymbolValor(String id){
        this.iden=id;
       System.out.println("Soy un valor");
   }

    public SymbolValor(SymbolArray arr){
        this.array=arr;
        System.out.println("Soy un valor");
    }
    public SymbolValor(){
        System.out.println("Soy un valor");
    }

    public SymbolValor(boolean b){
        this.b = b;
        if(b==true){

        }
        else{

        }
        System.out.println("Soy un valor");
    }

    public SymbolValor(SymbolSubProgramCall subProgCall){
        this.subProgramCall=subProgCall;
        System.out.println("Soy un valor");
    }

 }