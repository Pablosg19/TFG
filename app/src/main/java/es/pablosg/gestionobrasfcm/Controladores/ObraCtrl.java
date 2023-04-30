package es.pablosg.gestionobrasfcm.Controladores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Tareas.Obra.TareaDeleteObra;
import es.pablosg.gestionobrasfcm.Tareas.Obra.TareaGetObras;
import es.pablosg.gestionobrasfcm.Tareas.Obra.TareaGetObrasFiltro;
import es.pablosg.gestionobrasfcm.Tareas.Obra.TareaNewObra;
import es.pablosg.gestionobrasfcm.Tareas.Obra.TareaUpdateObra;

public class ObraCtrl {

    public static ArrayList<Obra> getObras(){
        ArrayList<Obra> obras = null;
        FutureTask tarea = new FutureTask(new TareaGetObras());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            obras = (ArrayList<Obra>) tarea.get();
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
        return obras;
    }

    public static boolean newObra(Obra o){
        FutureTask tarea = new FutureTask(new TareaNewObra(o));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean newObraOK = false;
        try {
            newObraOK = (boolean) tarea.get();
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
            return newObraOK;
        }
    }

    public static boolean deleteObra(String OBRA){
        FutureTask tarea = new FutureTask(new TareaDeleteObra(OBRA));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean deleteObraOK = false;
        try {
            deleteObraOK = (boolean) tarea.get();
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
            return deleteObraOK;
        }
    }

    public static boolean updateObra(Obra o, String OBRA){
        FutureTask tarea = new FutureTask(new TareaUpdateObra(o, OBRA));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean updateObraOK = false;
        try {
            updateObraOK = (boolean) tarea.get();
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
            return updateObraOK;
        }
    }

    public static ArrayList<Obra> getObrasFiltro(String filtro){
        ArrayList<Obra> obras = null;
        FutureTask tarea = new FutureTask(new TareaGetObrasFiltro(filtro));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            obras = (ArrayList<Obra>) tarea.get();
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
        return obras;
    }
}

