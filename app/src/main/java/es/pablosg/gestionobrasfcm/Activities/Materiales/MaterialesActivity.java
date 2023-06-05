package es.pablosg.gestionobrasfcm.Activities.Materiales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Finanzas.FinanzasActivity;
import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.Material;
import es.pablosg.gestionobrasfcm.Controladores.MaterialCtrl;
import es.pablosg.gestionobrasfcm.R;

public class MaterialesActivity extends AppCompatActivity {

    public static final String USUARIO_MATERIALES = "espablosggestionobrasfcmmaterialusuariointroducido";
    public static final String CARGO_MATERIALES = "espablosggestionobrasfcmmaterialcargousuario";


    private EditText edt_material;
    private EditText edt_familia;
    private ImageView img_addMaterial;
    private Button bt_obras;
    private Button bt_materiales;
    private Button bt_finanzas;
    private TextView txt_user;
    private RecyclerView rvMateriales;
    private ListaMaterialesAdapter materialesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiales);

        txt_user = (TextView) findViewById(R.id.txt_materiales_user);
        bt_obras = (Button) findViewById(R.id.bt_materiales_obras);
        bt_materiales = (Button) findViewById(R.id.bt_materiales_materiales);
        bt_finanzas = (Button) findViewById(R.id.bt_materiales_finanzas);
        edt_material = (EditText) findViewById(R.id.edt_filtroMaterial);
        edt_familia = (EditText) findViewById(R.id.edt_filtroFamilia);

        rvMateriales = (RecyclerView) findViewById(R.id.rv_gestionMateriales);
        img_addMaterial = (ImageView) findViewById(R.id.img_addMaterial);

        if(Login.CARGO_USUARIO.equals(ObrasActivity.admin) || Login.CARGO_USUARIO.equals(ObrasActivity.jefe) || Login.CARGO_USUARIO.equals(ObrasActivity.JefeObra)){
            img_addMaterial.setVisibility(View.VISIBLE);
        }
        else{
            img_addMaterial.setVisibility(View.INVISIBLE);
        }

        txt_user.setText(Login.USUARIO_INTRODUCIDO);

        materialesAdapter = new ListaMaterialesAdapter(this);
        ArrayList<Material> materiales = MaterialCtrl.getMateriales();
        if(materiales != null){
            materialesAdapter.setListaMateriales(materiales);
        }
        rvMateriales.setAdapter(materialesAdapter);
        rvMateriales.setLayoutManager(new LinearLayoutManager(this));
    }

    public void realizarBusqueda(View view){
        String material = String.valueOf(edt_material.getText());
        String familia = String.valueOf(edt_familia.getText());
        materialesAdapter = new ListaMaterialesAdapter(this);
        ArrayList<Material> materiales = MaterialCtrl.getMaterialFiltro(material, familia);
        if (materiales.size() != 0 ){
            materialesAdapter.setListaMateriales(materiales);
        }
        else{
            AlertDialog.Builder busqueda = new AlertDialog.Builder(this);
            busqueda.setTitle("Error");
            busqueda.setMessage("No se han encontrado resultados");
            busqueda.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            busqueda.show();
        }
        rvMateriales.setAdapter(materialesAdapter);
        rvMateriales.setLayoutManager(new LinearLayoutManager(this));
    }

    public void borrarFiltros(View view){
        edt_familia.setText("");
        edt_material.setText("");
        realizarBusqueda(view);
    }

    public void salir(View view){
        AlertDialog.Builder salir = new AlertDialog.Builder(this);
        salir.setTitle("¿Quieres cerrar sesión?");
        salir.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToLogin();
            }
        });
        salir.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        salir.show();
    }
    public void goToLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void goToFinanzas(View view){
        Intent intent = new Intent(this, FinanzasActivity.class);
        startActivity(intent);
    }

    public void goToObras(View view){
        Intent intent = new Intent(this, ObrasActivity.class);
        startActivity(intent);
    }

    public void newMaterial(View view){
        Intent intent = new Intent(this, NewMaterialActivity.class);
        startActivity(intent);
    }
}