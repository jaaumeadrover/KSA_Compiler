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
    private int index;
    private TipusSub tipusSub;

    /*
    Cas valor equival a una variable i cal consultar el id en la taula de símbols
     */
   public SymbolValor(String id, TipusSub tipusSub){
        this.iden=id;
        this.tipusSub=tipusSub;
        this.index = 1;
   }
    /*
    Cas valor en el qual tenim un array
     */
    public SymbolValor(SymbolArray arr,TipusSub tipusSub){
        this.array=arr;
        this.iden = arr.getID();
        this.tipusSub=tipusSub;
        this.index = 2;
    }

    /*
    Cas valor el qual és un integer directe.
     */
    public SymbolValor(Object integer){
        inte=Integer.parseInt(integer.toString()); //passam text a int
        iden=integer.toString();
        //System.out.println("Soc un integer: "+iden);
        tipusSub=TipusSub.INT;
        this.index = 3;
    }
    /*
    Cas valor equival a un booleam
     */
    public SymbolValor(String b){
        if(b.equals("true")){
            this.b=true;
            this.iden = "true";
        }else{
            this.b=false;
            this.iden="false";
        }
        //System.out.println("He utilitzat el constructor de boolean!");
        this.tipusSub=TipusSub.BOOLEAN;
        this.index = 4;
    }

    /*
    Cas valor el qual equival a una cridada del subprograma
     */
    public SymbolValor(SymbolSubProgramCall subProgCall,TipusSub tipus){
        this.subProgramCall=subProgCall;
        this.tipusSub=tipus;
        this.index = 5;
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
        this.index = 6;
        
    }
    //Constructor en la produccio lparen exprSimple rparen
    public SymbolValor(SymbolExpressioSimple expr){
        this.exprSimple=expr;
        this.tipusSub=expr.getTipusSubResultat();
        this.index = 7;
    }

    public SymbolValor() {
        this.esBuit=true;
        this.index = 8;
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

    public String codiTresAdreces(codiTresAdreces codi) {
        switch (index) {
            case 1:
                //generar codi de una variable identificador
                return iden;
            case 2:
                //generar codi de una variable array

                //x = arr.getIndex();
                //variable_temporal=(x-1)*(MidaTipusArray);
                //cas indexat  ->  s'utilitza per assignar valor a altra variable.

                //cas assignat ->  s'hi assigna un valor en operacio

            case 3:
                //generar codi de un integer

                //guardar en variable temporal int
                String temp = codi.addVariable(TipusSub.INT,"t");

                Operand enter = new Operand(iden,OperandsCTA.enterLit);

                codi.generar(TipusInstruccionsCTA.COPIA,enter,null,temp);

                return temp;
            case 4:
                //generar codi de un boolean
                String val="";
                if(b) {
                    val =  String.valueOf(-1);
                }else{
                    val = String.valueOf(0);
                }
                temp = codi.addVariable(TipusSub.BOOLEAN,"t");
                Operand bool = new Operand(val, OperandsCTA.boolea);
                codi.generar(TipusInstruccionsCTA.COPIA, bool, null, temp);
                return temp;
            case 5:
                //generar crida a subprograma
                subProgramCall.codiTresAdreces();

                //Dedins subProgramCall es fa aixo
                /*
                if(this.subProgramCall.retorna()){

                    Simbol s = ts.consultaFunc(this.subProgramCall.getId());

                    TipusSub tipussub = s.getTipusSub();
                    String temp = codi.addVariable(tipussub,"t");
                    subProgramCall.codiTresAdreces();
                    codi.generar(TipusInstruccionsCTA.CALL,this.subProgramCall.getId(),null,temp);

                }else{
                    subProgramCall.codiTresAdreces();
                    codi.generar(TipusInstruccionsCTA.CALL,this.subProgramCall.getId(),null,null);
                }
                */

            case 6:
                //generar instruccio not del boolean
                String operand1=this.exprSimple.codiTresAdreces(codi);
                codi.generar(TipusInstruccionsCTA.NOT,operand1,null,null);
            case 7:
                //generar codi de una expressió simple
            case 8:
                //constructor buit
        }
        return null;
    }
}