package es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;
import es.pablosg.gestionobrasfcm.Modelos.MovimientoFinanzaDB;

public class TareaGetMovimientoFinanzaFiltro implements Callable<ArrayList<MovimientoFinanza>> {

    private String filtroOBRA = null;

    public TareaGetMovimientoFinanzaFiltro(String filtroOBRA) {
        this.filtroOBRA = filtroOBRA;
    }

    @Override
    public ArrayList<MovimientoFinanza> call() throws Exception {
        ArrayList<MovimientoFinanza> movimientos = MovimientoFinanzaDB.getMovimientosFinanzasFiltro(filtroOBRA);
        return movimientos;
    }
}
