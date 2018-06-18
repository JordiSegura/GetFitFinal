package vidal.sergi.getfit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Usuario;

/**
 * Created by Sergi on 01/03/2018.
 */

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    TextView btnRegistrar, btnLogin;


    String emailRegistro, passwordRegistro;
    public String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnRegistrar = findViewById(R.id.registrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailRegistro = email.getText().toString();
                passwordRegistro = password.getText().toString();
                iniciarSesion(emailRegistro, passwordRegistro);
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }



    private void iniciarSesion(final String email, String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Bundle extras2 = getIntent().getExtras();
                    if (extras2!=null) {
                        String username = extras2.getString("nombreUsuario");

                    Toast.makeText(LoginActivity.this, "Usuario logueado correctamente.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("nombreUsuario", username);
                    startActivity(intent);}
                    else {
                        String username = emailRegistro.split("@")[0];

                        Toast.makeText(LoginActivity.this, "Usuario logueado correctamente.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("nombreUsuario", username);
                        Log.d("", "onCreate: LOGINUSU "+getUser());

                        startActivity(intent);
                    }
                    Log.d("svm", "Usuario creado correctamente");
                }else {
                    Toast.makeText(LoginActivity.this, "Usuario/Password incorrectos.", Toast.LENGTH_SHORT).show();
                    Log.d("svm", task.getException().getMessage()+"");

                }
            }
        });
    }
}
