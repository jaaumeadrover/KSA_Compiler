package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;
import compiler.GeneracioCodiIntermedi.*;
import java.util.ArrayList;

public class SymbolContCap {

    private SymbolContCap contCap;
    private TipusSub t;
    private String id;
    private String arr;
    private Tipus isarray;
    private ArrayList<TipusSub> tipusSub = new ArrayList<TipusSub>();
    private ArrayList<Tipus> tipus = new ArrayList<Tipus>();

    public SymbolContCap(TipusSub t, String arr, String id){
        this.t=t;
        this.arr = arr;
        this.id=id;
        if(arr==null){
            this.isarray = Tipus.VAR;
            tipus.add(Tipus.VAR);
            tipusSub.add(t);
        }else{
            tipusSub.add(t);
            tipus.add(Tipus.ARRAY);
            this.isarray=Tipus.ARRAY;
        }
    }

    public SymbolContCap(SymbolContCap contcap, TipusSub t, String arr, String id){
        this.contCap = contcap;
        this.t=t;
        this.arr = arr;
        this.id=id;
        if(arr==null){
            this.isarray= Tipus.VAR;
            tipus.add(Tipus.VAR);
            tipusSub.add(t);
        }else{
            tipusSub.add(t);
            tipus.add(Tipus.ARRAY);
            this.isarray=Tipus.ARRAY;
        }
    }

    public ArrayList<Tipus> getTipusParam() {
        return tipus;
    }
    public ArrayList<TipusSub> getTipusSubParam(){
        return tipusSub;
    }

    public TipusSub getTipusSub() {
        return t;
    }

    public void codiTresAdreces(codiTresAdreces codi){
        // Generar codi tres adreces amb els mètodes
        codi.addParametre(id,t,isarray);
        if(contCap!=null){
            contCap.codiTresAdreces(codi);
        }

    }

}