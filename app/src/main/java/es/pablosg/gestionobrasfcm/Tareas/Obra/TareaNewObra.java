package es.pablosg.gestionobrasfcm.Tareas.Obra;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Modelos.ObraDB;

public class TareaNewObra implements Callable<Boolean> {

    private Obra o = null;
    public TareaNewObra(Obra o){
        this.o = o;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean newObraOK = ObraDB.newObra(o);
            return newObraOK;
        } catch (Exception e1){
            return false;
        }
    }
}
