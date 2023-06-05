package es.pablosg.gestionobrasfcm.Activities.Obras.GestionMaterial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Activities.Obras.DetallesObrasActivity;
import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Clases.MovimientoFinanza;
import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Controladores.GestionMaterialesCtrl;
import es.pablosg.gestionobrasfcm.Controladores.MaterialCtrl;
import es.pablosg.gestionobrasfcm.Controladores.MovimientoFinanzaCtrl;
import es.pablosg.gestionobrasfcm.Controladores.ObraCtrl;
import es.pablosg.gestionobrasfcm.R;

public class NewGestionMaterialActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String NOMBRE_OBRA_GESTION = "espablosggestionobrasfcmnombreObraGestion";

    private TextView txt_user;
    private Spinner sp_familia;
    private Spinner sp_material;
    private EditText edt_precio;
    private EditText edt_cantidad;
    private TextView txt_obra;
    private Button bt_newGestionMaterial;

    private String familiaSeleccionada = "Todo";
    private String materialSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gestion_material);

        txt_obra = (TextView) findViewById(R.id.txt_newGestionMaterial_obra);
        sp_familia = (Spinner) findViewById(R.id.sp_newGestionMaterial_familia);
        sp_material = (Spinner) findViewById(R.id.sp_newGestionMaterial_material);
        edt_precio = (EditText) findViewById(R.id.edt_newGestionMaterial_precio);
        edt_cantidad = (EditText) findViewById(R.id.edt_newGestionMaterial_cantidad);
        txt_user = (TextView) findViewById(R.id.txt_newGestionMaterial_user);
        bt_newGestionMaterial = (Button) findViewById(R.id.bt_newGestionMaterial_anadir);

        txt_user.setText(Login.USUARIO_INTRODUCIDO);
        txt_obra.setText(DetallesObrasActivity.NOMBRE_OBRA);

        ArrayList<String> familias = new ArrayList<>();
        ArrayList<String> familias2 = MaterialCtrl.getFamilias();
        familias.add("Todo");
        for (String s: familias2) {
            familias.add(s);
        }
        sp_familia.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.estilos_spinner,familias);
        sp_familia.setAdapter(adapter);

        ArrayList<String> materiales = new ArrayList<>();
        materiales.add("");
        ArrayList<String> materiales2 = MaterialCtrl.getMaterial(familiaSeleccionada);
        for (String s: materiales2) {
            materiales.add(s);
        }
        sp_material.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,R.layout.estilos_spinner,materiales);
        sp_material.setAdapter(adapter1);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        familiaSeleccionada = adapterView.getItemAtPosition(i).toString();
        materialSeleccionado = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        familiaSeleccionada = "Todo";
    }

    public void buscarMaterial(View view){
        ArrayList<String> materiales = MaterialCtrl.getMaterial(familiaSeleccionada);
        sp_material.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,R.layout.estilos_spinner,materiales);
        sp_material.setAdapter(adapter1);
    }

    public void anadirNewGestionMaterial(View view){
        String obra = DetallesObrasActivity.NOMBRE_OBRA;
        String cantidad = String.valueOf(edt_cantidad.getText());
        String precio = String.valueOf(edt_precio.getText());
        if(cantidad.isEmpty() || precio.isEmpty() || materialSeleccionado.equalsIgnoreCase("")){
            bt_newGestionMaterial.setError("Es necesario rellenar todos los campos");
        }
        else {
            AlertDialog.Builder newGestionMaterial = new AlertDialog.Builder(this);
            newGestionMaterial.setTitle("¿Estas seguro de realizar la gestion de material?");
            newGestionMaterial.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GestionMaterial gm = new GestionMaterial(0,obra, materialSeleccionado, Double.valueOf(precio), Double.valueOf(cantidad));
                    boolean addGestionOK = GestionMaterialesCtrl.newGestionMaterial(gm);
                    if(addGestionOK){
                        Double precioTotal = Double.valueOf(ObrasActivity.format.format(Double.valueOf(precio) * Double.valueOf(cantidad)));
                        MovimientoFinanza mf = new MovimientoFinanza(0,obra,1, precioTotal);
                        boolean addMovimientoFinanzaOK = MovimientoFinanzaCtrl.newMovimientoFinanza(mf);
                        if(addMovimientoFinanzaOK){
                            mostrarMensaje(true);
                        }
                        else{
                            int idGestion = 0;
                            ArrayList<GestionMaterial> gestiones = GestionMaterialesCtrl.getGestionMaterialFiltro(obra);
                            for (GestionMaterial gm2: gestiones) {
                                if(gm2.getMATERIAL().equalsIgnoreCase(materialSeleccionado) && ObrasActivity.format.format(gm2.getPRECIO()) == ObrasActivity.format.format(Double.valueOf(precio)) && ObrasActivity.format.format(gm2.getCANTIDAD()) == ObrasActivity.format.format(Double.valueOf(cantidad))){
                                    idGestion = gm2.getID_GESTION_MATERIALES();
                                }
                            }
                            GestionMaterialesCtrl.deleteGestionMaterial(idGestion);
                            mostrarMensaje(false);
                        }
                    }
                    else {
                        mostrarMensaje(false);
                    }


                }
            });
            newGestionMaterial.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            newGestionMaterial.show();
        }
    }

    public void mostrarMensaje(boolean anadirOK){
        if (anadirOK){
            Toast.makeText(this, "Gestión de material completada",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, GestionObraActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Error en la gestión de material",Toast.LENGTH_LONG).show();
        }
    }

    public void cancelarNewGestionMaterial(View view){
        Intent intent = new Intent(this, GestionObraActivity.class);
        startActivity(intent);
    }

}