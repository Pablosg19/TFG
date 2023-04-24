package es.pablosg.gestionobrasfcm.Controladores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Tareas.Material.TareaDeleteMaterial;
import es.pablosg.gestionobrasfcm.Tareas.Material.TareaGetMateriales;
import es.pablosg.gestionobrasfcm.Tareas.Material.TareaGetMaterialesFiltro;
import es.pablosg.gestionobrasfcm.Tareas.Material.TareaNewMaterial;
import es.pablosg.gestionobrasfcm.Tareas.Material.TareaUpdateMaterial;

public class MaterialCtrl {

    public static ArrayList<Material> getMateriales(){
        ArrayList<Material> materiales = null;
        FutureTask tarea = new FutureTask(new TareaGetMateriales());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            materiales = (ArrayList<Material>) tarea.get();
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
        return materiales;
    }

    public static boolean newMaterial(Material m){
        FutureTask tarea = new FutureTask(new TareaNewMaterial(m));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean newMaterialOK = false;
        try {
            newMaterialOK = (boolean) tarea.get();
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
            return newMaterialOK;
        }
    }

    public static boolean deleteMaterial(String material){
        FutureTask tarea = new FutureTask(new TareaDeleteMaterial(material));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean deleteMaterialOK = false;
        try {
            deleteMaterialOK = (boolean) tarea.get();
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
            return deleteMaterialOK;
        }
    }

    public static boolean updateMaterial(Material m, String material){
        FutureTask tarea = new FutureTask(new TareaUpdateMaterial(m,material));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean updateMaterialOK = false;
        try {
            updateMaterialOK = (boolean) tarea.get();
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
            return updateMaterialOK;
        }
    }

    public static ArrayList<Material> getMaterialFiltro(String filtro){
        ArrayList<Material> materiales = null;
        FutureTask tarea = new FutureTask(new TareaGetMaterialesFiltro(filtro));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            materiales = (ArrayList<Material>) tarea.get();
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
        return materiales;
    }
}
