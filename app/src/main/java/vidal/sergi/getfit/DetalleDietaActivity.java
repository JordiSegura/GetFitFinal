package vidal.sergi.getfit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class DetalleDietaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvNombreComida;
    static TextView tvND;
    Intent intent;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detalledieta);

        recyclerView = findViewById(R.id.rvDetalleDieta);
        tvNombreComida = findViewById(R.id.tvNombreDieta);
        tvND= findViewById(R.id.tvND);

        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetalleDietaActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        final List<String> comidasList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String nombreDieta = getIntent().getExtras().getString("nombreDieta");
        Log.v("svm",nombreDieta);
        tvND.setText(nombreDieta);
        DatabaseReference rutinas = database.getReference(FirebaseReferences.DIETAS);

        rutinas.child(nombreDieta).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    comidasList.add(childDataSnapshot.getKey());
                }
                fotosList.add(R.drawable.almuerzo);
                fotosList.add(R.drawable.cena);
                fotosList.add(R.drawable.comida);
                fotosList.add(R.drawable.desayuno);
                fotosList.add(R.drawable.merienda);

                Log.v("svm",""+ comidasList.toString());
                DetalleDietaListAdapter adapter = new DetalleDietaListAdapter(comidasList, fotosList);
                recyclerView.setLayoutManager (new LinearLayoutManager(DetalleDietaActivity.this));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_dietas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(DetalleDietaActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(DetalleDietaActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(DetalleDietaActivity.this, DietasActivity.class);
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