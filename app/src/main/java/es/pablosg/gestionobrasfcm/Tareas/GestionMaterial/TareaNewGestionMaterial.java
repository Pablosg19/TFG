package es.pablosg.gestionobrasfcm.Tareas.GestionMaterial;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Modelos.GestionMaterialesDB;

public class TareaNewGestionMaterial implements Callable<Boolean> {

    private GestionMaterial gm = null;
    public TareaNewGestionMaterial(GestionMaterial gm){
        this.gm = gm;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean newGestionMaterialOK = GestionMaterialesDB.newGestionMaterial(gm);
            return newGestionMaterialOK;
        } catch (Exception e1){
            return false;
        }
    }
}
