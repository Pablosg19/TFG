package es.pablosg.gestionobrasfcm.Modelos;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;

public class MovimientoFinanzaDB {

    public static ArrayList<MovimientoFinanza> getMovimientosFinanzas()
    {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<MovimientoFinanza> movimientos = new ArrayList<MovimientoFinanza>();
        try
        {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT ID_MOVIMIENTO_FINANZA, obras_ID_OBRA, (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) as OBRA, MOVIMIENTO, DINERO FROM movimientos_finanzas ORDER BY OBRA;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                int ID_MOVIMIENTO_FINANZA = resultado.getInt("ID_MOVIMIENTO_FINANZA");
                int ID_OBRA = resultado.getInt("obras_ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                int MOVIMIENTO = resultado.getInt("MOVIMIENTO");
                Double DINERO = resultado.getDouble("DINERO");

                MovimientoFinanza mf = new MovimientoFinanza(ID_MOVIMIENTO_FINANZA, OBRA, MOVIMIENTO, DINERO);  //Paso String´s PRECIO_TOTAL calcular en el activity
                movimientos.add(mf);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return movimientos;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getMovimientosFinanzas");
            return movimientos;
        }
    }

    public static boolean newMovimientoFinanza(MovimientoFinanza mf) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "SELECT ID_OBRA FROM obras WHERE OBRA = ?";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, mf.getOBRA());
            ResultSet resultado = sentencia.executeQuery();

            int ID_OBRA = 0;
            while (resultado.next()) {
                ID_OBRA = resultado.getInt("ID_OBRA");
            }
            resultado.close();
            sentencia.close();

            ordenSQL = "INSERT INTO movimientos_finanzas (ID_MOVIMIENTO_FINANZA, obras_ID_OBRA, MOVIMIENTO, DINERO) VALUES(0,"+ ID_OBRA +",?,?);";
            sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setInt(1,mf.getMOVIMIENTO());
            sentencia.setDouble(2,mf.getDINERO());
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

    public static boolean deleteMovimientoFinanza(int ID_MOVIMIENTO_FINANZA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "DELETE FROM movimientos_finanzas WHERE (ID_MOVIMIENTO_FINANZA = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setInt(1,ID_MOVIMIENTO_FINANZA);
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

    public static boolean updateMovimientoFinanza(MovimientoFinanza mf, int ID_MOVIMIENTO_FINANZA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "UPDATE movimientos_finanzas SET obras_ID_OBRA = (SELECT ID_OBRA FROM obras WHERE OBRA = ?), MOVIMIENTO = ?, DINERO = ? WHERE ID_MOVIMIENTO_FINANZA = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,mf.getOBRA());
            sentencia.setInt(2,mf.getMOVIMIENTO());
            sentencia.setDouble(3,mf.getDINERO());
            sentencia.setInt(4, ID_MOVIMIENTO_FINANZA);
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

    public static ArrayList<MovimientoFinanza> getMovimientosFinanzasFiltro(String filtroOBRA){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }

        ArrayList<MovimientoFinanza> movimientos = new ArrayList<MovimientoFinanza>();
        try
        {
            filtroOBRA = "%"+filtroOBRA+"%";
            String ordenSQL = "SELECT ID_MOVIMIENTO_FINANZA, obras_ID_OBRA, (SELECT OBRA FROM obras WHERE ID_OBRA = obras_ID_OBRA) as OBRA, MOVIMIENTO, DINERO, FROM movimientos_finanzas WHERE OBRA LIKE ? ORDER BY OBRA;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, filtroOBRA);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int ID_MOVIMIENTO_FINANZA = resultado.getInt("ID_MOVIMIENTO_FINANZA");
                int ID_OBRA = resultado.getInt("obras_ID_OBRA");
                String OBRA = resultado.getString("OBRA");
                int MOVIMIENTO = resultado.getInt("MOVIMIENTO");
                Double DINERO = resultado.getDouble("DINERO");
                MovimientoFinanza mf = new MovimientoFinanza(ID_MOVIMIENTO_FINANZA, OBRA, MOVIMIENTO, DINERO);
                movimientos.add(mf);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return movimientos;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql getMovimientosFinanzasFiltroDB");
            return movimientos;
        }
    }
}
