package es.pablosg.gestionobrasfcm.Modelos;

import android.graphics.Bitmap;
import android.util.Log;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Clases.Material;

public class MaterialDB {

    public static ArrayList<Material> getMateriales()
    {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<Material> materiales = new ArrayList<Material>();
        try
        {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM materiales ORDER BY MATERIAL;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                int ID_MATERIAL = resultado.getInt("ID_MATERIAL");
                String MATERIAL = resultado.getString("MATERIAL");
                String UNIDAD_MEDIDA = resultado.getString("UNIDAD_MEDIDA");
                String ABREVIATURA_UNIDAD_MEDIDA = resultado.getString("ABREVIATURA_UNIDAD_MEDIDA");
                Material m = new Material(ID_MATERIAL, MATERIAL, UNIDAD_MEDIDA, ABREVIATURA_UNIDAD_MEDIDA);
                materiales.add(m);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return materiales;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getMateriales");
            return materiales;
        }
    }

    public static boolean newMaterial(Material m) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "INSERT INTO materiales (ID_MATERIAL, MATERIAL, UNIDAD_MEDIDA, ABREVIATURA_UNIDAD_MEDIDA) VALUES('0',?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,m.getMATERIAL());
            sentencia.setString(2,m.getUNIDAD_MEDIDA());
            sentencia.setString(3,m.getABREVIATURA_UNIDAD_MEDIDA());
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if (filasAfectadas > 0){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e1){
            return false;
        }
    }

    public static boolean deleteMaterial(String material){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "DELETE FROM materiales WHERE (MATERIAL = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,material);
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean updateMaterial(Material m, String ID_MATERIAL){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "UPDATE materiales SET MATERIAL = ?, UNIDAD_MEDIDA = ?, ABREVIATURA_UNIDAD_MEDIDA = ? WHERE ID_MATERIAL = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,m.getMATERIAL());
            sentencia.setString(2,m.getUNIDAD_MEDIDA());
            sentencia.setString(3,m.getABREVIATURA_UNIDAD_MEDIDA());
            sentencia.setString(4,ID_MATERIAL);
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e1){
            return false;
        }
    }

    public static ArrayList<Material> getMaterialesFiltro(String filtro){
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
        ArrayList<Material> materiales = new ArrayList<Material>();
        try
        {
            Log.i("filtro",filtro);
            filtro = "%"+filtro+"%";
            String ordenSQL = "SELECT * FROM materiales WHERE MATERIAL LIKE ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, filtro);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int ID_MATERIAL = resultado.getInt("ID_MATERIAL");
                String MATERIAL = resultado.getString("MATERIAL");
                String UNIDAD_MEDIDA = resultado.getString("UNIDAD_MEDIDA");
                String ABREVIATURA_UNIDAD_MEDIDA = resultado.getString("ABREVIATURA_UNIDAD_MEDIDA");
                Material m = new Material(ID_MATERIAL, MATERIAL, UNIDAD_MEDIDA, ABREVIATURA_UNIDAD_MEDIDA);
                materiales.add(m);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return materiales;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getMaterialesFiltroDB");
            return materiales;
        }
    }
}
