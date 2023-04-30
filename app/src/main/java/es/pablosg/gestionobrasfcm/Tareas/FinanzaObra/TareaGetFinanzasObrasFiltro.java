package es.pablosg.gestionobrasfcm.Tareas.FinanzaObra;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Modelos.FinanzaObraDB;

public class TareaGetFinanzasObrasFiltro implements Callable<ArrayList<FinanzaObra>> {

    private String filtroOBRA = null;

    public TareaGetFinanzasObrasFiltro(String filtroOBRA) {
        this.filtroOBRA = filtroOBRA;
    }

    @Override
    public ArrayList<FinanzaObra> call() throws Exception {
        ArrayList<FinanzaObra> finanzas = FinanzaObraDB.getFinanzasObrasFiltro(filtroOBRA);
        return finanzas;
    }
}
