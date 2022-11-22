/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablaSimbolos;

import ArbolSintactico.Tipo;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jaume
 */
public class TaulaSimbols {

    private ArrayList<Simbolo> ts;
    private HashMap<Integer, Simbolo> ta;
    private int nivell;
    private int maxNivells;
    private int ptrIniciFunc;

    public TaulaSimbols(TaulaSimbols prev) {
        //estructures de dades
        ts = new ArrayList<>();
        ta = new HashMap<>();
        //variables
        ptrIniciFunc=0;
        maxNivells=0;
        nivel=0;
    }
    /*
        Funció per afegir el símbol.
        Params: id,tipus variable, tipus subàmbit, posició.
        Return: 0 si no ha funcionat, 1 si funciona
     */
    public int afegeixSimbol(String id,Tipus t,TipusSub tSub,int pos){

            if(tSub==TipusSub.FUNC){//si símbol està dins una funció
                if(){ //si no existeix la funció retorna 0
                    return 0;
                }
                Simbol sym=new Simbol(id,t,nivell,tSub,pos);
                ta.put(nivell,sym);
                ts.add(ptrIniciFunc,sym);
                nivell=0;
                ptrIniciFunc=0;
            }else{
                if(){   //si ja existeix
                    return 0;
                }
                if()tSub == TipusSub.{

                }
            }

    }



}