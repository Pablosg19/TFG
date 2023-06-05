package es.pablosg.gestionobrasfcm.Activities.Obras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;

import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Controladores.MovimientoFinanzaCtrl;
import es.pablosg.gestionobrasfcm.Controladores.ObraCtrl;
import es.pablosg.gestionobrasfcm.R;

public class NewObraActivity extends AppCompatActivity {

    private EditText edt_obra;
    private EditText edt_direccion;
    private EditText edt_localizacion;
    private EditText edt_precioTerreno;
    private Button bt_anadirObra;
    private Button bt_cancelarObra;
    private TextView txt_usuario;

    Obra o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_obra);

        edt_obra = (EditText) findViewById(R.id.edt_newObra_Obra);
        edt_direccion = (EditText) findViewById(R.id.edt_newObra_direccion);
        edt_localizacion = (EditText) findViewById(R.id.edt_newObra_Localizacion);
        edt_precioTerreno = (EditText) findViewById(R.id.edt_newObra_PrecioTerreno);
        bt_anadirObra = (Button) findViewById(R.id.bt_newObra_anadirObra);
        bt_cancelarObra = (Button) findViewById(R.id.bt_newObra_cancelarObra);

        txt_usuario = (TextView) findViewById(R.id.txt_newObra_user);
        txt_usuario.setText(Login.USUARIO_INTRODUCIDO);
    }

    public void cancelarNewObra(View view) {
        Intent intent = new Intent(this, ObrasActivity.class);
        startActivity(intent);
    }

    public void anadirNewObra(View view) {
        String obra = String.valueOf(edt_obra.getText());
        String direccion = String.valueOf(edt_direccion.getText());
        String localizacion = String.valueOf(edt_localizacion.getText());
        String precio = String.valueOf(edt_precioTerreno.getText());
        if(obra.isEmpty() || direccion.isEmpty() || localizacion.isEmpty() || precio.isEmpty()){
            bt_anadirObra.setError("Es necesario rellenar todos los campos");
        }
        else {
            AlertDialog.Builder newObra = new AlertDialog.Builder(this);
            newObra.setTitle("¿Estas seguro de crear " + obra + "?");
            newObra.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    o = new Obra(0,obra, direccion, localizacion, Double.valueOf(precio), false,false);
                    boolean addObraOK = ObraCtrl.newObra(o);
                    if(addObraOK){
                        MovimientoFinanza mf = new MovimientoFinanza(0,obra,1,Double.valueOf(precio));
                        boolean addMovimientoFinanzaOK = MovimientoFinanzaCtrl.newMovimientoFinanza(mf);
                        if(addMovimientoFinanzaOK){
                            mostrarMensaje(true, obra);
                        }
                        else{
                            ObraCtrl.deleteObra(obra);
                            mostrarMensaje(false, obra);
                        }
                    }
                    else {
                        mostrarMensaje(false, obra);
                    }


                }
            });
            newObra.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            newObra.show();
        }
    }

    public void mostrarMensaje(boolean anadirOK, String obra){
        if (anadirOK){
            Toast.makeText(this, "Obra creada correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ObrasActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Error al crear la obra "+ obra ,Toast.LENGTH_LONG).show();
        }
    }
}