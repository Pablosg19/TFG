package es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;
import es.pablosg.gestionobrasfcm.Modelos.MovimientoFinanzaDB;

public class TareaDeleteMovimientoFinanza implements Callable<Boolean> {

    private int ID_MOVIMIENTO_FINANZA = 0;

    public TareaDeleteMovimientoFinanza(int ID_MOVIMIENTO_FINANZA){
        this.ID_MOVIMIENTO_FINANZA = ID_MOVIMIENTO_FINANZA;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean deleteMovimientoFinanzaOK = MovimientoFinanzaDB.deleteMovimientoFinanza(ID_MOVIMIENTO_FINANZA);
            return deleteMovimientoFinanzaOK;
        } catch (Exception e){
            return false;
        }
    }
}
