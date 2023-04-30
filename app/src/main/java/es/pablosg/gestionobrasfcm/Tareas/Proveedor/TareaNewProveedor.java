package es.pablosg.gestionobrasfcm.Tareas.Proveedor;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Proveedor;
import es.pablosg.gestionobrasfcm.Modelos.ProveedorDB;

public class TareaNewProveedor implements Callable<Boolean> {

    private Proveedor p = null;
    public TareaNewProveedor(Proveedor p){
        this.p = p;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean newProveedorOK = ProveedorDB.newProveedor(p);
            return newProveedorOK;
        } catch (Exception e1){
            return false;
        }
    }
}
