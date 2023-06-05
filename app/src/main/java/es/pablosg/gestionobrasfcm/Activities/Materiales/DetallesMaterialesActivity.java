package es.pablosg.gestionobrasfcm.Activities.Materiales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasViewHolder;
import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Controladores.MaterialCtrl;
import es.pablosg.gestionobrasfcm.Controladores.ObraCtrl;
import es.pablosg.gestionobrasfcm.R;

public class DetallesMaterialesActivity extends AppCompatActivity {

    private TextView txt_material;
    private EditText edt_unidadMedida;
    private EditText edt_abreviatura;
    private EditText edt_familia;
    private ImageView img_editar;
    private Button bt_aceptarCambios;
    private Button bt_cancelarCambios;
    private TextView txt_user;

    private Intent intent;

    private Material m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_materiales);

        intent = getIntent();

        txt_material = (TextView) findViewById(R.id.txt_detalle_material);
        edt_unidadMedida = (EditText) findViewById(R.id.edt_detalle_unidadMedida);
        edt_abreviatura = (EditText) findViewById(R.id.edt_detalle_abreviatura);
        edt_familia = (EditText) findViewById(R.id.edt_detalle_familia);
        img_editar = (ImageView) findViewById(R.id.img_detalleMaterial_editar);
        bt_aceptarCambios = (Button) findViewById(R.id.bt_detalleMaterial_aceptarCambios);
        bt_cancelarCambios = (Button) findViewById(R.id.bt_detalleMaterial_cancelarCambios);
        txt_user = (TextView) findViewById(R.id.txt_detalleMaterial_user);

        txt_user.setText(Login.USUARIO_INTRODUCIDO);
        if(Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.jefe) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.JefeObra) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.admin)){
            img_editar.setVisibility(View.VISIBLE);
        } else{
            img_editar.setVisibility(View.INVISIBLE);
        }
        bt_aceptarCambios.setVisibility(View.INVISIBLE);
        bt_cancelarCambios.setVisibility(View.INVISIBLE);

        m = (Material) intent.getParcelableExtra(MaterialesViewHolder.EXTRA_OBJETO_MATERIAL);

        txt_material.setText(m.getMATERIAL());
        edt_unidadMedida.setText(m.getUNIDAD_MEDIDA());
        edt_abreviatura.setText(m.getABREVIATURA_UNIDAD_MEDIDA());
        if(m.getFAMILIA().equals("")){
          edt_familia.setText("Sin familia asignada");
        }
        else{
            edt_familia.setText(m.getFAMILIA());
        }
    }

    public void editarMaterial(View view) {
        img_editar.setVisibility(View.INVISIBLE);
        bt_aceptarCambios.setVisibility(View.VISIBLE);
        bt_cancelarCambios.setVisibility(View.VISIBLE);

        edt_unidadMedida.setEnabled(true);
        edt_abreviatura.setEnabled(true);
        edt_familia.setEnabled(true);

    }

    public void cancelCambios(View view) {
        img_editar.setVisibility(View.VISIBLE);
        bt_aceptarCambios.setVisibility(View.INVISIBLE);
        bt_cancelarCambios.setVisibility(View.INVISIBLE);

        edt_unidadMedida.setEnabled(false);
        edt_abreviatura.setEnabled(false);
        edt_familia.setEnabled(false);

        edt_unidadMedida.setText(m.getUNIDAD_MEDIDA());
        edt_abreviatura.setText(m.getABREVIATURA_UNIDAD_MEDIDA());
        if(m.getFAMILIA().equals("")){
            edt_familia.setText("Sin familia asignada");
        }
        else{
            edt_familia.setText(m.getFAMILIA());
        }
    }

    public void updateMaterial(View view) {
        String material = String.valueOf(txt_material.getText());
        String unidadMedida = String.valueOf(edt_unidadMedida.getText());
        String abreviatura = String.valueOf(edt_abreviatura.getText());
        String familia = String.valueOf(edt_familia.getText());

        AlertDialog.Builder updateMaterial = new AlertDialog.Builder(this);
        updateMaterial.setTitle("¿Quieres actualizar los datos de " + material + "?");
        updateMaterial.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m = new Material(0, material, unidadMedida, abreviatura, familia);
                boolean updateMaterialOK = MaterialCtrl.updateMaterial(m, material);
                mostrarMensaje(updateMaterialOK, material);
            }
        });
        updateMaterial.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edt_unidadMedida.setText(m.getUNIDAD_MEDIDA());
                edt_abreviatura.setText(m.getABREVIATURA_UNIDAD_MEDIDA());
                if(m.getFAMILIA().equals("")){
                    edt_familia.setText("Sin familia asignada");
                }
                else{
                    edt_familia.setText(m.getFAMILIA());
                }
            }
        });
        updateMaterial.show();

        edt_unidadMedida.setEnabled(false);
        edt_abreviatura.setEnabled(false);
        edt_familia.setEnabled(false);

        img_editar.setVisibility(View.VISIBLE);
        bt_aceptarCambios.setVisibility(View.INVISIBLE);
        bt_cancelarCambios.setVisibility(View.INVISIBLE);

    }

    public void mostrarMensaje(boolean updateOK, String material){
        if (updateOK){
            Toast.makeText(this, "Datos de " + material + " actualizados correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MaterialesActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "No se han podido actualizar los datos de "+ material ,Toast.LENGTH_LONG).show();
        }
    }
}