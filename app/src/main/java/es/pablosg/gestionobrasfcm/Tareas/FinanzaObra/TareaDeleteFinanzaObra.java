package es.pablosg.gestionobrasfcm.Tareas.FinanzaObra;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Modelos.FinanzaObraDB;
import es.pablosg.gestionobrasfcm.Modelos.GestionMaterialesDB;

public class TareaDeleteFinanzaObra implements Callable<Boolean> {

    private String OBRA = null;

    public TareaDeleteFinanzaObra(String OBRA){
        this.OBRA = OBRA;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean deleteFinanzaObraOK = FinanzaObraDB.deleteFinanzaObra(OBRA);
            return deleteFinanzaObraOK;
        } catch (Exception e){
            return false;
        }
    }
}
