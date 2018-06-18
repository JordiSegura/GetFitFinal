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

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class AjustesActivity extends AppCompatActivity{

    TextView editarPerfil, politica, anuncios, version, ayuda;
    Intent intent;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ajustes);

        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AjustesActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(AjustesActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(AjustesActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(AjustesActivity.this, DietasActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        editarPerfil = findViewById(R.id.editarPerfil);
        politica = findViewById(R.id.politica);
        anuncios = findViewById(R.id.anuncios);
        version = findViewById(R.id.version);
        ayuda = findViewById(R.id.ayuda);

        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
                startActivity(intent);
            }
        });

//        politica.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
//                startActivity(intent);
//            }
//        });
//        anuncios.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
//                startActivity(intent);
//            }
//        });
//        version.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
//                startActivity(intent);
//            }
//        });
//        ayuda.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AjustesActivity.this, EditarPerfilActivity.class);
//                startActivity(intent);
//            }
//        });
    }

}
