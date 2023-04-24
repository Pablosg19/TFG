package es.pablosg.gestionobrasfcm.Controladores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import es.pablosg.gestionobrasfcm.Clases.Usuario;
import es.pablosg.gestionobrasfcm.Tareas.Usuario.TareaGetUsuarios;

public class UsuarioCtrl {

    public static ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = null;
        FutureTask tarea = new FutureTask(new TareaGetUsuarios());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            usuarios = (ArrayList<Usuario>) tarea.get();
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
        return usuarios;
    }
}
