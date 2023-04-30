package es.pablosg.gestionobrasfcm.Tareas.Obra;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Modelos.ObraDB;

public class TareaGetObrasFiltro implements Callable<ArrayList<Obra>> {

    private String filtro = null;

    public TareaGetObrasFiltro(String filtro) {
        this.filtro = filtro;
    }

    @Override
    public ArrayList<Obra> call() throws Exception {
        ArrayList<Obra> obras = ObraDB.getObrasFiltro(filtro);
        return obras;
    }
}
