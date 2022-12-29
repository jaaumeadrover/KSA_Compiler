package compiler.GeneracioCodiIntermedi;
import compiler.ArbreSintactic.Operacions;
import  compiler.Symbols.TaulaSimbols.TipusSub;
import java.util.ArrayList;

/*
    CLASSE: TaulaVariables
    AUTOR: ATA2
    FUNCIONALITAT: ens serveix per implementar l'estructura de dades necessària per a poder emmagatzemar les variables.
    DATA CREACIÓ: 26/12/2022
 */
public class TaulaVariables {
    private ArrayList<Variable> TV;
    private int numVar;
    private int numVarTemp;

    public TaulaVariables(){
        this.TV=new ArrayList<>();
        this.numVar=0;
        this.numVarTemp=0;
    }

    public int getNumVar(){
        return this.numVar;
    }

    public int getNumVarTemp(){
        return this.numVarTemp;
    }

    public ArrayList<Variable> getTaulaVariables(){
        return this.TV;
    }

    /*
    Mètode per a afegir una nova variable a la llista.
     */
    public String novaVariable(String nom,TipusSub t) {
        // Nom diferent de null = variable no temporal
        if (nom != null) {
            int i = 0;
            while (i < TV.size()) {
                Variable v = TV.get(i);
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
            Variable novaVar=new Variable("temporal" + nv, t, true, npActius);
            TV.add(novaVar);
            nv++;
            return "t" + nv;
        }
        // Si la nova variable no es temporal (tenia nom) i no es trobava
        // a la taula de variables (no ha fet el return del while) l'afegim a la
        // taula de variables amb el seu nom
        Variable novaVar=new Variable(nom, t, false, npActius);
        TV.add(novaVar);
        return nom + "_" + npActius;
    }

    public int addVariable(Variable v) {

        if (!existeix(v)) {
            numVar++;
            TV.add(v); //mirar que no este duplicada esta variable
        }
        return numVar;
    }

    public boolean existeix(Variable v) {

        if (TV.isEmpty()) {
            if (v.nom == null) {
                numVarTemp++;
                v.nom = "t" + numVarTemp;

            }
        } else {
            if (v.nom == null) {
                numVarTemp++;
                v.nom = "t" + numVarTemp;

            }
        }
        for (int i = 0; i < TV.size(); i++) {
            if (v.nom.equals(TV.get(i).nom)) {
                return true;
            }
        }
        return false;
    }

    /*
        Mètode que ens permet borrar la variable de la taula de variables amb el nom que
        ens passen per paràmetre.
     */
    public void eliminaVariable(String nom) {
        for (int i = 0; i < TV.size(); i++) {
            if (TV.get(i).getNom().equals(nom)) {
                TV.remove(i);
                break;
            }
        }
    }
    /*
        Mètode que ens retorna la variable desitjada, passada per parametre.
     */
    public Variable getVariable(String nom) {
        int div = nom.lastIndexOf("_");
        if (div >= 0) {
            int capa = Integer.parseInt(nom.substring(div + 1));
            String n = nom.substring(0, div);
            for (int i = 0; i < TV.size(); i++) {
                if (TV.get(i).getNom().equals(n) && TV.get(i).getProc() == capa) {
                    return TV.get(i);
                }
            }
        } else {
            for (int i = 0; i < TV.size(); i++) {
                if (TV.get(i).getNom().equals(nom)) {
                    return TV.get(i);
                }
            }
        }
        return null;
    }
    /*
    Mètode que retorna el nom d'una variable afegint l'àmbit en el que l'utilitzam
     */
    public String getNomVariable(String nom) {
        // Anem iterant sobre tots els elements de la taula de variables
        for (int i = 0 ;i < TV.size(); i++){
            Variable v = TV.get(i);
            // Cas on la variable no té processos actius (no s'està utilitzant)
            if (v.getNom().equals(nom) && v.getProc() == 0) {
                if(v.getTemp()){
                    return nom;
                }
                return nom + "_" + 0;

                // Cas on la variable té processos actius (s'està utilitzant)
            }else if (v.getNom().equals(nom) && v.getProc() == npActius){
                if(v.getTemp()){
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