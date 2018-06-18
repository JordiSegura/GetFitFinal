package vidal.sergi.getfit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class DetalleRutinaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvNombreMusculo;
    static TextView tvNR;
    Intent intent;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detallerutina);

        recyclerView = findViewById(R.id.rvDetalleRutina);
        tvNombreMusculo = findViewById(R.id.tvNombreRutina);
        tvNR= findViewById(R.id.tvindica);

        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetalleRutinaActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        final List<String> musculosList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String nombreRutina = getIntent().getExtras().getString("nombreRutina");
        Log.v("svm",nombreRutina);
        tvNR.setText(nombreRutina);
        DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);

        rutinas.child(nombreRutina).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    musculosList.add(childDataSnapshot.getKey());
                }
                fotosList.add(R.drawable.abdominales);
                fotosList.add(R.drawable.biceps1);
                fotosList.add(R.drawable.cuadriceps);
                fotosList.add(R.drawable.dorsales1);
                fotosList.add(R.drawable.femoral1);
                fotosList.add(R.drawable.gemelos1);
                fotosList.add(R.drawable.hombroforntal);
                fotosList.add(R.drawable.hombroposterior);
                fotosList.add(R.drawable.pectoral);
                fotosList.add(R.drawable.triceps);

                DetalleRutinaListAdapter adapter = new DetalleRutinaListAdapter(musculosList, fotosList);
                recyclerView.setLayoutManager (new LinearLayoutManager(DetalleRutinaActivity.this));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
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
                        intent = new Intent(DetalleRutinaActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(DetalleRutinaActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(DetalleRutinaActivity.this, DietasActivity.class);
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

    public static String getTextFromTextView(TextView textView){
        try{
            return (String) textView.getText();
        }
        catch (Exception ex){
            Log.d("Exception","Exception of type"+ex.getMessage());
        }
        return "";
    }
}