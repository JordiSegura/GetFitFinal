package vidal.sergi.getfit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Usuario;

public class RegistroActivity extends AppCompatActivity {

    EditText email, password;
    TextView btnLogin, btnRegistrar;
    String emailRegistro, passwordRegistro;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference(FirebaseReferences.USERS);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registro);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnRegistrar = findViewById(R.id.registrar);
        btnLogin = findViewById(R.id.login);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BUTTON","onCLick()");
                emailRegistro = email.getText().toString();
                passwordRegistro = password.getText().toString();
                registrar(emailRegistro, passwordRegistro);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registrar(String email, String pass){
        Log.d("svm", "registrar()");
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String username = emailRegistro.split("@")[0];
                    String hoy = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

                    Calendar calendar = Calendar.getInstance();
                    Date today = calendar.getTime();

                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    Date tomorrow = calendar.getTime();

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    String todayAsString = dateFormat.format(today);
                    String tomorrowAsString = dateFormat.format(tomorrow);

                    System.out.println(todayAsString);
                    System.out.println(tomorrowAsString);

                    usersRef.child(username).setValue(new Usuario(" ", " ", 0, " ", 0, 0,String.valueOf(1),String.valueOf(1),tomorrowAsString,tomorrowAsString));
                    Toast.makeText(RegistroActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                    intent.putExtra("nombreUsuario",username);
                    startActivity(intent);
                    Log.d("svm", "Usuario creado correctamente.");
                }else {
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e) {
                        Toast.makeText(RegistroActivity.this, "Contraseña Débil", Toast.LENGTH_SHORT).show();
                        Log.e("svm", "Contraseña Débil");

                    } catch(FirebaseAuthInvalidCredentialsException e) {
                        Toast.makeText(RegistroActivity.this, "Email inválido", Toast.LENGTH_SHORT).show();
                        Log.e("svm", "Email inválido");

                    } catch(FirebaseAuthUserCollisionException e) {
                        Toast.makeText(RegistroActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                        Log.e("svm", "El usuario ya existe");

                    } catch(Exception e) {
                        Log.e("svm", e.getMessage());
                    }
                }
            }
        });
    }
}