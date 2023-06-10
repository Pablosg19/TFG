package es.pablosg.gestionobrasfcm.Activities.Obras.GestionMaterial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Activities.Obras.DetallesObrasActivity;
import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.GestionMaterial;
import es.pablosg.gestionobrasfcm.Controladores.GestionMaterialesCtrl;
import es.pablosg.gestionobrasfcm.R;

public class GestionObraActivity extends AppCompatActivity {

    public static final String OBRA_GESTION_MATERIALES = "espablosggestionobrasfcmobragestionmateriales";

    private TextView txt_obra;
    private TextView txt_user;
    private RecyclerView rv_gestionMateriales;
    private ImageView img_addGestionMaterial;
    private ListaGestionMaterialesAdapter gestionMaterialesAdapter;
    private ImageView img_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_obra);

        txt_obra = (TextView) findViewById(R.id.txt_gestionObra_obra);
        txt_user = (TextView) findViewById(R.id.txt_gestionObra_user);
        img_addGestionMaterial = (ImageView) findViewById(R.id.img_gestionObra_add);
        rv_gestionMateriales = (RecyclerView) findViewById(R.id.rv_gestionMateriales);
        img_volver = (ImageView) findViewById(R.id.img_gestionObra_back);

        txt_user.setText(Login.USUARIO_INTRODUCIDO);

        txt_obra.setText(DetallesObrasActivity.NOMBRE_OBRA);

        if(Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.JefeObra) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.admin) || Login.CARGO_USUARIO.equalsIgnoreCase(ObrasActivity.jefe)){
            img_addGestionMaterial.setVisibility(View.VISIBLE);
        }
        else{
            img_addGestionMaterial.setVisibility(View.INVISIBLE);
        }

        gestionMaterialesAdapter = new ListaGestionMaterialesAdapter(this);
        ArrayList<GestionMaterial> gestionMateriales = GestionMaterialesCtrl.getGestionMaterialFiltro(DetallesObrasActivity.NOMBRE_OBRA);
        if(gestionMateriales != null){
            gestionMaterialesAdapter.setListaGestionMateriales(gestionMateriales);
        }
        rv_gestionMateriales.setAdapter(gestionMaterialesAdapter);
        rv_gestionMateriales.setLayoutManager(new LinearLayoutManager(this));
    }

    public void newGestionMaterial(View view){
        Intent intent = new Intent(this, NewGestionMaterialActivity.class);
        startActivity(intent);
    }

    public void volver (View view){
        Intent intent = new Intent(this, ObrasActivity.class);
        startActivity(intent);
    }
}