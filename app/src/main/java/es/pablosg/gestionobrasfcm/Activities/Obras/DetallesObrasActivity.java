package es.pablosg.gestionobrasfcm.Activities.Obras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Activities.Obras.GestionMaterial.GestionObraActivity;
import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Controladores.FinanzaObraCtrl;
import es.pablosg.gestionobrasfcm.Controladores.MovimientoFinanzaCtrl;
import es.pablosg.gestionobrasfcm.Controladores.ObraCtrl;
import es.pablosg.gestionobrasfcm.R;

public class DetallesObrasActivity extends AppCompatActivity {

    public static String NOMBRE_OBRA;

    private TextView txt_Obra;
    private EditText edt_direccion;
    private EditText edt_localizacion;
    private EditText edt_precioTerreno;
    private RadioGroup radioGroupTerminar;
    private RadioButton rb_terminada_si;
    private RadioButton rb_terminada_no;
    private RadioGroup radioGroupVendida;
    private RadioButton rb_vendida_si;
    private RadioButton rb_vendida_no;
    private ImageView img_editar;
    private ImageView img_gestionObras;
    private Button bt_aceptarCambios;
    private Button bt_cancelarCambios;
    private TextView txt_user;
    private EditText edt_precioVenta;
    private ImageView img_volver;

    private Intent intent;

    private Obra o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_obras);

        intent = getIntent();

        txt_Obra = (TextView) findViewById(R.id.txt_detalle_obra);
        edt_direccion = (EditText) findViewById(R.id.edt_detalle_direccion);
        edt_localizacion = (EditText) findViewById(R.id.edt_detalle_localizacion);
        edt_precioTerreno = (EditText) findViewById(R.id.edt_detalle_precioTerreno);
        radioGroupTerminar = (RadioGroup) findViewById(R.id.radioGroupTerminar);
        rb_terminada_si = (RadioButton) findViewById(R.id.rb_detalle_terminada_si);
        rb_terminada_no = (RadioButton) findViewById(R.id.rb_detalle_terminada_no);
        radioGroupVendida = (RadioGroup) findViewById(R.id.radioGroupVendida);
        rb_vendida_si = (RadioButton) findViewById(R.id.rb_detalle_vendida_si);
        rb_vendida_no = (RadioButton) findViewById(R.id.rb_detalle_vendida_no);
        edt_precioVenta = (EditText) findViewById(R.id.edt_detalle_precioVenta);
        img_editar = (ImageView) findViewById(R.id.img_detalle_editar);
        img_gestionObras = (ImageView) findViewById(R.id.img_gestionObra);
        bt_aceptarCambios = (Button) findViewById(R.id.bt_detalle_aceptarCambios);
        bt_cancelarCambios = (Button) findViewById(R.id.bt_detalle_cancelarCambios);
        txt_user = (TextView) findViewById(R.id.txt_detalle_user);
        img_volver = (ImageView) findViewById(R.id.img_detalle_back);

        txt_user.setText(Login.USUARIO_INTRODUCIDO);
        if(Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.jefe) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.JefeObra) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.AgenteInmobiliario) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.admin)){
            img_editar.setVisibility(View.VISIBLE);
        } else{
            img_editar.setVisibility(View.INVISIBLE);
        }
        bt_aceptarCambios.setVisibility(View.INVISIBLE);
        bt_cancelarCambios.setVisibility(View.INVISIBLE);

        if(Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.jefe) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.JefeObra) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.admin)) {
            img_gestionObras.setVisibility(View.VISIBLE);
        } else{
            img_gestionObras.setVisibility(View.INVISIBLE);
        }
        o = (Obra) intent.getParcelableExtra(ObrasViewHolder.EXTRA_OBJETO_OBRA);

        NOMBRE_OBRA = o.getOBRA();
        txt_Obra.setText(o.getOBRA());
        edt_direccion.setText(o.getDIRECCION());
        edt_localizacion.setText(o.getLOCALIZACION());
        edt_precioTerreno.setText(ObrasActivity.format.format(o.getPRECIO_TERRENO()));
        if (o.isTERMINAR()){
            img_gestionObras.setVisibility(View.INVISIBLE);
            radioGroupTerminar.check(R.id.rb_detalle_terminada_si);
        }
        else {
            radioGroupTerminar.check(R.id.rb_detalle_terminada_no);
        }
        if(o.isVENDIDA()){
            radioGroupVendida.check(R.id.rb_detalle_vendida_si);
        }
        else {
            radioGroupVendida.check(R.id.rb_detalle_vendida_no);
        }

        if(o.isVENDIDA() && o.isTERMINAR()){
            img_editar.setVisibility(View.INVISIBLE);
            img_gestionObras.setVisibility(View.INVISIBLE);
            edt_precioVenta.setVisibility(View.INVISIBLE);
        }
    }

    public void editarObra(View view) {
        bt_aceptarCambios.setError(null);

        img_gestionObras.setVisibility(View.INVISIBLE);
        img_editar.setVisibility(View.INVISIBLE);
        bt_aceptarCambios.setVisibility(View.VISIBLE);
        bt_cancelarCambios.setVisibility(View.VISIBLE);

        String cargo = Login.CARGO_USUARIO;
        if(o.isTERMINAR()){
            if (cargo.equalsIgnoreCase(ObrasActivity.AgenteInmobiliario) || cargo.equalsIgnoreCase(ObrasActivity.admin)){
                rb_vendida_si.setEnabled(true);
                rb_vendida_no.setEnabled(true);
                edt_precioVenta.setEnabled(true);
            }
        }
        else{
            if(cargo.equalsIgnoreCase(ObrasActivity.jefe) || cargo.equalsIgnoreCase(ObrasActivity.JefeObra)){
                edt_localizacion.setEnabled(true);
                edt_direccion.setEnabled(true);
                edt_precioTerreno.setEnabled(true);
                rb_terminada_no.setEnabled(true);
                rb_terminada_si.setEnabled(true);
            }
            else if (cargo.equalsIgnoreCase(ObrasActivity.AgenteInmobiliario)){
                rb_vendida_si.setEnabled(true);
                rb_vendida_no.setEnabled(true);
                edt_precioVenta.setEnabled(true);
            }
            else if (cargo.equalsIgnoreCase(ObrasActivity.admin)){
                edt_localizacion.setEnabled(true);
                edt_direccion.setEnabled(true);
                edt_precioTerreno.setEnabled(true);
                rb_terminada_no.setEnabled(true);
                rb_terminada_si.setEnabled(true);
                rb_vendida_si.setEnabled(true);
                rb_vendida_no.setEnabled(true);
                edt_precioVenta.setEnabled(true);
            }
        }
    }

    public void cancelCambios(View view) {
        img_gestionObras.setVisibility(View.VISIBLE);
        img_editar.setVisibility(View.VISIBLE);
        bt_aceptarCambios.setVisibility(View.INVISIBLE);
        bt_cancelarCambios.setVisibility(View.INVISIBLE);

        edt_localizacion.setEnabled(false);
        edt_direccion.setEnabled(false);
        edt_precioTerreno.setEnabled(false);
        rb_terminada_no.setEnabled(false);
        rb_terminada_si.setEnabled(false);
        rb_vendida_si.setEnabled(false);
        rb_vendida_no.setEnabled(false);
        edt_precioVenta.setEnabled(false);

        txt_Obra.setText(o.getOBRA());
        edt_direccion.setText(o.getDIRECCION());
        edt_localizacion.setText(o.getLOCALIZACION());
        edt_precioTerreno.setText(ObrasActivity.format.format(o.getPRECIO_TERRENO()));
        edt_precioVenta.setText(null);
        if (o.isTERMINAR()){
            radioGroupTerminar.check(R.id.rb_detalle_terminada_si);
        }
        else {
            radioGroupTerminar.check(R.id.rb_detalle_terminada_no);
        }
        if(o.isVENDIDA()){
            radioGroupVendida.check(R.id.rb_detalle_vendida_si);
        }
        else {
            radioGroupVendida.check(R.id.rb_detalle_vendida_no);
        }
    }

    public void updateObra(View view) {
        Double precioTerrenoAnterior = o.getPRECIO_TERRENO();

        String obra = String.valueOf(txt_Obra.getText());
        String direccion = String.valueOf(edt_direccion.getText());
        String localizacion = String.valueOf(edt_localizacion.getText());
        String precioTerreno = String.valueOf(edt_precioTerreno.getText());
        String precioVenta = String.valueOf(edt_precioVenta.getText());
        boolean terminada = false;
        if(rb_terminada_si.isChecked()){
            terminada = true;
        }
        boolean vendida = false;
        if(rb_vendida_si.isChecked()){
            vendida = true;
        }

        // Comprobacion de que los campos estan rellenos
        if(obra.isEmpty() || direccion.isEmpty() || localizacion.isEmpty() || precioTerreno.isEmpty()){
                bt_aceptarCambios.setError("Debes rellenar todos los campos");
        }
        else{
            ObrasActivity.format.format(Double.valueOf(precioTerreno));

            AlertDialog.Builder updateObra = new AlertDialog.Builder(this);
            updateObra.setTitle("¿Quieres actualizar los datos de " + obra + "?");
            boolean finalTerminada = terminada;
            boolean finalVendida = vendida;
            if (finalTerminada){
                img_gestionObras.setVisibility(View.INVISIBLE);
            }
            if(finalVendida){
                if (precioVenta.isEmpty()){
                    bt_aceptarCambios.setError("Debes rellenar todos los campos. Si la obra ha sido vendida indica el precio de venta.");
                }
                else{
                    updateObra.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            o = new Obra(0, obra, direccion, localizacion, Double.valueOf(precioTerreno) , finalTerminada, finalVendida);
                            boolean updateObraOK = ObraCtrl.updateObra(o, obra);
                            if(updateObraOK){
                                int movimiento = 0;
                                Double dinero = precioTerrenoAnterior - Double.valueOf(precioTerreno);
                                if(Double.valueOf(precioTerreno) > precioTerrenoAnterior){
                                    movimiento = 1;
                                    dinero = Double.valueOf(precioTerreno) - precioTerrenoAnterior;
                                    MovimientoFinanza mf = new MovimientoFinanza(0,obra,movimiento,dinero);
                                    MovimientoFinanzaCtrl.newMovimientoFinanza(mf);
                                } else if (dinero == 0) {
                                    // No hacemos movimientoFinanza
                                }
                                else{
                                    MovimientoFinanza mf = new MovimientoFinanza(0,obra,movimiento,dinero);
                                    MovimientoFinanzaCtrl.newMovimientoFinanza(mf);
                                }

                                if (finalTerminada && finalVendida) {
                                    Double venta = Double.valueOf(precioVenta);
                                    MovimientoFinanza mf2 = new MovimientoFinanza(0, obra, 0, venta);
                                    boolean newMovimientoVenderOK = MovimientoFinanzaCtrl.newMovimientoFinanza(mf2);
                                    if (newMovimientoVenderOK) {
                                        boolean newFinanzaOK = FinanzaObraCtrl.newFinanzaObra(obra);
                                        if (newFinanzaOK) {
                                            mostrarMensaje(newFinanzaOK, obra);
                                        } else {
                                            MovimientoFinanza mf3 = new MovimientoFinanza(0, obra, 1, venta); // Se crea en negativo para igualarlo a 0 como no se han creado bien todos los booleans
                                            MovimientoFinanzaCtrl.newMovimientoFinanza(mf3);
                                            mostrarMensaje(newFinanzaOK, obra);
                                        }
                                    }
                                }
                                edt_precioVenta.setText(null);
                            }
                            else{
                                mostrarMensaje(updateObraOK, obra);
                                edt_precioVenta.setText(null);
                            }
                        }
                    });
                    updateObra.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            txt_Obra.setText(o.getOBRA());
                            edt_direccion.setText(o.getDIRECCION());
                            edt_localizacion.setText(o.getLOCALIZACION());
                            edt_precioTerreno.setText(ObrasActivity.format.format(o.getPRECIO_TERRENO()));
                            if (o.isTERMINAR()){
                                radioGroupTerminar.check(R.id.rb_detalle_terminada_si);
                            }
                            else {
                                radioGroupTerminar.check(R.id.rb_detalle_terminada_no);
                            }
                            if(o.isVENDIDA()){
                                radioGroupVendida.check(R.id.rb_detalle_vendida_si);
                            }
                            else {
                                radioGroupVendida.check(R.id.rb_detalle_vendida_no);
                            }
                        }
                    });
                    updateObra.show();

                    img_gestionObras.setVisibility(View.VISIBLE);
                    img_editar.setVisibility(View.VISIBLE);
                    bt_aceptarCambios.setVisibility(View.INVISIBLE);
                    bt_cancelarCambios.setVisibility(View.INVISIBLE);

                    edt_localizacion.setEnabled(false);
                    edt_direccion.setEnabled(false);
                    edt_precioTerreno.setEnabled(false);
                    rb_terminada_no.setEnabled(false);
                    rb_terminada_si.setEnabled(false);
                    rb_vendida_si.setEnabled(false);
                    rb_vendida_no.setEnabled(false);
                    edt_precioVenta.setEnabled(false);
                }
            }
            else{
                updateObra.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        o = new Obra(0, obra, direccion, localizacion, Double.valueOf(precioTerreno) , finalTerminada, finalVendida);
                        boolean updateObraOK = ObraCtrl.updateObra(o, obra);
                        if(updateObraOK){
                            int movimiento = 0;
                            Double dinero = precioTerrenoAnterior - Double.valueOf(precioTerreno);
                            if(Double.valueOf(precioTerreno) > precioTerrenoAnterior){
                                movimiento = 1;
                                dinero = Double.valueOf(precioTerreno) - precioTerrenoAnterior;
                                MovimientoFinanza mf = new MovimientoFinanza(0,obra,movimiento,dinero);
                                MovimientoFinanzaCtrl.newMovimientoFinanza(mf);
                            } else if (dinero == 0) {
                                // No hacemos movimientoFinanza
                            }
                            else{
                                MovimientoFinanza mf = new MovimientoFinanza(0,obra,movimiento,dinero);
                                MovimientoFinanzaCtrl.newMovimientoFinanza(mf);
                            }

                            if (finalTerminada && finalVendida) {
                                Double venta = Double.valueOf(precioVenta);
                                MovimientoFinanza mf2 = new MovimientoFinanza(0, obra, 0, venta);
                                boolean newMovimientoVenderOK = MovimientoFinanzaCtrl.newMovimientoFinanza(mf2);
                                if (newMovimientoVenderOK) {
                                    boolean newFinanzaOK = FinanzaObraCtrl.newFinanzaObra(obra);
                                    if (newFinanzaOK) {
                                        mostrarMensaje(newFinanzaOK, obra);
                                    } else {
                                        MovimientoFinanza mf3 = new MovimientoFinanza(0, obra, 1, venta); // Se crea en negativo para igualarlo a 0 como no se han creado bien todos los booleans
                                        MovimientoFinanzaCtrl.newMovimientoFinanza(mf3);
                                        mostrarMensaje(newFinanzaOK, obra);
                                    }
                                }
                            }
                            edt_precioVenta.setText(null);
                        }
                        else{
                            mostrarMensaje(updateObraOK, obra);
                            edt_precioVenta.setText(null);
                        }
                    }
                });
                updateObra.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txt_Obra.setText(o.getOBRA());
                        edt_direccion.setText(o.getDIRECCION());
                        edt_localizacion.setText(o.getLOCALIZACION());
                        edt_precioTerreno.setText(ObrasActivity.format.format(o.getPRECIO_TERRENO()));
                        if (o.isTERMINAR()){
                            radioGroupTerminar.check(R.id.rb_detalle_terminada_si);
                        }
                        else {
                            radioGroupTerminar.check(R.id.rb_detalle_terminada_no);
                        }
                        if(o.isVENDIDA()){
                            radioGroupVendida.check(R.id.rb_detalle_vendida_si);
                        }
                        else {
                            radioGroupVendida.check(R.id.rb_detalle_vendida_no);
                        }
                    }
                });
                updateObra.show();

                img_gestionObras.setVisibility(View.VISIBLE);
                img_editar.setVisibility(View.VISIBLE);
                bt_aceptarCambios.setVisibility(View.INVISIBLE);
                bt_cancelarCambios.setVisibility(View.INVISIBLE);

                edt_localizacion.setEnabled(false);
                edt_direccion.setEnabled(false);
                edt_precioTerreno.setEnabled(false);
                rb_terminada_no.setEnabled(false);
                rb_terminada_si.setEnabled(false);
                rb_vendida_si.setEnabled(false);
                rb_vendida_no.setEnabled(false);
                edt_precioVenta.setEnabled(false);
            }
        }
    }

    public void mostrarMensaje(boolean updateOK, String obra){
        if (updateOK){
            Toast.makeText(this, "Datos de " + obra + " actualizados correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ObrasActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "No se han podido actualizar los datos de "+ obra ,Toast.LENGTH_LONG).show();
        }
    }

    public void gestionarObra(View view){
        Intent intent = new Intent(this, GestionObraActivity.class);
        startActivity(intent);
    }

    public void volver(View view){
        Intent intent = new Intent(this, ObrasActivity.class);
        startActivity(intent);
    }
}