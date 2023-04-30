package es.pablosg.gestionobrasfcm.Tareas.GestionMaterial;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Modelos.GestionMaterialesDB;

public class TareaGetGestionMaterial implements Callable<ArrayList<GestionMaterial>> {

    @Override
    public ArrayList<GestionMaterial> call() throws Exception {
        ArrayList<GestionMaterial> gestiones = GestionMaterialesDB.getGestionMateriales();
        return gestiones;
    }
}
