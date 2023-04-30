package es.pablosg.gestionobrasfcm.Tareas.GestionMaterial;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Modelos.GestionMaterialesDB;

public class TareaGetGestionMaterialesFiltro implements Callable<ArrayList<GestionMaterial>> {

    private String filtroOBRA = null;
    private String filtroPROVEEDOR = null;
    private String filtroMATERIAL = null;

    public TareaGetGestionMaterialesFiltro(String filtroOBRA, String filtroPROVEEDOR, String filtroMATERIAL) {
        this.filtroOBRA = filtroOBRA;
        this.filtroPROVEEDOR = filtroPROVEEDOR;
        this.filtroMATERIAL = filtroMATERIAL;
    }

    @Override
    public ArrayList<GestionMaterial> call() throws Exception {
        ArrayList<GestionMaterial> gestiones = GestionMaterialesDB.getGestionMaterialesFiltro(filtroOBRA, filtroPROVEEDOR, filtroMATERIAL);
        return gestiones;
    }
}
