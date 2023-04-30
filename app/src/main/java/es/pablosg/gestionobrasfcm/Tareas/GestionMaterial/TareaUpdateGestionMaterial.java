package es.pablosg.gestionobrasfcm.Tareas.GestionMaterial;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Modelos.GestionMaterialesDB;

public class TareaUpdateGestionMaterial implements Callable<Boolean> {

    private GestionMaterial gm = null;
    private int GESTION_MATERIAL = 0;

    public TareaUpdateGestionMaterial(GestionMaterial gm, int GESTION_MATERIAL){
        this.gm = gm;
        this.GESTION_MATERIAL = GESTION_MATERIAL;
    }
    @Override
    public Boolean call() throws Exception {
        try {
            boolean updateGestionMaterialOK = GestionMaterialesDB.updateGestionMaterial(gm, GESTION_MATERIAL);
            return updateGestionMaterialOK;
        } catch (Exception e1){
            return false;
        }
    }
}
