package vidal.sergi.getfit;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

public class DiasActivity extends AppCompatActivity {
    public final String TAG ="DiasActivity";
    Intent intent;

    ImageView ivLogo;



    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dias);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
        ivLogo = findViewById(R.id.ivLogo1);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DiasActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation4);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(DiasActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(DiasActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(DiasActivity.this, DietasActivity.class);
                        startActivity(intent);;
                        break;
                }
                return true;
            }
        });

    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        mImageUrls.add("https://image.flaticon.com/icons/png/128/42/42524.png");
        mNames.add("Lunes");

        mImageUrls.add("https://www.shareicon.net/data/256x256/2015/11/15/672701_calendar_512x512.png");
        mNames.add("Martes");

        mImageUrls.add("https://www.shareicon.net/data/256x256/2015/11/16/672906_calendar_512x512.png");
        mNames.add("Miercoles");

        mImageUrls.add("https://www.shareicon.net/data/256x256/2015/11/16/672860_calendar_512x512.png");
        mNames.add("Jueves");

        mImageUrls.add("https://www.shareicon.net/data/256x256/2015/11/15/672747_calendar_512x512.png");
        mNames.add("Viernes");

        mImageUrls.add("https://www.shareicon.net/data/256x256/2015/11/15/672757_calendar_512x512.png");
        mNames.add("Sábado");

        mImageUrls.add("https://www.shareicon.net/data/128x128/2015/11/15/672742_calendar_512x512.png");
        mNames.add("Domingo");

        initRecyclerView();
    }
    private void initRecyclerView(){
        Bundle extras2 = getIntent().getExtras();
            String value = extras2.getString("semana1");
            String contador2 = value;
            String val = contador2;



        RecyclerView recyclerView = findViewById(R.id.recycler1);
        DiasAdapter adapter = new DiasAdapter(mNames,mImageUrls,this,contador2);
        adapter.setIntento(val);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }




}
