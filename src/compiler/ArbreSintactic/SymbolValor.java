package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolValor {

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
   public SymbolValor(String id, TipusSub tipusSub){
        this.iden=id;
        this.tipusSub=tipusSub;

   }
    /*
    Cas valor en el qual tenim un array
     */
    public SymbolValor(SymbolArray arr){
        this.array=arr;
        this.iden = arr.getID();

    }

    /*
    Cas valor el qual és un integer directe.
     */
    public SymbolValor(Object integer){
        inte=Integer.parseInt(integer.toString()); //passam text a int

        tipusSub=TipusSub.INT;
    }
    /*
    Cas valor equival a un booleam
     */
    public SymbolValor(boolean b){
        this.b = b;
        this.tipusSub=TipusSub.BOOLEAN;
    }

    /*
    Cas valor el qual equival a una cridada del subprograma
     */
    public SymbolValor(SymbolSubProgramCall subProgCall){
        this.subProgramCall=subProgCall;
    }
    
    /*
    Producció on es posa not (ExprSimple)
    */
    public SymbolValor(SymbolExpressioSimple expr){
        this.exprSimple=expr;
        this.tipusSub=TipusSub.BOOLEAN;
        if(this.exprSimple.getTipusSubResultat()==this.tipusSub){
            this.tipusSub=TipusSub.BOOLEAN;
        }else{
            this.tipusSub=TipusSub.NULL;
        }
        
    }
    
    public int getValorInt(){
        return inte;
    }

    public TipusSub getTipusSub() {
        return tipusSub;
    }

    public String getIden() {
        return iden;
    }
}