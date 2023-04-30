package es.pablosg.gestionobrasfcm.Tareas.FinanzaObra;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Modelos.FinanzaObraDB;

public class TareaGetFinanzasObras implements Callable<ArrayList<FinanzaObra>> {

    @Override
    public ArrayList<FinanzaObra> call() throws Exception {
        ArrayList<FinanzaObra> finanzas = FinanzaObraDB.getFinanzasObras();
        return finanzas;
    }
}
