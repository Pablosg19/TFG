package es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;
import es.pablosg.gestionobrasfcm.Modelos.MovimientoFinanzaDB;

public class TareaGetMovimientoFinanza implements Callable<ArrayList<MovimientoFinanza>> {

    @Override
    public ArrayList<MovimientoFinanza> call() throws Exception {
        ArrayList<MovimientoFinanza> movimientos = MovimientoFinanzaDB.getMovimientosFinanzas();
        return movimientos;
    }
}
