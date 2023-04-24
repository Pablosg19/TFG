package es.pablosg.gestionobrasfcm.Tareas.Material;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;

public class TareaNewMaterial implements Callable<Boolean> {

    private Material m = null;
    public TareaNewMaterial(Material m){
        this.m = m;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean newMaterialOK = MaterialDB.newMaterial(m);
            return newMaterialOK;
        } catch (Exception e1){
            return false;
        }
    }
}
