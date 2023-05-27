package es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Modelos.MovimientoFinanzaDB;

public class TareaGetMovimientoFinanzaMostrar implements Callable<ArrayList<MovimientoFinanza>> {

    private String filtroMostrar = null;
    private String OBRA = null;

    public TareaGetMovimientoFinanzaMostrar(String filtroMostrar, String OBRA) {
        this.filtroMostrar = filtroMostrar;
        this.OBRA = OBRA;
    }

    @Override
    public ArrayList<MovimientoFinanza> call() throws Exception {
        ArrayList<MovimientoFinanza> movimientos = MovimientoFinanzaDB.getMovimientosFinanzasMostrar(filtroMostrar, OBRA);
        return movimientos;
    }
}
