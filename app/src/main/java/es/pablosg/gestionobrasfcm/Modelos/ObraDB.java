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

import es.pablosg.gestionobrasfcm.Bitmap.ImagenesBlobBitmap;
import es.pablosg.gestionobrasfcm.Clases.Obra;

public class ObraDB {

    public static ArrayList<Obra> getObras()
    {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<Obra> obras = new ArrayList<Obra>();
        try
        {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM obras ORDER BY OBRA;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                int ID_OBRA = resultado.getInt("ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                String DIRECCION = resultado.getString("DIRECCION");
                String LOCALIZACION = resultado.getString("LOCALIZACION");
                double PRECIO_TERRENO = resultado.getDouble("PRECIO_TERRENO");
                boolean TERMINAR = resultado.getBoolean("TERMINAR");
                boolean VENDIDA = resultado.getBoolean("VENDIDA");
                Obra o = new Obra(ID_OBRA, OBRA, DIRECCION, LOCALIZACION, PRECIO_TERRENO, TERMINAR, VENDIDA);
                obras.add(o);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return obras;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getObrasDB");
            return obras;
        }
    }

    public static boolean newObra(Obra o) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "INSERT INTO obras (ID_OBRA, OBRA, DIRECCION, LOCALIZACION, PRECIO_TERRENO, TERMINAR, VENDIDA) VALUES(0,?,?,?,?,0,0);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,o.getOBRA());
            sentencia.setString(2,o.getDIRECCION());
            sentencia.setString(3,o.getLOCALIZACION());
            sentencia.setDouble(4,o.getPRECIO_TERRENO());
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

    public static boolean deleteObra(String OBRA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "DELETE FROM obras WHERE (OBRA = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,OBRA);
            int ID_OBRA = sentencia.executeUpdate();
            sentencia.close();

            ordenSQL = "DELETE FROM obras WHERE (OBRA = '" + ID_OBRA + "');";
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

    public static boolean updateObra(Obra o, String OBRA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "SELECT ID_OBRA FROM obras WHERE OBRA = ?";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, OBRA);
            ResultSet resultado = sentencia.executeQuery();
            int ID_OBRA = 0;
            while (resultado.next()) {
                ID_OBRA = resultado.getInt("ID_OBRA");
            }
            sentencia.close();
            resultado.close();

            ordenSQL = "UPDATE obras SET OBRA = ?, DIRECCION = ?, LOCALIZACION = ?, PRECIO_TERRENO = ?, TERMINAR = ?, VENDIDA = ? WHERE ID_OBRA = ?;";
            sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,o.getOBRA());
            sentencia.setString(2,o.getDIRECCION());
            sentencia.setString(3,o.getLOCALIZACION());
            sentencia.setDouble(4,o.getPRECIO_TERRENO());
            sentencia.setBoolean(5, o.isTERMINAR());
            sentencia.setBoolean(6, o.isVENDIDA());
            sentencia.setInt(7, ID_OBRA);
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
            e1.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Obra> getObrasFiltro(String filtroObra, String filtroLocalizacion){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<Obra> obras = new ArrayList<Obra>();
        try
        {
            filtroObra = filtroObra+"%";
            filtroLocalizacion = "%"+filtroLocalizacion+"%";
            String ordenSQL = "SELECT ID_OBRA, OBRA, DIRECCION, LOCALIZACION, PRECIO_TERRENO, TERMINAR, VENDIDA FROM obras WHERE OBRA LIKE ? and LOCALIZACION LIKE ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, filtroObra);
            sentencia.setString(2, filtroLocalizacion);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int ID_OBRA = resultado.getInt("ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                String DIRECCION = resultado.getString("DIRECCION");
                String LOCALIZACION = resultado.getString("LOCALIZACION");
                double PRECIO_TERRENO = resultado.getDouble("PRECIO_TERRENO");
                boolean TERMINAR = resultado.getBoolean("TERMINAR");
                boolean VENDIDA = resultado.getBoolean("VENDIDA");
                Obra o = new Obra(ID_OBRA, OBRA, DIRECCION, LOCALIZACION, PRECIO_TERRENO, TERMINAR, VENDIDA);
                obras.add(o);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return obras;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getObrasFiltro");
            return obras;
        }
    }
}
