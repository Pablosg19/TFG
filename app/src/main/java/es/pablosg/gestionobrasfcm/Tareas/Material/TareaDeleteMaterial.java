package es.pablosg.gestionobrasfcm.Tareas.Material;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;

public class TareaDeleteMaterial implements Callable<Boolean> {

    private String MATERIAL = null;

    public TareaDeleteMaterial(String MATERIAL){
        this.MATERIAL = MATERIAL;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean deleteMaterialOK = MaterialDB.deleteMaterial(MATERIAL);
            return deleteMaterialOK;
        } catch (Exception e){
            return false;
        }
    }
}
