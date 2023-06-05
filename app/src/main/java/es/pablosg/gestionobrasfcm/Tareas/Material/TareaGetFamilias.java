package es.pablosg.gestionobrasfcm.Tareas.Material;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;


public class TareaGetFamilias implements Callable<ArrayList<String>>{
    @Override
    public ArrayList<String> call() throws Exception {
        ArrayList<String> familias = MaterialDB.getFamilias();
        return familias;
    }
}
