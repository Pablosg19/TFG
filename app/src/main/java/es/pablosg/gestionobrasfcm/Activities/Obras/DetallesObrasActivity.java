package es.pablosg.gestionobrasfcm.Activities.Obras;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Controladores.ObraCtrl;
import es.pablosg.gestionobrasfcm.R;

public class DetallesObrasActivity extends AppCompatActivity {

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
    private Button bt_aceptarCambios;
    private Button bt_cancelarCambios;
    private TextView txt_user;

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
        img_editar = (ImageView) findViewById(R.id.img_detalle_editar);
        bt_aceptarCambios = (Button) findViewById(R.id.bt_detalle_aceptarCambios);
        bt_cancelarCambios = (Button) findViewById(R.id.bt_detalle_cancelarCambios);
        txt_user = (TextView) findViewById(R.id.txt_detalle_user);

        txt_user.setText(ObrasActivity.USER);
        if(ObrasActivity.CARGO.equalsIgnoreCase(ObrasActivity.jefe) || ObrasActivity.CARGO.equalsIgnoreCase(ObrasActivity.JefeObra) || ObrasActivity.CARGO.equalsIgnoreCase(ObrasActivity.AgenteInmobiliario) || ObrasActivity.CARGO.equalsIgnoreCase(ObrasActivity.admin)){
            img_editar.setVisibility(View.VISIBLE);
        } else{
            img_editar.setVisibility(View.INVISIBLE);
        }
        bt_aceptarCambios.setVisibility(View.INVISIBLE);
        bt_cancelarCambios.setVisibility(View.INVISIBLE);

        o = (Obra) intent.getParcelableExtra(ObrasViewHolder.EXTRA_OBJETO_OBRA);

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

    public void editarObra(View view) {
        img_editar.setVisibility(View.INVISIBLE);
        bt_aceptarCambios.setVisibility(View.VISIBLE);
        bt_cancelarCambios.setVisibility(View.VISIBLE);

        String cargo = ObrasActivity.CARGO;
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
        }
        else if (cargo.equalsIgnoreCase(ObrasActivity.admin)){
            edt_localizacion.setEnabled(true);
            edt_direccion.setEnabled(true);
            edt_precioTerreno.setEnabled(true);
            rb_terminada_no.setEnabled(true);
            rb_terminada_si.setEnabled(true);
            rb_vendida_si.setEnabled(true);
            rb_vendida_no.setEnabled(true);
        }
    }

    public void cancelCambios(View view) {
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

    public void updateObra(View view) {
        img_editar.setVisibility(View.VISIBLE);
        bt_aceptarCambios.setVisibility(View.INVISIBLE);
        bt_cancelarCambios.setVisibility(View.INVISIBLE);

        String obra = String.valueOf(txt_Obra.getText());
        String direccion = String.valueOf(edt_direccion.getText());
        String localizacion = String.valueOf(edt_localizacion.getText());
        String precioTerreno = String.valueOf(edt_precioTerreno.getText());
        boolean terminada = false;
        if(rb_terminada_si.isChecked()){
            terminada = true;
        }
        boolean vendida = false;
        if(rb_vendida_si.isChecked()){
            vendida = true;
        }
        ObrasActivity.format.format(Double.valueOf(precioTerreno));

        AlertDialog.Builder updateObra = new AlertDialog.Builder(this);
        updateObra.setTitle("¿Quieres actualizar los datos de " + obra + "?");
        boolean finalTerminada = terminada;
        boolean finalVendida = vendida;
        updateObra.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                o = new Obra(0, obra, direccion, localizacion, Double.valueOf(precioTerreno) , finalTerminada, finalVendida);
                boolean updateObraOK = ObraCtrl.updateObra(o, obra);
                mostrarMensaje(updateObraOK, obra);
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

        edt_localizacion.setEnabled(false);
        edt_direccion.setEnabled(false);
        edt_precioTerreno.setEnabled(false);
        rb_terminada_no.setEnabled(false);
        rb_terminada_si.setEnabled(false);
        rb_vendida_si.setEnabled(false);
        rb_vendida_no.setEnabled(false);
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
}