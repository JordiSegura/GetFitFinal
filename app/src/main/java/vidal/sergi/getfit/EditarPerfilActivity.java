package vidal.sergi.getfit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by alu2011543 on 12/03/2018.
 */

public class EditarPerfilActivity extends AppCompatActivity {

    EditText edNombre, edApellidos, edEdad, edPeso, edAltura;
    TextView btnSave, btnIMC;
    Intent intent;

    String nombre, apellidos, sexo;
    int edad, altura;
    double peso, imc;
    Spinner spinner;
    int spinnerPosition;
    Usuario usuario;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference(FirebaseReferences.USERS);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_editarperfil);
        spinner = findViewById(R.id.spinner1);
        String[] items = new String[]{"Hombre", "Mujer"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        final String username = email.split("@")[0];
        usersRef.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                edNombre.setText(String.valueOf(dataSnapshot.child("nombre").getValue()));
                edApellidos.setText(String.valueOf(dataSnapshot.child("apellidos").getValue()));
                edEdad.setText(String.valueOf(dataSnapshot.child("edad").getValue()));
//                spinner.setSele().toString(String.valueOf(dataSnapshot.child("sexo").getValue()));
                Log.d("svm", spinner.getSelectedItem().toString());
                if (dataSnapshot.child("sexo").getValue().equals("Hombre")) {
                    spinnerPosition = adapter.getPosition("Hombre");
                    spinner.setSelection(spinnerPosition);
                }else{
                    spinnerPosition = adapter.getPosition("Mujer");
                    spinner.setSelection(spinnerPosition);
                }
                edPeso.setText(String.valueOf(dataSnapshot.child("peso").getValue()));
                edAltura.setText(String.valueOf(dataSnapshot.child("altura").getValue()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

        usuario = new Usuario();
        edNombre = findViewById(R.id.edNombre);
        edApellidos = findViewById(R.id.edApellidos);
        edEdad = findViewById(R.id.edEdad);
        edPeso = findViewById(R.id.edPeso);
        edAltura = findViewById(R.id.edAltura);
        btnSave = findViewById(R.id.btnSave);
        btnIMC = findViewById(R.id.btnIMC);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edNombre.getText().toString();
                apellidos = edApellidos.getText().toString();
                edad = Integer.parseInt(edEdad.getText().toString());
                sexo = spinner.getSelectedItem().toString();
                peso = Double.parseDouble(edPeso.getText().toString());
                altura = Integer.parseInt(edAltura.getText().toString());

                usuario.setNombre(nombre);
                usuario.setApellidos(apellidos);
                usuario.setEdad(edad);
                usuario.setSexo(sexo);
                usuario.setPeso(peso);
                usuario.setAltura(altura);
                usersRef.child(username).child("nombre").setValue(usuario.getNombre());
                usersRef.child(username).child("apellidos").setValue(usuario.getApellidos());
                usersRef.child(username).child("edad").setValue(usuario.getEdad());
                usersRef.child(username).child("sexo").setValue(usuario.getSexo());
                usersRef.child(username).child("peso").setValue(usuario.getPeso());
                usersRef.child(username).child("altura").setValue(usuario.getAltura());


                Log.d("svm:", "Datos de usuario actualizados.");
                Toast.makeText(EditarPerfilActivity.this, "Datos de usuario actualizados.", Toast.LENGTH_SHORT).show();
            }
        });

        btnIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peso = Double.parseDouble(edPeso.getText().toString());
                altura = Integer.parseInt(edAltura.getText().toString());
                double alturaCM = (double)altura/100;
                imc = peso / (alturaCM * alturaCM);
                usuario.setPeso(peso);
                usuario.setAltura(altura);
                usuario.setImc(imc);
                usersRef.child(username).child("imc").setValue(imc);
                Toast.makeText(EditarPerfilActivity.this, "Tu IMC es: " + imc, Toast.LENGTH_SHORT).show();


            }
        });

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_rutinas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(EditarPerfilActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(EditarPerfilActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(EditarPerfilActivity.this, DietasActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
