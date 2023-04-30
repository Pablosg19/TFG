package es.pablosg.gestionobrasfcm.Tareas.Proveedor;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Modelos.ProveedorDB;

public class TareaDeleteProveedor implements Callable<Boolean> {

    private String PROVEEDOR = null;

    public TareaDeleteProveedor(String PROVEEDOR){
        this.PROVEEDOR = PROVEEDOR;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean deleteProveedorOK = ProveedorDB.deleteProveedor(PROVEEDOR);
            return deleteProveedorOK;
        } catch (Exception e){
            return false;
        }
    }
}
