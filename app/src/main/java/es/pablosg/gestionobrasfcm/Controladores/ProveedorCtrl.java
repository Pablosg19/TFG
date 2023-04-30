package es.pablosg.gestionobrasfcm.Controladores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


import es.pablosg.gestionobrasfcm.Clases.Proveedor;
import es.pablosg.gestionobrasfcm.Tareas.Proveedor.TareaDeleteProveedor;
import es.pablosg.gestionobrasfcm.Tareas.Proveedor.TareaGetProveedores;
import es.pablosg.gestionobrasfcm.Tareas.Proveedor.TareaGetProveedoresFiltro;
import es.pablosg.gestionobrasfcm.Tareas.Proveedor.TareaNewProveedor;
import es.pablosg.gestionobrasfcm.Tareas.Proveedor.TareaUpdateProveedor;


public class ProveedorCtrl {

    public static ArrayList<Proveedor> getProveedor(){
        ArrayList<Proveedor> proveedores = null;
        FutureTask tarea = new FutureTask(new TareaGetProveedores());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            proveedores = (ArrayList<Proveedor>) tarea.get();
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
        return proveedores;
    }

    public static boolean newProveedor(Proveedor p){
        FutureTask tarea = new FutureTask(new TareaNewProveedor(p));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean newProveedorOK = false;
        try {
            newProveedorOK = (boolean) tarea.get();
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
            return newProveedorOK;
        }
    }

    public static boolean deleteProveedor(String PROVEEDOR){
        FutureTask tarea = new FutureTask(new TareaDeleteProveedor(PROVEEDOR));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean deleteProveedorOK = false;
        try {
            deleteProveedorOK = (boolean) tarea.get();
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
            return deleteProveedorOK;
        }
    }

    public static boolean updateProveedor(Proveedor p, String PROVEEDOR){
        FutureTask tarea = new FutureTask(new TareaUpdateProveedor(p, PROVEEDOR));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean updateProveedorOK = false;
        try {
            updateProveedorOK = (boolean) tarea.get();
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
            return updateProveedorOK;
        }
    }

    public static ArrayList<Proveedor> getProveedorFiltro(String filtro){
        ArrayList<Proveedor> proveedores = null;
        FutureTask tarea = new FutureTask(new TareaGetProveedoresFiltro(filtro));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            proveedores = (ArrayList<Proveedor>) tarea.get();
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
        return proveedores;
    }
}
