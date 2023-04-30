package es.pablosg.gestionobrasfcm.Tareas.Obra;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Modelos.ObraDB;

public class TareaGetObras implements Callable<ArrayList<Obra>> {

    @Override
    public ArrayList<Obra> call() throws Exception {
        ArrayList<Obra> obras = ObraDB.getObras();
        return obras;
    }
}
