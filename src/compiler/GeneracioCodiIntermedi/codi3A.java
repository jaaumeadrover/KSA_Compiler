package compiler.generacioCodiIntermedi

public class codi3A{
    private ArrayList<Instruccion> codi = new ArrayList<>();
    private ArrayList<Instruccion> constant = new ArrayList<>(); //Declaracion var.
    private boolean esDeclaracio=false;

    private TaulaProcediments tp=new TaulaVariables;
    private TaulaVariables tv=new TaulaVariables;

    private int nEtiquetes=0;

    public String novaEtiqueta(){
        nEtiquetes++;
        return "e"+ne;
    }

    public void generar(Operador a,Operand op1,Operand op2,String dest){
        //si generam una declaració quan no hi ha mètodes actius
        if(esDeclaracio && npa==0){
            constant.add(new Instruccio(a,op1,op2,dest));
        }else{
            //si no declaració o procediment actius>0
            codi.add(new Instruccio(a,op1,op2,dest) );
        }
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
}


