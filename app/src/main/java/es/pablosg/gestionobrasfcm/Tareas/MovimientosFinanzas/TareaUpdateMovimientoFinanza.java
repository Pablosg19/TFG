package es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Modelos.MovimientoFinanzaDB;

public class TareaUpdateMovimientoFinanza implements Callable<Boolean> {

    private MovimientoFinanza mf = null;
    private int ID_MOVIMIENTO_FINANZA = 0;

    public TareaUpdateMovimientoFinanza(MovimientoFinanza mf, int ID_MOVIMIENTO_FINANZA){
        this.mf = mf;
        this.ID_MOVIMIENTO_FINANZA = ID_MOVIMIENTO_FINANZA;
    }
    @Override
    public Boolean call() throws Exception {
        try {
            boolean updateMovimientoFinanzaOK = MovimientoFinanzaDB.updateMovimientoFinanza(mf, ID_MOVIMIENTO_FINANZA);
            return updateMovimientoFinanzaOK;
        } catch (Exception e1){
            return false;
        }
    }
}

