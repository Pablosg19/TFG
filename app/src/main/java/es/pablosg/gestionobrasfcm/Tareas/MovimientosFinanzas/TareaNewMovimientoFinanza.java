package es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Modelos.MaterialDB;
import es.pablosg.gestionobrasfcm.Modelos.MovimientoFinanzaDB;

public class TareaNewMovimientoFinanza implements Callable<Boolean> {

    private MovimientoFinanza mf = null;
    public TareaNewMovimientoFinanza(MovimientoFinanza mf){
        this.mf = mf;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean newMovimientoFinanzaOK = MovimientoFinanzaDB.newMovimientoFinanza(mf);
            return newMovimientoFinanzaOK;
        } catch (Exception e1){
            return false;
        }
    }
}
