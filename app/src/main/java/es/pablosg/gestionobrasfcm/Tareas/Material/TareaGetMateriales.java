package es.pablosg.gestionobrasfcm.Tareas.Material;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;

public class TareaGetMateriales implements Callable<ArrayList<Material>> {

    @Override
    public ArrayList<Material> call() throws Exception {
        ArrayList<Material> materiales = MaterialDB.getMateriales();
        return materiales;
    }
}
