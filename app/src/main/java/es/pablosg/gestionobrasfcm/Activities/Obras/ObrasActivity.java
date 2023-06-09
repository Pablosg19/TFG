package es.pablosg.gestionobrasfcm.Activities.Obras;

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

import java.text.DecimalFormat;
import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Finanzas.FinanzasActivity;
import es.pablosg.gestionobrasfcm.Activities.Login;
import es.pablosg.gestionobrasfcm.Activities.Materiales.MaterialesActivity;
import es.pablosg.gestionobrasfcm.Clases.Obra;
import es.pablosg.gestionobrasfcm.Controladores.ObraCtrl;
import es.pablosg.gestionobrasfcm.R;

public class ObrasActivity extends AppCompatActivity {

    public static DecimalFormat format = new DecimalFormat("#.00");

    public static final String USUARIO_INTRODUCIDO = "espablosggestionobrasfcmnewobrausuariointroducido";
    public static final String CARGO_USUARIO = "espablosggestionobrasfcmnewobracargousuario";

    public static final String admin = "Administrador";
    public static final String jefe = "Jefe";
    public static final String JefeObra = "Jefe de Obra";
    public static final String AgenteInmobiliario = "Agente Inmobiliario";
    public static final String Administrativo = "Administrativo";
    public static final String Albañil = "Albañil";

    public String CARGO;
    public String USER;

    private Intent intent;

    private Button bt_obras;
    private Button bt_materiales;
    private Button bt_finanzas;
    private EditText edt_nombre;
    private EditText edt_localizacion;
    private TextView txt_user;
    private ImageView img_newObra;

    private RecyclerView rvObras;
    private ListaObrasAdapter obrasAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);

        USER = Login.USUARIO_INTRODUCIDO;
        CARGO = Login.CARGO_USUARIO;

        txt_user = (TextView) findViewById(R.id.txt_obras_user);
        bt_obras = (Button) findViewById(R.id.bt_obras_obras);
        bt_materiales = (Button) findViewById(R.id.bt_obras_materiales);
        bt_finanzas = (Button) findViewById(R.id.bt_obras_finanzas);
        edt_nombre = (EditText) findViewById(R.id.edt_filtroObra);
        edt_localizacion = (EditText) findViewById(R.id.edt_filtroLocalizacion);
        rvObras = (RecyclerView) findViewById(R.id.rv_gestionMateriales);
        img_newObra = (ImageView) findViewById(R.id.img_addObra);

        if(CARGO.equals(admin) || CARGO.equals(jefe) || CARGO.equals(JefeObra)){
            img_newObra.setVisibility(View.VISIBLE);
        }
        else{
            img_newObra.setVisibility(View.INVISIBLE);
        }

        txt_user.setText(USER);

        obrasAdapter = new ListaObrasAdapter(this);
        ArrayList<Obra> obras = ObraCtrl.getObras();
        if(obras != null){
            obrasAdapter.setListaObras(obras);
        }
        rvObras.setAdapter(obrasAdapter);
        rvObras.setLayoutManager(new LinearLayoutManager(this));

    }

    public void goToFinanzas(View view) {
        if(CARGO.equals(JefeObra) || CARGO.equals(Albañil) || CARGO.equals(AgenteInmobiliario)){
            AlertDialog.Builder permisos = new AlertDialog.Builder(this);
            permisos.setTitle("Error");
            permisos.setMessage("Este usuario no dispone de los permisos requeridos para acceder a Finanzas");
            permisos.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            permisos.show();
        }
        else {
            Intent intent = new Intent(this, FinanzasActivity.class);
            startActivity(intent);
        }
    }

    public void borrarFiltros(View view){
        edt_nombre.setText("");
        edt_localizacion.setText("");
        realizarBusqueda(view);
    }

    public void realizarBusqueda(View view){
        String nombre = String.valueOf(edt_nombre.getText());
        String localizacion = String.valueOf(edt_localizacion.getText());

        obrasAdapter = new ListaObrasAdapter(this);
        ArrayList<Obra> obras = ObraCtrl.getObrasFiltro(nombre, localizacion);
        if (obras.size() != 0 ){
            obrasAdapter.setListaObras(obras);
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
        rvObras.setAdapter(obrasAdapter);
        rvObras.setLayoutManager(new LinearLayoutManager(this));
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
        if(CARGO.equals(Administrativo) || CARGO.equals(Albañil) || CARGO.equals(AgenteInmobiliario)){
            AlertDialog.Builder permisos = new AlertDialog.Builder(this);
            permisos.setTitle("Error");
            permisos.setMessage("Este usuario no dispone de los permisos requeridos para acceder a Materiales");
            permisos.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            permisos.show();
        }
        else {
            Intent intent = new Intent(this, MaterialesActivity.class);
            startActivity(intent);
        }
    }

    public void newObra(View view){
        Intent intent = new Intent(this,NewObraActivity.class);
        startActivity(intent);
    }
}