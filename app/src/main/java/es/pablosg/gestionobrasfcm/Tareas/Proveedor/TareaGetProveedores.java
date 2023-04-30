package es.pablosg.gestionobrasfcm.Tareas.Proveedor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Proveedor;
import es.pablosg.gestionobrasfcm.Modelos.ProveedorDB;

public class TareaGetProveedores implements Callable<ArrayList<Proveedor>> {

    @Override
    public ArrayList<Proveedor> call() throws Exception {
        ArrayList<Proveedor> proveedores = ProveedorDB.getProveedores();
        return proveedores;
    }
}
