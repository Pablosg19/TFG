package es.pablosg.gestionobrasfcm.Modelos;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;

public class FinanzaObraDB {

    public static ArrayList<FinanzaObra> getFinanzasObras()
    {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<FinanzaObra> finanzas = new ArrayList<FinanzaObra>();
        try
        {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT obras_ID_OBRA, (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) as OBRA, GASTOS, INGRESOS FROM finanza_obra ORDER BY OBRA;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {

                int ID_OBRA = resultado.getInt("obras_ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                Double GASTOS = resultado.getDouble("GASTOS");
                Double INGRESOS = resultado.getDouble("INGRESOS");
                FinanzaObra fo = new FinanzaObra(ID_OBRA, OBRA, GASTOS, INGRESOS);  //Paso String´s PRECIO_TOTAL calcular en el activity
                finanzas.add(fo);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return finanzas;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getFinanzasObras");
            return finanzas;
        }
    }

    public static boolean newFinanzaObra(FinanzaObra fo) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "INSERT INTO finanzas_obras (obras_ID_OBRA, GASTOS, INGRESOS) VALUES((SELECT ID_OBRA FROM obras WHERE OBRA = ?),?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,fo.getOBRA());
            sentencia.setDouble(2,fo.getGASTOS());
            sentencia.setDouble(3,fo.getINGRESOS());

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

    public static boolean deleteFinanzaObra(String OBRA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "DELETE FROM finanzas_obras WHERE (obras_ID_OBRA = (SELECT ID_OBRA FROM obras WHERE OBRA = ?));";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,OBRA);

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

    public static boolean updateFinanzaObra(FinanzaObra fo, String OBRA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "UPDATE gestion_materiales SET GASTOS = ?, INGRESOS = ? WHERE obras_ID_OBRA = (SELECT ID_OBRA FROM obras, WHERE OBRA = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setDouble(1,fo.getGASTOS());
            sentencia.setDouble(2,fo.getINGRESOS());
            sentencia.setString(3, OBRA);

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

    public static ArrayList<FinanzaObra> getFinanzasObrasFiltro(String filtroOBRA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }

        ArrayList<FinanzaObra> finanzas = new ArrayList<FinanzaObra>();
        try
        {
            filtroOBRA = "%"+filtroOBRA+"%";
            String ordenSQL = "SELECT obras_ID_OBRA, (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) as OBRA, GASTOS, INGRESOS FROM finanza_obra WHERE (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) LIKE ? ORDER BY OBRA;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, filtroOBRA);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int ID_OBRA = resultado.getInt("obras_ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                Double GASTOS = resultado.getDouble("GASTOS");
                Double INGRESOS = resultado.getDouble("INGRESOS");
                FinanzaObra fo = new FinanzaObra(ID_OBRA, OBRA, GASTOS, INGRESOS);
                finanzas.add(fo);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return finanzas;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getFinanzasObrasFiltroDB");
            return finanzas;
        }
    }
}
