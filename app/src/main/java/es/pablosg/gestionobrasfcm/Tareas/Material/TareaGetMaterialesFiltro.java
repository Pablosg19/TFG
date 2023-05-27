package es.pablosg.gestionobrasfcm.Tareas.Material;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;

public class TareaGetMaterialesFiltro implements Callable<ArrayList<Material>> {

    private String filtroMaterial = null;
    private String filtroFamilia = null;

    public TareaGetMaterialesFiltro(String filtroMaterial, String filtroFamilia) {
        this.filtroFamilia = filtroFamilia;
        this.filtroMaterial = filtroMaterial;
    }

    @Override
    public ArrayList<Material> call() throws Exception {
        ArrayList<Material> materiales = MaterialDB.getMaterialesFiltro(filtroMaterial, filtroFamilia);
        return materiales;
    }
}
