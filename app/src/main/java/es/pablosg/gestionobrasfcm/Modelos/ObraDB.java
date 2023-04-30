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
                int NUM_VIVIENDAS = resultado.getInt("NUM_VIVIENDAS");
                boolean TERMINAR = resultado.getBoolean("TERMINAR");
                Blob PLANO = resultado.getBlob("PLANO");
                Bitmap bm_PLANO;
                Obra o;
                if(PLANO != null){
                    byte[] bfoto = ImagenesBlobBitmap.blob_to_bytes(PLANO);
                    bm_PLANO = ImagenesBlobBitmap.decodeSampledBitmapFrombyteArray(bfoto, ConfiguracionDB.ANCHO_IMAGENES_BITMAP, ConfiguracionDB.ALTO_IMAGENES_BITMAP);
                    o = new Obra(ID_OBRA, OBRA, DIRECCION, LOCALIZACION, NUM_VIVIENDAS, TERMINAR, bm_PLANO);
                }
                else{
                    o = new Obra(ID_OBRA, OBRA, DIRECCION, LOCALIZACION, NUM_VIVIENDAS, TERMINAR,null);

                }
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
            String ordenSQL = "INSERT INTO obras (ID_OBRA, OBRA, DIRECCION, LOCALIZACION, NUM_VIVIENDAS, TERMINAR, PLANO) VALUES('0',?,?,?,?,0,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,o.getOBRA());
            sentencia.setString(2,o.getDIRECCION());
            sentencia.setString(3,o.getLOCALIZACION());
            sentencia.setInt(4,o.getNUM_VIVIENDAS());
            if (o.getPLANO() != null){
                byte[] bl1 = ImagenesBlobBitmap.bitmap_to_bytes_png(o.getPLANO());
                sentencia.setBytes(5,bl1);
            }
            else{
                sentencia.setBytes(5, null);
            }
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

    public static boolean updateObra(Obra o, String ID_OBRA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "UPDATE obras SET OBRA = ?, DIRECCION = ?, LOCALIZACION = ?, NUM_VIVIENDA = ?, TERMINAR = ?, PLANO = ? WHERE ID_OBRA = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,o.getOBRA());
            sentencia.setString(2,o.getDIRECCION());
            sentencia.setString(3,o.getLOCALIZACION());
            sentencia.setInt(4,o.getNUM_VIVIENDAS());
            sentencia.setBoolean(5, o.isTERMINAR());
            if (o.getPLANO() != null){
                byte[] bl1 = ImagenesBlobBitmap.bitmap_to_bytes_png(o.getPLANO());
                sentencia.setBytes(6,bl1);
            }
            sentencia.setString(7, ID_OBRA);
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

    public static ArrayList<Obra> getObrasFiltro(String filtro){
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
        ArrayList<Obra> obras = new ArrayList<Obra>();
        try
        {
            Log.i("filtro",filtro);
            filtro = "%"+filtro+"%";
            String ordenSQL = "SELECT ID_OBRA, OBRA, DIRECCION, LOCALIZACION, NUM_VIVIENDAS, TERMINAR, PLANO FROM obras WHERE OBRA LIKE ? or LOCALIZACION LIKE ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, filtro);
            sentencia.setString(2, filtro);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int ID_OBRA = resultado.getInt("ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                String DIRECCION = resultado.getString("DIRECCION");
                String LOCALIZACION = resultado.getString("LOCALIZACION");
                int NUM_VIVIENDAS = resultado.getInt("NUM_VIVIENDAS");
                boolean TERMINAR = resultado.getBoolean("TERMINAR");
                Blob PLANO = resultado.getBlob("PLANO");
                Bitmap bm_foto;
                Obra o;
                if(PLANO != null){
                    byte[] bfoto = ImagenesBlobBitmap.blob_to_bytes(PLANO);
                    bm_foto = ImagenesBlobBitmap.decodeSampledBitmapFrombyteArray(bfoto, ConfiguracionDB.ANCHO_IMAGENES_BITMAP, ConfiguracionDB.ALTO_IMAGENES_BITMAP);
                    o = new Obra(ID_OBRA, OBRA, DIRECCION, LOCALIZACION, NUM_VIVIENDAS, TERMINAR, bm_foto);
                }
                else{
                    o = new Obra(ID_OBRA, OBRA, DIRECCION, LOCALIZACION, NUM_VIVIENDAS, TERMINAR, null);

                }
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
