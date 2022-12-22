package GeneracioCodiIntermedi;
import ArbreSintactic.operacions;
import  Symbols.TipusSub;
import java.util.ArrayList;

public class codiTresAdreces{

    // Arraylist que emmagatzemarà el codi de 3 adreçes que anem generant
    private  ArrayList<Instruccio> codi=new ArrayList<>();

    // Arraylist que emmagatzemarà les constants del codi per tal de poder accedir a elles
    // durant el procés de generació de codi intermedi
    private  ArrayList<Instruccio> constant=new ArrayList<>();

    //???
    private boolean declaracion = false;

    // Arraylist en el qual anirem montant els subprogrames
    private  ArrayList<Params> parametres=new ArrayList<>();

    // Taula de variables per emmagatzemar descripcions
    private  ArrayList<Variable> TV=new ArrayList<>();
    // nombre de variables existents
    private int nv=0;

    // Taula de procediments per emmagatzemar dades descriptives dels subprogrames
    private  ArrayList<Subprograma> TP=new ArrayList<>();
    // comptador per saber quants de subprogrames es tenen
    private int np=0;
    // Nombre de procediment actius
    private int npActius = 0;

    //comptador d'estiquetes existents
    private int etiquetes=0;

    //Afegim una nova etiqueta i tornam el seu nom (les etiquetes començen per 0)
    public String newEtiqueta() {
        String novaEtiqueta =  "e" + ne;
        ne++;
        return novaEtiqueta
    }

    /*
        MÈTODES RELACIONATS AMB LES VARIABLES
     */

    // Afegeix una nova varioble a la taula de variables
    public String newVariable(TipusSub t, String nom) {
        // Nom diferent de null = variable no temporal
        if (nom != null) {
            int i = 0;
            while (i < tv.size()) {
                Variable v = tv.get(i);
                // Cas on variable dins TV
                if (v.getNom().equals(nom) && v.getProc() == npActius) {
                    return nom + "_" + npActius;
                }
                i++;

            }
        } else {
            // Afegim a la taula de variables una nova varible temporal,
            // ja que com no té nom, no ha estat declarada i sabem que ha estat
            // creada per a la realització del codi de tres adreces
            Variable novaVar=new Variable("temporal" + nv, t, npActius, true)
            tv.add(novaVar);
            return "t" + nv;
            nv++;
        }

        // Si la nova variable no es temporal (tenia nom) i no es trobava
        // a la taula de variables (no ha fet el return del while) l'afegim a la
        // taula de variables amb el seu nom
        Variable novaVar=new Variable(nom, t, npActius, false)
        tv.add(novaVar);
        return nom + "_" + npActius;
    }

    // Retorna el nom d'una variable afegint l'àmbit en el que l'utilitzam
    public String getVarName(String nombre) {
        // Anem iterant sobre tots els elements de la taula de variables
        for (i = 0 ;i < tv.size(); i++){
            Variable v = tv.get(i);
            // Cas on la variable no té processos actius (no s'està utilitzant)
            if (v.getNom().equals(nom) && v.getProc() == 0) {
                if(v.getTemporal()){
                    return nom;
                }
                return nom + "_" + 0;

            // Cas on la variable té processos actius (s'està utilitzant)
            }else if (v.getId().equals(nom) && v.getProc() == npActius){
                if(v.getTemporal()){
                    // si la variable és temporal tan sols retornam el nom
                    return nom;
                }
                //si no és temporal retornam el nom amb el número de processos actius
                return nom + "_" + npActius;
            }
        }

        // si no hem trobat la variabe a la taula de variables retornam 'null'
        return null;
    }
}