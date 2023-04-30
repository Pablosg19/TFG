package es.pablosg.gestionobrasfcm.Tareas.FinanzaObra;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Modelos.FinanzaObraDB;

public class TareaNewFinanzaObra implements Callable<Boolean> {

    private FinanzaObra fo = null;
    public TareaNewFinanzaObra(FinanzaObra fo){
        this.fo = fo;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean newFinanzaObraOK = FinanzaObraDB.newFinanzaObra(fo);
            return newFinanzaObraOK;
        } catch (Exception e1){
            return false;
        }
    }
}
