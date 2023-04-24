package es.pablosg.gestionobrasfcm.Tareas.Material;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;

public class TareaGetMaterialesFiltro implements Callable<ArrayList<Material>> {

    private String filtro = null;

    public TareaGetMaterialesFiltro(String filtro) {
        this.filtro = filtro;
    }

    @Override
    public ArrayList<Material> call() throws Exception {
        ArrayList<Material> materiales = MaterialDB.getMaterialesFiltro(filtro);
        return materiales;
    }
}
