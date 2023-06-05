package es.pablosg.gestionobrasfcm.Tareas.FinanzaObra;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Modelos.FinanzaObraDB;

public class TareaNewFinanzaObra implements Callable<Boolean> {

    private String obra = null;
    public TareaNewFinanzaObra(String obra){
        this.obra = obra;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean newFinanzaObraOK = FinanzaObraDB.newFinanzaObra(obra);
            return newFinanzaObraOK;
        } catch (Exception e1){
            return false;
        }
    }
}
