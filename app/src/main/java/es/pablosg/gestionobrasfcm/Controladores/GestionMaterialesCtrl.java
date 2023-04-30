package es.pablosg.gestionobrasfcm.Controladores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Tareas.GestionMaterial.TareaDeleteGestionMaterial;
import es.pablosg.gestionobrasfcm.Tareas.GestionMaterial.TareaGetGestionMaterial;
import es.pablosg.gestionobrasfcm.Tareas.GestionMaterial.TareaGetGestionMaterialesFiltro;
import es.pablosg.gestionobrasfcm.Tareas.GestionMaterial.TareaNewGestionMaterial;
import es.pablosg.gestionobrasfcm.Tareas.GestionMaterial.TareaUpdateGestionMaterial;


public class GestionMaterialesCtrl {

    public static ArrayList<GestionMaterial> getGestionMateriales(){
        ArrayList<GestionMaterial> gestiones = null;
        FutureTask tarea = new FutureTask(new TareaGetGestionMaterial());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            gestiones = (ArrayList<GestionMaterial>) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(2000, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }  catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gestiones;
    }

    public static boolean newGestionMaterial(GestionMaterial gm){
        FutureTask tarea = new FutureTask(new TareaNewGestionMaterial(gm));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean newGestionMaterialOK = false;
        try {
            newGestionMaterialOK = (boolean) tarea.get();
            es.shutdown();
            try{
                if (!es.awaitTermination(800,TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            } catch (InterruptedException e1) {
                es.shutdownNow();
            }
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        finally {
            return newGestionMaterialOK;
        }
    }

    public static boolean deleteGestionMaterial(String ID_GESTION_MATERIAL){
        FutureTask tarea = new FutureTask(new TareaDeleteGestionMaterial(ID_GESTION_MATERIAL));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean deleteGestionMaterialOK = false;
        try {
            deleteGestionMaterialOK = (boolean) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return deleteGestionMaterialOK;
        }
    }

    public static boolean updateGestionMaterial(GestionMaterial gm, int ID_GESTION_MATERIAL){
        FutureTask tarea = new FutureTask(new TareaUpdateGestionMaterial(gm,ID_GESTION_MATERIAL));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean updateGestionMaterialOK = false;
        try {
            updateGestionMaterialOK = (boolean) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e1) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        finally {
            return updateGestionMaterialOK;
        }
    }

    public static ArrayList<GestionMaterial> getGestionMaterialFiltro(String filtroOBRA, String filtroPROVEEDOR, String filtroMATERIAL){
        ArrayList<GestionMaterial> gestiones = null;
        FutureTask tarea = new FutureTask(new TareaGetGestionMaterialesFiltro(filtroOBRA, filtroPROVEEDOR, filtroMATERIAL));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            gestiones = (ArrayList<GestionMaterial>) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(2000, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }  catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gestiones;
    }
}
