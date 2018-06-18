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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vidal.sergi.getfit.Objetos.Alimento;
import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class AlimentosActivity extends AppCompatActivity {

    GridView gridView;
    static TextView tvNC;
    Intent intent;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alimentos);

        gridView = findViewById(R.id.gvDieta);
        tvNC = findViewById(R.id.tvNC);

        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AlimentosActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        final List<Alimento> alimentosList = new ArrayList<>();
        final List<Integer> fotosList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String nombreComida = getIntent().getExtras().getString("nombreComida");
        final String nombreDieta = getIntent().getExtras().getString("nombreDieta");
        Log.d("svm", nombreDieta);
        Log.d("svm", nombreComida);
        tvNC.setText(nombreComida);

        DatabaseReference dietas = database.getReference(FirebaseReferences.DIETAS);
        dietas.child(nombreDieta).child(nombreComida).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    alimentosList.add(childDataSnapshot.getValue(Alimento.class));
                }

                for(Alimento alimento: alimentosList)
                    Log.d("svm", alimento.toString());

                AlimentosListAdaptar adapter = new AlimentosListAdaptar(this, alimentosList, nombreDieta);
                gridView.setAdapter(adapter);
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
                        intent = new Intent(AlimentosActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(AlimentosActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(AlimentosActivity.this, DietasActivity.class);
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