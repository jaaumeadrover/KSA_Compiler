package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.*;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolExpressioSimple extends SymbolBase{
    private SymbolExpressioSimple expressioSimple;
    private SymbolOperacio operacio;
    private SymbolValor valor;
    private String iden;
    private SymbolVarInit varinit;
    private TipusSub tsOperador;
    private TipusSub tsResultat;
    
    /*
    Constructor en el qual hi ha més d'una expressió
    */
    public SymbolExpressioSimple(SymbolExpressioSimple expr, SymbolOperacio oper, codiTresAdreces codi){
        this.expressioSimple = expr;
        this.operacio = oper;
        this.tsOperador = operacio.getTipusSub();
        //setTsResultat();
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO AMB PARENTESIS");
    }
    
    /*
    Constructor expressio Simple sense expressions interiors.
    */
    public SymbolExpressioSimple(SymbolValor val, SymbolOperacio oper, codiTresAdreces codi){
        this.expressioSimple = null;
        this.valor=val;
        this.operacio=oper;
        this.tsOperandor=operacio.getTipusSub();
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO: "+operacio.getTipusSub());
        //if oper is null
        if(oper.isEmpty()){
            this.tsResultat=val.getTipusSub();
            System.out.println("HOLA SOC UNA PRODUCCIÓ ON OPER ÉS NULL: "+this.tsResultat);
        }else{
            System.out.println("HOLAA");
            setTsResultat();
        }
        //else 
    }

    public SymbolExpressioSimple(String id, SymbolVarInit varini, codiTresAdreces codi){
        this.iden=id;
        this.varinit=varinit;
        System.out.println("SOC UN SYMBOL SYMBOLEXPRESSIO");
    }
    public TipusSub tsOperador(){
        return tsOperador;
    }
    public SymbolExpressioSimple getExpr(){
        return expressioSimple;
    }
    
    /*
    tsResultat:
    NULL: si incorrecto
    INT:
    BOOLEAN: correcte per al whileStatement
    STRING: 
    */
    public void setTsResultat(){
        TipusSub x=null;
        
        //Cas 1: expressió sense expressions anteriors
        if(this.expressioSimple==null){
            switch (this.valor.getTipusSub()){
                //Cas Valor Int i operador aritmètic
                case INT:
                    System.out.println("INT CASE: "+operacio.getTipusSub());
                    //Pot ser signe aritmètic o real
                    if(operacio.getTipusSub()==TipusSub.INT){
                        System.out.println("INT CASE2");
                        //signe aritmètic
                        if(operacio.getOperador().getTipusOperador()=='A'){
                            x=TipusSub.INT;
                        //signe real
                        }else{
                            System.out.println("SIGNE REAL!");
                            x=TipusSub.BOOLEAN;
                        }
                    }
                    break;
                case BOOLEAN:
                    //Si signe boolean, expressió correcta amb resultat boolean
                    if(operacio.getTipusSub()==TipusSub.BOOLEAN){
                        x=TipusSub.BOOLEAN;
                    }
                    break;

            }

        //Cas 2: expressió amb expressins dintre ella.
        }else{

            //mentre no quedin expressons per recórrer

                SymbolExpressioSimple exprSimple=new SymbolExpressioSimple(this.expressioSimple,this.expressioSimple.getOperacio());
                if(exprSimple.getTipusSubResultat()==null){
                    //Hi ha hagut un format incorrecte
                    System.out.println("Aquesta expressió és incorrecta!!!!");
                    x=null;
                }

        }
        if(x==null){
            System.out.println("Expressió incorrecta");
        }
        this.tsResultat=x;
    }
    public TipusSub getTipusSubResultat(){
        return this.tsResultat;
    }

    public SymbolOperacio getOperacio() {
        return operacio;
    }
}