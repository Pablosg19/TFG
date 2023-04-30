package es.pablosg.gestionobrasfcm.Tareas.Obra;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Modelos.ObraDB;

public class TareaUpdateObra implements Callable<Boolean> {

    private Obra o = null;
    private String OBRA = null;

    public TareaUpdateObra(Obra o, String OBRA){
        this.o = o;
        this.OBRA = OBRA;
    }
    @Override
    public Boolean call() throws Exception {
        try {
            boolean updateObraOK = ObraDB.updateObra(o, OBRA);
            return updateObraOK;
        } catch (Exception e1){
            return false;
        }
    }
}
