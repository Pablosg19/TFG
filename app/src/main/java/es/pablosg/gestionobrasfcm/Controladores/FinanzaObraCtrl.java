package es.pablosg.gestionobrasfcm.Controladores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Tareas.FinanzaObra.TareaDeleteFinanzaObra;
import es.pablosg.gestionobrasfcm.Tareas.FinanzaObra.TareaGetFinanzasObras;
import es.pablosg.gestionobrasfcm.Tareas.FinanzaObra.TareaGetFinanzasObrasFiltro;
import es.pablosg.gestionobrasfcm.Tareas.FinanzaObra.TareaNewFinanzaObra;
import es.pablosg.gestionobrasfcm.Tareas.FinanzaObra.TareaUpdateFinanzaObra;
import es.pablosg.gestionobrasfcm.Tareas.GestionMaterial.TareaUpdateGestionMaterial;

public class FinanzaObraCtrl {

    public static ArrayList<FinanzaObra> getFinanzasObras(){
        ArrayList<FinanzaObra> finanzas = null;
        FutureTask tarea = new FutureTask(new TareaGetFinanzasObras());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            finanzas = (ArrayList<FinanzaObra>) tarea.get();
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
        return finanzas;
    }

    public static boolean newFinanzaObra(FinanzaObra fo){
        FutureTask tarea = new FutureTask(new TareaNewFinanzaObra(fo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean newFinanzaObraOK = false;
        try {
            newFinanzaObraOK = (boolean) tarea.get();
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
            return newFinanzaObraOK;
        }
    }

    public static boolean deleteFinanzaObra(String OBRA){
        FutureTask tarea = new FutureTask(new TareaDeleteFinanzaObra(OBRA));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean deleteFinanzaObraOK = false;
        try {
            deleteFinanzaObraOK = (boolean) tarea.get();
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
            return deleteFinanzaObraOK;
        }
    }

    public static boolean updateFinanzaObra(FinanzaObra fo, String OBRA){
        FutureTask tarea = new FutureTask(new TareaUpdateFinanzaObra(fo,OBRA));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean updateFinanzaObraOK = false;
        try {
            updateFinanzaObraOK = (boolean) tarea.get();
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
            return updateFinanzaObraOK;
        }
    }

    public static ArrayList<FinanzaObra> getFinanzaObraFiltro(String filtroOBRA){
        ArrayList<FinanzaObra> finanzas = null;
        FutureTask tarea = new FutureTask(new TareaGetFinanzasObrasFiltro(filtroOBRA));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            finanzas = (ArrayList<FinanzaObra>) tarea.get();
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
        return finanzas;
    }
}
