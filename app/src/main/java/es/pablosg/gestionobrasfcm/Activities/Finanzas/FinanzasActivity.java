package es.pablosg.gestionobrasfcm.Activities.Finanzas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Activities.Materiales.MaterialesActivity;
import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.FinanzaObra;
import es.pablosg.gestionobrasfcm.Controladores.FinanzaObraCtrl;
import es.pablosg.gestionobrasfcm.R;

public class FinanzasActivity extends AppCompatActivity {

    public static final String USUARIO_FINANZAS = "espablosggestionobrasfcmfinanzasusuariointroducido";
    public static final String CARGO_FINANZAS = "espablosggestionobrasfcmfinanzascargousuario";

    private EditText edt_obra;
    private TextView txt_user;
    private RecyclerView rvFinanzas;
    private ListaFinanzasAdapter finanzasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finanzas);

        edt_obra = (EditText) findViewById(R.id.edt_filtroObra_finanzas);
        rvFinanzas = (RecyclerView) findViewById(R.id.rv_movimientosFinanzas);
        txt_user = (TextView) findViewById(R.id.txt_finanzas_user);

        txt_user.setText(ObrasActivity.USER);

        finanzasAdapter = new ListaFinanzasAdapter(this);
        ArrayList<FinanzaObra> finanzas = FinanzaObraCtrl.getFinanzasObras();
        if(finanzas != null){
            finanzasAdapter.setListaFinanzas(finanzas);
        }
        rvFinanzas.setAdapter(finanzasAdapter);
        rvFinanzas.setLayoutManager(new LinearLayoutManager(this));
    }

    public void realizarBusqueda(View view){
        String obra = String.valueOf(edt_obra.getText());
        finanzasAdapter = new ListaFinanzasAdapter(this);
        ArrayList<FinanzaObra> finanzas = FinanzaObraCtrl.getFinanzaObraFiltro(obra);
        if (finanzas.size() != 0 ){
            finanzasAdapter.setListaFinanzas(finanzas);
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
        rvFinanzas.setAdapter(finanzasAdapter);
        rvFinanzas.setLayoutManager(new LinearLayoutManager(this));
    }

    public void borrarFiltros(View view){
        edt_obra.setText("");
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

    public void goToMateriales(View view){
        Intent intent = new Intent(this, MaterialesActivity.class);
        startActivity(intent);
    }

    public void goToObras(View view){
        Intent intent = new Intent(this, ObrasActivity.class);
        intent.putExtra(USUARIO_FINANZAS,ObrasActivity.USER);
        intent.putExtra(CARGO_FINANZAS, ObrasActivity.CARGO);
        startActivity(intent);
    }
}