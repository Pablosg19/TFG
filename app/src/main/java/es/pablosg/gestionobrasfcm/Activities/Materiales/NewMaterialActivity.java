package es.pablosg.gestionobrasfcm.Activities.Materiales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Controladores.MaterialCtrl;
import es.pablosg.gestionobrasfcm.R;

public class NewMaterialActivity extends AppCompatActivity {

    private EditText edt_material;
    private EditText edt_unidadMedida;
    private EditText edt_abreviaturaUnidadMedida;
    private EditText edt_familia;
    private Button bt_newMaterial;
    private Button bt_cancelarMaterial;
    private TextView txt_usuario;

    Material m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_material);

        edt_material = (EditText) findViewById(R.id.edt_newMaterial_material);
        edt_unidadMedida = (EditText) findViewById(R.id.edt_newMaterial_unidadMedida);
        edt_abreviaturaUnidadMedida = (EditText) findViewById(R.id.edt_newMaterial_abreviaturaUnidadMedida);
        edt_familia = (EditText) findViewById(R.id.edt_newMaterial_familia);
        bt_newMaterial = (Button) findViewById(R.id.bt_newMaterial_anadirMaterial);
        bt_cancelarMaterial = (Button) findViewById(R.id.bt_newMaterial_cancelarMaterial);
        txt_usuario = (TextView) findViewById(R.id.txt_newMaterial_user);

        txt_usuario.setText(Login.USUARIO_INTRODUCIDO);
    }

    public void cancelarNewMaterial(View view) {
        Intent intent = new Intent(this, MaterialesActivity.class);
        startActivity(intent);
    }

    public void anadirNewMaterial(View view) {
        String material = String.valueOf(edt_material.getText());
        String unidadMedida = String.valueOf(edt_unidadMedida.getText());
        String abreviatura = String.valueOf(edt_abreviaturaUnidadMedida.getText());
        String familia = String.valueOf(edt_familia.getText());
        if(material.isEmpty() || unidadMedida.isEmpty() || abreviatura.isEmpty()){
            bt_newMaterial.setError("Es necesario rellenar todos los campos");
        }
        else {
            AlertDialog.Builder newMaterial = new AlertDialog.Builder(this);
            newMaterial.setTitle("¿Estas seguro de añadir el material " + material + "?");
            newMaterial.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m = new Material(0,material, unidadMedida, abreviatura, familia);
                    boolean addMaterialOK = MaterialCtrl.newMaterial(m);
                    if(addMaterialOK){
                       mostrarMensaje(true, material);
                    }
                    else {
                        mostrarMensaje(false, material);
                    }


                }
            });
            newMaterial.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            newMaterial.show();
        }
    }

    public void mostrarMensaje(boolean anadirOK, String material){
        if (anadirOK){
            Toast.makeText(this, "Material añadido correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MaterialesActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Error al añadir el material "+ material ,Toast.LENGTH_LONG).show();
        }
    }

}