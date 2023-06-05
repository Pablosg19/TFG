package es.pablosg.gestionobrasfcm.Tareas.Material;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;

public class TareaGetMaterial implements Callable<ArrayList<String>> {

    private String familia = null;

    public TareaGetMaterial(String familia) {
        this.familia = familia;
    }

    @Override
    public ArrayList<String> call() throws Exception {
        ArrayList<String> materiales = MaterialDB.getMaterial(familia);
        return materiales;
    }
}
