package es.pablosg.gestionobrasfcm.Controladores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas.TareaDeleteMovimientoFinanza;
import es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas.TareaGetMovimientoFinanza;
import es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas.TareaGetMovimientoFinanzaFiltro;
import es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas.TareaNewMovimientoFinanza;
import es.pablosg.gestionobrasfcm.Tareas.MovimientosFinanzas.TareaUpdateMovimientoFinanza;


public class MovimientoFinanzaCtrl {

    public static ArrayList<MovimientoFinanza> getMovimientosFinanzas(){
        ArrayList<MovimientoFinanza> movimientos = null;
        FutureTask tarea = new FutureTask(new TareaGetMovimientoFinanza());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            movimientos = (ArrayList<MovimientoFinanza>) tarea.get();
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
        return movimientos;
    }

    public static boolean newMovimientoFinanza(MovimientoFinanza mf){
        FutureTask tarea = new FutureTask(new TareaNewMovimientoFinanza(mf));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean newMovimientoFinanzaOK = false;
        try {
            newMovimientoFinanzaOK = (boolean) tarea.get();
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
            return newMovimientoFinanzaOK ;
        }
    }

    public static boolean deleteMovimientoFinanza(int ID_MOVIMIENTO_FINANZA){
        FutureTask tarea = new FutureTask(new TareaDeleteMovimientoFinanza(ID_MOVIMIENTO_FINANZA));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean deleteMovimientoFinanzaOK = false;
        try {
            deleteMovimientoFinanzaOK = (boolean) tarea.get();
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
            return deleteMovimientoFinanzaOK;
        }
    }

    public static boolean updateGestionMaterial(MovimientoFinanza mf, int ID_MOVIMIENTO_FINANZA){
        FutureTask tarea = new FutureTask(new TareaUpdateMovimientoFinanza(mf,ID_MOVIMIENTO_FINANZA));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean updateMovimientoFinanzaOK = false;
        try {
            updateMovimientoFinanzaOK = (boolean) tarea.get();
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
            return updateMovimientoFinanzaOK;
        }
    }

    public static ArrayList<MovimientoFinanza> getMovimientoFinanzaFiltro(String filtroOBRA){
        ArrayList<MovimientoFinanza> movimientos = null;
        FutureTask tarea = new FutureTask(new TareaGetMovimientoFinanzaFiltro(filtroOBRA));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            movimientos = (ArrayList<MovimientoFinanza>) tarea.get();
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
        return movimientos;
    }
}
