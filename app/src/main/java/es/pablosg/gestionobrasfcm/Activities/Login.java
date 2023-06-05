package es.pablosg.gestionobrasfcm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import es.pablosg.gestionobrasfcm.Activities.Finanzas.FinanzasActivity;
import es.pablosg.gestionobrasfcm.Activities.Obras.ObrasActivity;
import es.pablosg.gestionobrasfcm.Clases.Usuario;
import es.pablosg.gestionobrasfcm.Controladores.UsuarioCtrl;
import es.pablosg.gestionobrasfcm.R;

public class Login extends AppCompatActivity {

    public static String USUARIO_INTRODUCIDO;
    public static String CARGO_USUARIO;

    private EditText edtUser;
    private EditText edtPassword;
    private TextView errorLogin;
    private String CARGO;

    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtUser = (EditText) findViewById(R.id.edt_user);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        errorLogin = (TextView) findViewById(R.id.txt_ErrorLogin);

        errorLogin.setVisibility(View.INVISIBLE);
    }

    public void iniciarSesion(View view) {
        String usuario = String.valueOf(edtUser.getText());
        String contraseña = String.valueOf(edtPassword.getText());
        boolean error = false;
        if (usuario.isEmpty()){
            edtUser.setError("Debes escribir el nombre de usuario");
            error = true;
        }
        if (contraseña.isEmpty()){
            edtPassword.setError("Debes escribir una contraseña");
            error = true;
        }
        if (error){
            return;
        }
        usuarios = UsuarioCtrl.getUsuarios();

        for (Usuario user : usuarios) {
            if (usuario.equals(user.getUSER()) && contraseña.equals(user.getPASSWORD())){
                errorLogin.setVisibility(View.INVISIBLE);
                CARGO = user.getCARGO();
                Intent intent;
                if (CARGO.equalsIgnoreCase("Administrativo")){
                    intent = new Intent(this, FinanzasActivity.class);
                }
                else{
                    intent = new Intent(this, ObrasActivity.class);
                }

                USUARIO_INTRODUCIDO = user.getNOMBRE();
                CARGO_USUARIO = CARGO;
                // intent.putExtra(USUARIO_INTRODUCIDO,user.getNOMBRE());
                // intent.putExtra(CARGO_USUARIO, CARGO);
                startActivity(intent);
                return;
            }
        }
        errorLogin.setVisibility(View.VISIBLE);
    }
}