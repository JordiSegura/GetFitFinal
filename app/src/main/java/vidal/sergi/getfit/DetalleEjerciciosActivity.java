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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class DetalleEjerciciosActivity extends AppCompatActivity {

    ImageView ivEjercicio;
    TextView tvNEjercicio, tvS, tvR, tvD;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);
    Intent intent;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detalle_ejercicios);

        ivEjercicio = findViewById(R.id.ivEjercicio);
        tvNEjercicio = findViewById(R.id.tvNEjercicio);
        tvS = findViewById(R.id.tvS);
        tvR = findViewById(R.id.tvR);
        tvD = findViewById(R.id.tvD);

        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetalleEjerciciosActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        String nombreEjercicio = getIntent().getExtras().getString("nombreEjercicio");
        String nombreMusculo = getIntent().getExtras().getString("nombreMusculo");
        String nombreRutina = getIntent().getExtras().getString("nombreRutina");
        Log.d("svm", nombreMusculo);
        Log.d("svm", nombreEjercicio);
        Log.d("svm", nombreRutina);

        rutinas.child(nombreRutina).child(nombreMusculo).child(nombreEjercicio).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvNEjercicio.setText(dataSnapshot.child("nombre").getValue(String.class));
                tvS.setText("Series: " + String.valueOf(dataSnapshot.child("series").getValue(Integer.class)));
                tvR.setText("Repeticiones: " + String.valueOf(dataSnapshot.child("repeticiones").getValue(Integer.class)));
                tvD.setText("Descanso: " + String.valueOf(dataSnapshot.child("descanso").getValue(Integer.class)) + "\"");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_rutinas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(DetalleEjerciciosActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(DetalleEjerciciosActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(DetalleEjerciciosActivity.this, DietasActivity.class);
                        startActivity(intent);
                        break;
//                    case R.id.action_ajustes:
//                        intent = new Intent(RutinasActivity.this, AjustesActivity.class);
//                        startActivity(intent);
//                        break;
                }
                return true;
            }
        });

    }
}