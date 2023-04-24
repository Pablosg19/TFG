package es.pablosg.gestionobrasfcm.Tareas.Usuario;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.pablosg.gestionobrasfcm.Clases.Usuario;
import es.pablosg.gestionobrasfcm.Modelos.UsuarioDB;

public class TareaGetUsuarios implements Callable<ArrayList<Usuario>> {

    @Override
    public ArrayList<Usuario> call() throws Exception {
        ArrayList<Usuario> usuarios = UsuarioDB.getUsuarios();
        return usuarios;
    }
}
