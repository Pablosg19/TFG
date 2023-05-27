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
                String FAMILIA = resultado.getString("FAMILIA");
                Material m = new Material(ID_MATERIAL, MATERIAL, UNIDAD_MEDIDA, ABREVIATURA_UNIDAD_MEDIDA, FAMILIA);
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
            String ordenSQL = "INSERT INTO materiales (ID_MATERIAL, MATERIAL, UNIDAD_MEDIDA, ABREVIATURA_UNIDAD_MEDIDA, FAMILIA) VALUES('0',?,?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,m.getMATERIAL());
            sentencia.setString(2,m.getUNIDAD_MEDIDA());
            sentencia.setString(3,m.getABREVIATURA_UNIDAD_MEDIDA());
            sentencia.setString(4,m.getFAMILIA());
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

    public static boolean deleteMaterial(String material){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "DELETE FROM materiales WHERE (MATERIAL = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,material);
            int ID_MATERIAL = sentencia.executeUpdate();
            sentencia.close();

            ordenSQL = "DELETE FROM materiales WHERE (MATERIAL = '" + ID_MATERIAL + "';";
            PreparedStatement sentencia2 = conexion.prepareStatement(ordenSQL);

            int rows = sentencia2.executeUpdate();
            sentencia.close();
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

    public static boolean updateMaterial(Material m, String MATERIAL){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "SELECT ID_MATERIAL FROM materiales WHERE MATERIAL = ?";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, MATERIAL);
            ResultSet resultado = sentencia.executeQuery();
            int ID_MATERIAL = 0;
            while (resultado.next()) {
                ID_MATERIAL = resultado.getInt("ID_MATERIAL");
            }
            sentencia.close();
            resultado.close();

            ordenSQL = "UPDATE materiales SET MATERIAL = ?, UNIDAD_MEDIDA = ?, ABREVIATURA_UNIDAD_MEDIDA = ?, FAMILIA = ? WHERE ID_MATERIAL = " + ID_MATERIAL + ";";
            sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,m.getMATERIAL());
            sentencia.setString(2,m.getUNIDAD_MEDIDA());
            sentencia.setString(3,m.getABREVIATURA_UNIDAD_MEDIDA());
            sentencia.setString(4, m.getFAMILIA());
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

    public static ArrayList<Material> getMaterialesFiltro(String filtroMaterial, String filtroFamilia){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<Material> materiales = new ArrayList<Material>();
        try
        {
            filtroMaterial = "%"+filtroMaterial+"%";
            filtroFamilia = "%"+filtroFamilia+"%";
            String ordenSQL = "SELECT * FROM materiales WHERE MATERIAL LIKE ? AND FAMILIA LIKE ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, filtroMaterial);
            sentencia.setString(2, filtroFamilia);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int ID_MATERIAL = resultado.getInt("ID_MATERIAL");
                String MATERIAL = resultado.getString("MATERIAL");
                String UNIDAD_MEDIDA = resultado.getString("UNIDAD_MEDIDA");
                String ABREVIATURA_UNIDAD_MEDIDA = resultado.getString("ABREVIATURA_UNIDAD_MEDIDA");
                String FAMILIA = resultado.getString("FAMILIA");
                Material m = new Material(ID_MATERIAL, MATERIAL, UNIDAD_MEDIDA, ABREVIATURA_UNIDAD_MEDIDA, FAMILIA);
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
