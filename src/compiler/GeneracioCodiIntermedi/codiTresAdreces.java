package compiler.GeneracioCodiIntermedi;

import compiler.Symbols.TaulaSimbols.TipusSub;
import java.util.ArrayList;
import compiler.ArbreSintactic.Operacions;
import compiler.Symbols.TaulaSimbols.Tipus;

public class codiTresAdreces{
    private ArrayList<Instruccio> codi = new ArrayList<>();
    private ArrayList<Instruccio> constant = new ArrayList<>(); //Declaracion var.
    private ArrayList<Parametre> parametres;
    private boolean esDeclaracio=false;

    private TaulaProcediments tp=new TaulaProcediments();
    private TaulaVariables tv=new TaulaVariables();


    private int nEtiquetes=0;

    public int getSize(){
        return this.codi.size();
    }

    public String novaEtiqueta(){
        nEtiquetes++;
        return "e"+nEtiquetes;
    }
    public String novaEtiqueta(String iden){
        nEtiquetes++;
        return "e"+iden;
    }

    public void generar(TipusInstruccionsCTA a,Operand op1,Operand op2,String dest){
        //si generam una declaració quan no hi ha mètodes actius
        if(esDeclaracio && tp.getNumProcActius()==0){
            constant.add(new Instruccio(a,op1,op2,dest));
        }else{
            //si no declaració o procediment actius>0
            codi.add(new Instruccio(a,op1,op2,dest) );
        }
    }
    public void StartParam(){
        parametres=new ArrayList<>();
    }

    public void addParametre(String id, TipusSub t, Tipus tipus) {
        Parametre p = new Parametre(id,t,tipus);
        tv.novaVariable(id,t,tp,false,1,0);
        parametres.add(p);
    }

    public ArrayList<Parametre> getParametres() {
        return parametres;
    }
    
    //MÈTODE PER AFEGIR UNA VARIABLE
    public String addVariable(TipusSub t, String id){
        int size = tv.getNumVar();
        String num="";
        if(id.equals("t")) {
            num = null;
        }else{
            num = id;
        }
        
        return tv.novaVariable(num,t, tp,false,1,0);
    }
    
    //MÈTODE PER A AFEGIR UNA CONSTANT
    public String addVariable(TipusSub t, String id, int valor){
        int size = tv.getNumVar();
        String num="";
        if(id.equals("t")) {
            num = null;
        }else{
            num = id;
        }

        return tv.novaVariable(num,t, tp,true,valor,0);
    }
    /*
    Mètode per a afegir una variable Array.
        tipus,string,procedure,dimensio
    */
    public String addVariableArray(TipusSub t,String str,int procedure,int dim){
        String s="";
        Variable var=new Variable(t,str,procedure,dim);
        tv.addVariable(var);
        return s;
    }
    
    public Variable getVar(String nom) {
        Variable v=tv.getVariable(nom);
        return v;
    }

    public void començaDecl(){
        generar(TipusInstruccionsCTA.GOTO, null, null, "run");
        this.esDeclaracio=true;
    }

    public void acabaDecl(){
        this.esDeclaracio = false;
        generar(TipusInstruccionsCTA.SKIP, null, null, "run");
        codi.addAll(constant);
        constant.clear();
    }
    //traduirOperacio a operador??

    public TipusSub getTipusOperacio(Operacions op){
        if (op == Operacions.MUL || op == Operacions.DIV || op == Operacions.ADD || op == Operacions.SUB) {
            return TipusSub.INT;
        } else {
            return TipusSub.BOOLEAN;
        }
    }

    public static TipusInstruccionsCTA transforma(Operacions op){
         switch (op) {
                case ADD:
                    return TipusInstruccionsCTA.SUMA;
                case SUB:
                    return TipusInstruccionsCTA.RESTA;
                case MUL:
                    return TipusInstruccionsCTA.PRODUCTE;
                case DIV:
                    return TipusInstruccionsCTA.DIVISIO;
                case MOD:
                    return TipusInstruccionsCTA.MODUL;
                case EQ:
                    return TipusInstruccionsCTA.EQ;
                case NEQ:
                    return TipusInstruccionsCTA.NE;
                case BG:
                    return TipusInstruccionsCTA.GT;
                case SM:
                    return TipusInstruccionsCTA.LT;
                case BGEQ:
                    return TipusInstruccionsCTA.GE;
                case SMEQ:
                    return TipusInstruccionsCTA.LE;
                case OR:
                    return TipusInstruccionsCTA.OR;
                case AND:
                    return TipusInstruccionsCTA.AND;
                case NEG:
                    return TipusInstruccionsCTA.NOT;
            }
            return null;
        }
    

    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i <codi.size(); i++) {
            s += codi.get(i).toString()+"\n";
        }
        return s;
    }

    public TaulaVariables getTv() {
        return tv;
    }
    
    public TaulaProcediments getTp() {
        return tp;
    }
    
    public ArrayList<Instruccio> Instruccions(){
        return codi;
    }
    
}


