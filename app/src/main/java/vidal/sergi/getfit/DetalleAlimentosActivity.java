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

import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class DetalleAlimentosActivity extends AppCompatActivity {

    ImageView ivAlimento;
    TextView tvNALimento, tvC, tvG;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dietas = database.getReference(FirebaseReferences.DIETAS);
    Intent intent;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detalle_alimentos);

        ivAlimento = findViewById(R.id.ivAlimento);
        tvNALimento = findViewById(R.id.tvNALimento);
        tvC = findViewById(R.id.tvC);
        tvG = findViewById(R.id.tvG);

        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetalleAlimentosActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        String nombreAlimento = getIntent().getExtras().getString("nombreAlimento");
        String nombreComida = getIntent().getExtras().getString("nombreComida");
        String nombreDieta = getIntent().getExtras().getString("nombreDieta");
        Log.d("svm", nombreComida);
        Log.d("svm", nombreAlimento);
        Log.d("svm", nombreDieta);

        dietas.child(nombreDieta).child(nombreComida).child(nombreAlimento).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvNALimento.setText(dataSnapshot.child("nombre").getValue(String.class));
                tvC.setText("Calorias: " + String.valueOf(dataSnapshot.child("calorias").getValue(Integer.class)));
                tvG.setText("Cantidad: " + String.valueOf(dataSnapshot.child("gramos").getValue(Integer.class)) + "g");

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
                        intent = new Intent(DetalleAlimentosActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(DetalleAlimentosActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(DetalleAlimentosActivity.this, DietasActivity.class);
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
