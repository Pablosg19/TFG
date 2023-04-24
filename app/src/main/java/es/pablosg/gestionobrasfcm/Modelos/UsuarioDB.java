package es.pablosg.gestionobrasfcm.Modelos;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Clases.Usuario;

public class UsuarioDB {

    public static ArrayList<Usuario> getUsuarios(){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return null;
        }
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM usuarios ORDER BY ID_USER;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()){
                int ID_USER = resultado.getInt("ID_USER");
                String USER = resultado.getString("USER");
                String NOMBRE = resultado.getString("NOMBRE");
                String CARGO = resultado.getString("CARGO");
                String PASSWORD = resultado.getString("PASSWORD");
                Usuario user = new Usuario(ID_USER, USER, NOMBRE, CARGO, PASSWORD);
                usuarios.add(user);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return usuarios;
        } catch (SQLException e){
            Log.i("sql","error sql obtenerUsuariosDB");
            return usuarios;
        }
    }
}
