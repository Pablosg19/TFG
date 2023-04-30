package es.pablosg.gestionobrasfcm.Tareas.FinanzaObra;

import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Modelos.FinanzaObraDB;
import es.pablosg.gestionobrasfcm.Modelos.GestionMaterialesDB;

public class TareaUpdateFinanzaObra implements Callable<Boolean> {

    private FinanzaObra fo = null;
    private String OBRA = null;

    public TareaUpdateFinanzaObra(FinanzaObra fo, String OBRA){
        this.fo = fo;
        this.OBRA = OBRA;
    }
    @Override
    public Boolean call() throws Exception {
        try {
            boolean updateFinanzaObraOK = FinanzaObraDB.updateFinanzaObra(fo, OBRA);
            return updateFinanzaObraOK;
        } catch (Exception e1){
            return false;
        }
    }
}
