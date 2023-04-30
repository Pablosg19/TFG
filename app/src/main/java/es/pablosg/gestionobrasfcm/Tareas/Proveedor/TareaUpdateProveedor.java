package es.pablosg.gestionobrasfcm.Tareas.Proveedor;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Proveedor;
import es.pablosg.gestionobrasfcm.Modelos.ProveedorDB;

public class TareaUpdateProveedor implements Callable<Boolean> {

    private Proveedor p = null;
    private String PROVEEDOR = null;

    public TareaUpdateProveedor(Proveedor p, String PROVEEDOR){
        this.p = p;
        this.PROVEEDOR = PROVEEDOR;
    }
    @Override
    public Boolean call() throws Exception {
        try {
            boolean updateProveedorOK = ProveedorDB.updateProveedor(p, PROVEEDOR);
            return updateProveedorOK;
        } catch (Exception e1){
            return false;
        }
    }
}
