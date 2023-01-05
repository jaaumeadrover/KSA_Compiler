package compiler.ArbreSintactic;
import compiler.Symbols.TaulaSimbols.Tipus;
import compiler.Symbols.TaulaSimbols.TipusSub;
import compiler.GeneracioCodiIntermedi.*;

public class SymbolContCap {

    private SymbolContCap contCap;
    private TipusSub t;
    private String id;
    private String arr;
    private Tipus isarray;

    public SymbolContCap(TipusSub t, String arr, String id){
        this.t=t;
        this.arr = arr;
        this.id=id;
        if(arr==null){
            this.isarray=Tipus.VAR;
        }else{
            this.isarray=Tipus.ARRAY;
        }
    }

    public SymbolContCap(SymbolContCap contcap, TipusSub t, String arr, String id){
        this.contCap = contcap;
        this.t=t;
        this.arr = arr;
        this.id=id;
        if(arr==null){
            this.isarray=Tipus.VAR;
        }else{
            this.isarray=Tipus.ARRAY;
        }
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