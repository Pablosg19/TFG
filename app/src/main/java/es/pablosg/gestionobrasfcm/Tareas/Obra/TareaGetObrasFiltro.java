package es.pablosg.gestionobrasfcm.Tareas.Obra;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Modelos.ObraDB;

public class TareaGetObrasFiltro implements Callable<ArrayList<Obra>> {

    private String filtroObra = null;
    private String filtroLocalizacion = null;

    public TareaGetObrasFiltro(String filtroObra, String filtroLocalizacion) {
        this.filtroObra = filtroObra;
        this.filtroLocalizacion = filtroLocalizacion;
    }

    @Override
    public ArrayList<Obra> call() throws Exception {
        ArrayList<Obra> obras = ObraDB.getObrasFiltro(filtroObra, filtroLocalizacion);
        return obras;
    }
}
