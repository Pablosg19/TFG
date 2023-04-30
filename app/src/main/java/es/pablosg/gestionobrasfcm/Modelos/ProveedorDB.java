package es.pablosg.gestionobrasfcm.Modelos;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Clases.Proveedor;


public class ProveedorDB {

    public static ArrayList<Proveedor> getProveedores()
    {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        try
        {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT ID_PROVEEDOR, PROVEEDOR FROM provedores ORDER BY PROVEEDOR;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                int ID_PROVEEDOR = resultado.getInt("ID_PROVEEDOR");
                String PROVEEDOR = resultado.getString("PROVEEDOR");


                Proveedor p = new Proveedor(ID_PROVEEDOR, PROVEEDOR);
                proveedores.add(p);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return proveedores;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getProveedoresDB");
            return proveedores;
        }
    }

    public static boolean newProveedor(Proveedor p) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "INSERT INTO proveedores (ID_PROVEEDOR, PROVEEDOR) VALUES('0',?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setInt(1,p.getID_PROVEEDOR());
            sentencia.setString(2,p.getPROVEEDOR());

            int rows = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if (rows > 0){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e1){
            return false;
        }
    }

    public static boolean deleteProveedor(String PROVEEDOR){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "SELECT ID_PROVEEDOR FROM proveedores WHERE PROVEEDOR = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,PROVEEDOR);
            int ID_PROVEEDOR = sentencia.executeUpdate();
            sentencia.close();

            ordenSQL = "DELETE FROM proveedores WHERE ID_PROVEEDOR = '" + ID_PROVEEDOR + "';";
            PreparedStatement sentencia2 = conexion.prepareStatement(ordenSQL);
            int rows = sentencia2.executeUpdate();
            conexion.close();
            if(rows > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }
    public static boolean updateProveedor(Proveedor p, String ID_PROVEEDOR){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "UPDATE proveedores SET PROVEEDOR = ? WHERE ID_PROVEEDOR = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,p.getPROVEEDOR());
            sentencia.setString(2,ID_PROVEEDOR);

            int rows = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(rows > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e1){
            return false;
        }
    }

    public static ArrayList<Proveedor> getProveedoresFiltro(String filtro){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        if (filtro == null){
            Log.i("filtro","nulo");
        }
        else{
            Log.i("filtro", filtro);
        }
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>(

        );
        try
        {
            Log.i("filtro",filtro);
            filtro = "%"+filtro+"%";
            String ordenSQL = "SELECT ID_PROVEEDOR, PROVEEDOR FROM proveedores WHERE PROVEEDOR LIKE ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, filtro);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int ID_PROVEEDOR = resultado.getInt("ID_PROVEEDOR");
                String PROVEEDOR = resultado.getString("PROVEEDOR");

                Proveedor p = new Proveedor(ID_PROVEEDOR, PROVEEDOR);
                proveedores.add(p);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return proveedores;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getProveedoresFiltro");
            return proveedores;
        }
    }


}
