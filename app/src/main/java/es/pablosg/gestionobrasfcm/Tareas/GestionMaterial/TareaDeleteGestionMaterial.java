package es.pablosg.gestionobrasfcm.Tareas.GestionMaterial;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Modelos.GestionMaterialesDB;

public class TareaDeleteGestionMaterial implements Callable<Boolean> {

    private int GESTION_MATERIAL = 0;

    public TareaDeleteGestionMaterial(int GESTION_MATERIAL){
        this.GESTION_MATERIAL = GESTION_MATERIAL;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean deleteGestionMaterialOK = GestionMaterialesDB.deleteGestionMaterial(GESTION_MATERIAL);
            return deleteGestionMaterialOK;
        } catch (Exception e){
            return false;
        }
    }
}
