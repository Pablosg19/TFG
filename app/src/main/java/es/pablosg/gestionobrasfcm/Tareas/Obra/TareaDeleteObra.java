package es.pablosg.gestionobrasfcm.Tareas.Obra;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Modelos.ObraDB;

public class TareaDeleteObra implements Callable<Boolean> {

    private String OBRA = null;

    public TareaDeleteObra(String OBRA){
        this.OBRA = OBRA;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean deleteObraOK = ObraDB.deleteObra(OBRA);
            return deleteObraOK;
        } catch (Exception e){
            return false;
        }
    }
}
