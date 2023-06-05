package es.pablosg.gestionobrasfcm.Modelos;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;


public class GestionMaterialesDB {

    public static ArrayList<GestionMaterial> getGestionMateriales()
    {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<GestionMaterial> gestiones = new ArrayList<GestionMaterial>();
        try
        {
            Statement sentencia = conexion.createStatement();
            // String ordenSQL = "SELECT ID_GESTION_MATERIALES, obras_ID_OBRA, (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) as OBRA, materiales_ID_MATERIAL, (SELECT MATERIAL FROM materiales WHERE ID_MATERIAL = materiales_ID_MATERIAL) as MATERIAL, PRECIO, CANTIDAD,(PRECIO * CANTIDAD) as PRECIO_TOTAL FROM gestion_materiales ORDER BY OBRA;";
            String ordenSQL = "SELECT ID_GESTION_MATERIALES, obras_ID_OBRA, (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) as OBRA, materiales_ID_MATERIAL, (SELECT MATERIAL FROM materiales WHERE ID_MATERIAL = materiales_ID_MATERIAL) as MATERIAL, PRECIO, CANTIDAD, (SELECT ABREVIATURA_UNIDAD_MEDIDA FROM materiales WHERE ID_MATERIAL = materiales_ID_MATERIAL) AS UNIDAD_MEDIDA,(PRECIO * CANTIDAD) as PRECIO_TOTAL FROM gestion_materiales ORDER BY OBRA;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                int ID_GRESTION_MATERIAL = resultado.getInt("ID_GESTION_MATERIALES");
                int ID_OBRA = resultado.getInt("obras_ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                int ID_MATERIAL = resultado.getInt("materiales_ID_MATERIAL");
                String MATERIAL = resultado.getString("MATERIAL");
                String UNIDAD_MEDIDA = resultado.getString("UNIDAD_MEDIDA");
                Double PRECIO = resultado.getDouble("PRECIO");
                Double CANTIDAD = resultado.getDouble("CANTIDAD");
                Double PRECIO_TOTAL = resultado.getDouble("PRECIO_TOTAL");
               // GestionMaterial gm = new GestionMaterial(ID_GRESTION_MATERIAL, ID_OBRA, ID_MATERIAL, ID_PROVEEDOR, PRECIO, CANTIDAD, PRECIO_TOTAL); // Paso ID´s
                GestionMaterial gm = new GestionMaterial(ID_GRESTION_MATERIAL, OBRA, MATERIAL, UNIDAD_MEDIDA, PRECIO, CANTIDAD);  //Paso String´s PRECIO_TOTAL calcular en el activity
                gestiones.add(gm);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return gestiones;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getGestionMateriales");
            return gestiones;
        }
    }

    public static boolean newGestionMaterial(GestionMaterial gm) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "INSERT INTO gestion_materiales (ID_GESTION_MATERIALES, obras_ID_OBRA, materiales_ID_MATERIAL, PRECIO, CANTIDAD) VALUES('0',(SELECT ID_OBRA FROM obras WHERE OBRA = ?),(SELECT ID_MATERIAL FROM materiales WHERE MATERIAL = ?), ?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,gm.getOBRA());
            sentencia.setString(2,gm.getMATERIAL());
            sentencia.setDouble(3,gm.getPRECIO());
            sentencia.setDouble(4, gm.getCANTIDAD());
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

    public static boolean deleteGestionMaterial(int GESTION_MATERIAL){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "DELETE FROM gestion_materiales WHERE (ID_GESTION_MATERIAL = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setInt(1,GESTION_MATERIAL);

            int rows = sentencia.executeUpdate();
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

    public static boolean updateGestionMaterial(GestionMaterial gm, int ID_GESTION_MATERIAL){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "UPDATE gestion_materiales SET obras_ID_OBRA = (SELECT ID_OBRA FROM obras WHERE OBRA = ?), materiales_ID_MATERIAL = (SELECT ID_MATERIAL FROM materiales WHERE MATERIAL = ?), PRECIO = ?, CANTIDAD = ? WHERE ID_GESTION_MATERIAL = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,gm.getOBRA());
            sentencia.setString(2,gm.getMATERIAL());
            sentencia.setDouble(3,gm.getPRECIO());
            sentencia.setDouble(4,gm.getCANTIDAD());
            sentencia.setInt(5,ID_GESTION_MATERIAL);
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

    public static ArrayList<GestionMaterial> getGestionMaterialesFiltro(String filtroOBRA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }

        ArrayList<GestionMaterial> gestiones = new ArrayList<GestionMaterial>();
        try
        {
            // String ordenSQL = "SELECT ID_GESTION_MATERIALES, obras_ID_OBRA, (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) as OBRA, materiales_ID_MATERIAL, (SELECT MATERIAL FROM materiales WHERE ID_MATERIAL = materiales_ID_MATERIAL) as MATERIAL,(SELECT ABREVIATURA_UNIDAD_MEDIDA FROM materiales WHERE ID_MATERIAL = materiales_ID_MATERIAL) AS UNIDAD_MEDIDA, PRECIO, CANTIDAD,(PRECIO * CANTIDAD) as PRECIO_TOTAL FROM gestion_materiales WHERE obras_ID_OBRA = (SELECT ID_OBRA FROM OBRAS WHERE OBRA = '" + filtroOBRA + "') ORDER BY ID_GESTION_MATERIALES;";
            String ordenSQL = "SELECT distinct(materiales_ID_MATERIAL), (SELECT MATERIAL FROM materiales WHERE ID_MATERIAL = materiales_ID_MATERIAL) as MATERIAL, obras_ID_OBRA, (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) as OBRA, sum(CANTIDAD) AS CANTIDAD,(SELECT ABREVIATURA_UNIDAD_MEDIDA FROM materiales WHERE ID_MATERIAL = materiales_ID_MATERIAL) AS UNIDAD_MEDIDA FROM gestion_materiales WHERE obras_ID_OBRA = (SELECT ID_OBRA FROM OBRAS WHERE OBRA = '" + filtroOBRA + "') GROUP BY materiales_ID_MATERIAL, obras_ID_OBRA;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int ID_OBRA = resultado.getInt("obras_ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                int ID_MATERIAL = resultado.getInt("materiales_ID_MATERIAL");
                String MATERIAL = resultado.getString("MATERIAL");
                String UNIDAD_MEDIDA = resultado.getString("UNIDAD_MEDIDA");
                Double CANTIDAD = resultado.getDouble("CANTIDAD");
                GestionMaterial gm = new GestionMaterial(0, OBRA, MATERIAL, UNIDAD_MEDIDA, null, CANTIDAD);
                gestiones.add(gm);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return gestiones;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getGestionMaterialesFiltroDB");
            return gestiones;
        }
    }
}
