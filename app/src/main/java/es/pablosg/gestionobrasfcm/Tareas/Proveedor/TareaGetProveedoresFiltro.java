package es.pablosg.gestionobrasfcm.Tareas.Proveedor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Proveedor;
import es.pablosg.gestionobrasfcm.Modelos.ProveedorDB;

public class TareaGetProveedoresFiltro implements Callable<ArrayList<Proveedor>> {

    private String filtro = null;

    public TareaGetProveedoresFiltro(String filtro) {
        this.filtro = filtro;
    }

    @Override
    public ArrayList<Proveedor> call() throws Exception {
        ArrayList<Proveedor> proveedores = ProveedorDB.getProveedoresFiltro(filtro);
        return proveedores;
    }
}
