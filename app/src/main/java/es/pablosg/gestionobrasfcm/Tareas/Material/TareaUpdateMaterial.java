package es.pablosg.gestionobrasfcm.Tareas.Material;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;


public class TareaUpdateMaterial implements Callable<Boolean> {

    private Material m = null;
    private String MATERIAL = null;

    public TareaUpdateMaterial(Material m, String MATERIAL){
        this.m = m;
        this.MATERIAL = MATERIAL;
    }
    @Override
    public Boolean call() throws Exception {
        try {
            boolean updateMaterialOK = MaterialDB.updateMaterial(m, MATERIAL);
            return updateMaterialOK;
        } catch (Exception e1){
            return false;
        }
    }
}

