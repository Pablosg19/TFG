package es.pablosg.gestionobrasfcm.Tareas.GestionMaterial;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Modelos.GestionMaterialesDB;

public class TareaGetGestionMaterialesFiltro implements Callable<ArrayList<GestionMaterial>> {

    private String filtroOBRA = null;

    public TareaGetGestionMaterialesFiltro(String filtroOBRA) {
        this.filtroOBRA = filtroOBRA;
    }

    @Override
    public ArrayList<GestionMaterial> call() throws Exception {
        ArrayList<GestionMaterial> gestiones = GestionMaterialesDB.getGestionMaterialesFiltro(filtroOBRA);
        return gestiones;
    }
}
