package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolValor {

    private String iden;
    private SymbolArray array;
    private int inte;
    private boolean b;
    private boolean esBuit;
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
    public SymbolValor(SymbolArray arr,TipusSub tipusSub){
        this.array=arr;
        this.iden = arr.getID();
        this.tipusSub=tipusSub;
    }

    /*
    Cas valor el qual és un integer directe.
     */
    public SymbolValor(Object integer){
        inte=Integer.parseInt(integer.toString()); //passam text a int
        iden=integer.toString();
        System.out.println("Soc un integer: "+iden);
        tipusSub=TipusSub.INT;
    }
    /*
    Cas valor equival a un booleam
     */
    public SymbolValor(String b){
        if(b.equals("true")){
            this.b=true;
        }else{
            this.b=false;
        }
        System.out.println("He utilitzat el constructor de boolean!");
        this.tipusSub=TipusSub.BOOLEAN;
    }

    /*
    Cas valor el qual equival a una cridada del subprograma
     */
    public SymbolValor(SymbolSubProgramCall subProgCall,TipusSub tipus){
        this.subProgramCall=subProgCall;
        this.tipusSub=tipus;
    }
    
    /*
    Producció on es posa not (ExprSimple)
    */
    public SymbolValor(SymbolExpressioSimple expr,boolean b){
        this.exprSimple=expr;
        this.tipusSub=TipusSub.BOOLEAN;
        if(this.exprSimple.getTipusSubResultat()==this.tipusSub){
            this.tipusSub=TipusSub.BOOLEAN;
        }else{
            this.tipusSub=TipusSub.NULL;
        }
        
    }
    //Constructor en la produccio lparen exprSimple rparen
    public SymbolValor(SymbolExpressioSimple expr){
        this.exprSimple=expr;
        this.tipusSub=expr.getTipusSubResultat();
    }

    public SymbolValor() {
        this.esBuit=true;
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
    public boolean esBuit(){
        return this.esBuit;
    }
    
    @Override
    public String toString(){
        String s="";
        s+="id: "+iden+",valor: "+"";
        
        return s;
    }
}