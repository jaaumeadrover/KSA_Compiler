package compiler.GeneracioCodiIntermedi;

import compiler.Symbols.TaulaSimbols.TipusSub;
import java.util.ArrayList;
import compiler.AbreSintactic.Operacions;

public class codi3A{
    private ArrayList<Instruccio> codi = new ArrayList<>();
    private ArrayList<Instruccio> constant = new ArrayList<>(); //Declaracion var.
    private boolean esDeclaracio=false;

    private TaulaProcediments tp=new TaulaProcediments();
    private TaulaVariables tv=new TaulaVariables();

    private int nEtiquetes=0;

    public String novaEtiqueta(){
        nEtiquetes++;
        return "e"+ne;
    }
    public String novaEtiqueta(String iden){
        nEtiquetes++;
        return "e"+iden;
    }

    public void generar(TipusOperacionsCTA a,Operand op1,Operand op2,String dest){
        //si generam una declaració quan no hi ha mètodes actius
        if(esDeclaracio && npa==0){
            constant.add(new Instruccio(a,op1,op2,dest));
        }else{
            //si no declaració o procediment actius>0
            codi.add(new Instruccio(a,op1,op2,dest) );
        }
    }
    public void addParametro(Tipo t, String id) {
        Parametro p = new Parametro(t, id);
        newVariable(t, id);
        param.add(p);
    }

    public void començaDecl(){
        generar(Operador.GOTO, null, null, "run");
        this.esDeclaracio=true;
    }

    public void acabaDecl(){
        this.esDeclaracio = false;
        generar(Operador.SKIP, null, null, "run");
        codi.addAll(constante);
        constante.clear();
    }
    //traduirOperacio a operador??

    public TipusSub getTipusOperacio(){
        if (op == Operador.MULT || op == Operador.DIV || op == Operador.SUMA || op == Operador.RESTA) {
            return TipusSub.INT;
        } else {
            return TipusSub.BOOLEAN;
        }
    }

    public static TipusInstruccioCTA tranforma(Operacio o){
         switch (op) {
                case Operacions.ADD:
                    return TipusInstruccionsCTA.SUMA;
                case Operacions.SUB:
                    return TipusINstruccionsCTA.RESTA;
                case Operacions.MUL:
                    return TipusInstruccionsCTA.PRODUCTE;
                case Operacions.DIV:
                    return TipusInstruccionsCTA.DIVISIO;
                case Operacions.MOD:
                    return TipusInstruccionsCTA.MODUL;
                case Operacions.EQ:
                    return TipusInstruccionsCTA.PRODUCTE;
                case Operacions.BG:
                    return TipusInstruccionsCTA.GT;
                case Operacions.SM:
                    return TipusInstruccionsCTA.LT;
                case Operacions.BGEQ:
                    return TipusInstruccionsCTA.GE;
                case Operacions.SMEQ:
                    return TipusInstruccionsCTA.LE;
                case Operacions.OR:
                    return TipusInstruccionsCTA.OR;
                case Operacions.AND:
                    return TipusInstruccionsCTA.AND;
                case Operacions.NEG:
                    return TipusInstruccionsCTA.NOT;
            }
            return null;
        }
    }

    @Override
    public String toString(){
        s = "";
        for (int i = 0; i <codi.size(); i++) {
            s += codi.get(i).toString+"\n";
        }
        return s;
    }
}


