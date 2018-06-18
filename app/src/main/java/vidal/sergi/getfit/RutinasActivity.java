package vidal.sergi.getfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Musculo;
import vidal.sergi.getfit.Objetos.Rutina;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class RutinasActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tvNombreRutina,txtResultado;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    Intent intent;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rutinas = database.getReference(FirebaseReferences.RUTINAS);
    DatabaseReference rutina1 = database.getReference(FirebaseReferences.RUTINA1);
    DatabaseReference rutina2 = database.getReference(FirebaseReferences.RUTINA2);
    DatabaseReference rutina3 = database.getReference(FirebaseReferences.RUTINA3);
    List<Musculo> musculoList;
    List<Ejercicio> ejercicioList;
    List<Rutina> rutinaList;
    ImageView ivLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rutinas);



txtResultado = findViewById(R.id.txtResultado);
        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RutinasActivity.this, AjustesActivity.class);
                startActivity(intent);

            }
        });


        recyclerView = findViewById(R.id.rvRutina);
        tvNombreRutina = findViewById(R.id.tvNombreRutina);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_rutinas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(RutinasActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(RutinasActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(RutinasActivity.this, DietasActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        final Rutina definicion = new Rutina(1, R.drawable.gym1, "Definición", crearRutina1());
        final Rutina volumen = new Rutina(2, R.drawable.gym2, "Volumen", crearRutina2());
        final Rutina fuerza = new Rutina(3, R.drawable.gym3, "Fuerza", crearRutina3());
        rutinaList = new ArrayList<>();
        rutinaList.add(definicion);
        rutinaList.add(volumen);
        rutinaList.add(fuerza);

        RutinasListAdapter adapter = new RutinasListAdapter(rutinaList);
        recyclerView.setLayoutManager (new LinearLayoutManager(RutinasActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }



    private List<Musculo> crearRutina1(){
        musculoList = new ArrayList<>();
        ejercicioList = new ArrayList<>();

        //Grupo 1
        Ejercicio pressBarra = new Ejercicio("Press inclinado con barra",4, 12, 2);
        ejercicioList.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio("Press banca con mancuernas", 3, 10, 2);
        ejercicioList.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio("Aperturas inclinadas 15º", 3, 15, 1.30);
        ejercicioList.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio("Peck deck", 3, 12, 1.30);
        ejercicioList.add(peckDeck);

        Musculo pectoral = new Musculo("Pectoral", ejercicioList);
        musculoList.add(pectoral);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(pectoral.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio("Press Frontal en Maquina", 4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio("Elevaciones Laterales en Polea", 4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio("Press Tras Nuca", 3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo("Hombro Frontal", ejercicioList);
        musculoList.add(hombroFrontal);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(hombroFrontal.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio("Triceps en Polea", 4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio("Press Frances en Polea", 4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo("Tríceps", ejercicioList);
        musculoList.add(triceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(triceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 4
        ejercicioList = new ArrayList<>();

        Ejercicio jalonPecho = new Ejercicio("Jalon al Pecho", 4, 12, 2);
        ejercicioList.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio("Remo Bajo en Polea", 4, 10, 1.30);
        ejercicioList.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio("Jalon al Pecho Invertido", 4, 10, 2);
        ejercicioList.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio("Jalon tras Nuca", 3, 12, 2);
        ejercicioList.add(jalonNuca);

        Musculo dorsal = new Musculo("Dorsal", ejercicioList);
        musculoList.add(dorsal);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(dorsal.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio("Pajaros en MAquina", 4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio("Encogimientos con Mancuernas", 3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo("Hombro Posterior", ejercicioList);
        musculoList.add(hombrosPosterior);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(hombrosPosterior.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio("Curl con Barra en Polea", 4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio("Curl Alterno con Mancuernas", 4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo("Biceps", ejercicioList);
        musculoList.add(biceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(biceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 7
        ejercicioList = new ArrayList<>();

        Ejercicio extensionCuadriceps = new Ejercicio("Extension de Cuadriceps", 4, 15, 2);
        ejercicioList.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio("Sentadillas", 4, 12, 2);
        ejercicioList.add(sentadilla);
        Ejercicio prensa = new Ejercicio("Prensa", 3, 12, 2);
        ejercicioList.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio("Jalon tras Nuca", 3, 10, 0);
        ejercicioList.add(zancadasMultipower);

        Musculo cuadriceps = new Musculo("Cuadriceps", ejercicioList);
        musculoList.add(cuadriceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(cuadriceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio("Femoral Tumbado", 5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo("Femoral", ejercicioList);
        musculoList.add(femoral);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(femoral.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio("Gemelos sentado en Maquina", 5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo("Gemelos", ejercicioList);
        musculoList.add(gemelos);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(gemelos.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio("Elevaciones de Tronco", 4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio("Elevaciones de Piernas", 4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo("Abdominales", ejercicioList);
        musculoList.add(abdominales);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina1.getKey()).child(abdominales.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        return musculoList;
    }

    private List<Musculo> crearRutina2(){
        musculoList = new ArrayList<>();
        ejercicioList = new ArrayList<>();

        //Grupo 1
        Ejercicio pressBarra = new Ejercicio("Press inclinado con barra",4, 12, 2);
        ejercicioList.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio("Press banca con mancuernas", 3, 10, 2);
        ejercicioList.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio("Aperturas inclinadas 15º", 3, 15, 1.30);
        ejercicioList.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio("Peck deck", 3, 12, 1.30);
        ejercicioList.add(peckDeck);

        Musculo pectoral = new Musculo("Pectoral", ejercicioList);
        musculoList.add(pectoral);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(pectoral.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio("Press Frontal en Maquina", 4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio("Elevaciones Laterales en Polea", 4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio("Press Tras Nuca", 3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo("Hombro Frontal", ejercicioList);
        musculoList.add(hombroFrontal);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(hombroFrontal.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio("Triceps en Polea", 4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio("Press Frances en Polea", 4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo("Triceps", ejercicioList);
        musculoList.add(triceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(triceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 4
        ejercicioList = new ArrayList<>();

        Ejercicio jalonPecho = new Ejercicio("Jalon al Pecho", 4, 12, 2);
        ejercicioList.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio("Remo Bajo en Polea", 4, 10, 1.30);
        ejercicioList.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio("Jalon al Pecho Invertido", 4, 10, 2);
        ejercicioList.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio("Jalon tras Nuca", 3, 12, 2);
        ejercicioList.add(jalonNuca);

        Musculo dorsal = new Musculo("Dorsal", ejercicioList);
        musculoList.add(dorsal);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(dorsal.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio("Pajaros en MAquina", 4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio("Encogimientos con Mancuernas", 3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo("Hombro Posterior", ejercicioList);
        musculoList.add(hombrosPosterior);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(hombrosPosterior.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio("Curl con Barra en Polea", 4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio("Curl Alterno con Mancuernas", 4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo("Biceps", ejercicioList);
        musculoList.add(biceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(biceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 7
        ejercicioList = new ArrayList<>();

        Ejercicio extensionCuadriceps = new Ejercicio("Extension de Cuadriceps", 4, 15, 2);
        ejercicioList.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio("Sentadillas", 4, 12, 2);
        ejercicioList.add(sentadilla);
        Ejercicio prensa = new Ejercicio("Prensa", 3, 12, 2);
        ejercicioList.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio("Jalon tras Nuca", 3, 10, 0);
        ejercicioList.add(zancadasMultipower);

        Musculo cuadriceps = new Musculo("Cuadriceps", ejercicioList);
        musculoList.add(cuadriceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(cuadriceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio("Femoral Tumbado", 5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo("Femoral", ejercicioList);
        musculoList.add(femoral);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(femoral.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio("Gemelos sentado en Maquina", 5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo("Gemelos", ejercicioList);
        musculoList.add(gemelos);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(gemelos.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio("Elevaciones de Tronco", 4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio("Elevaciones de Piernas", 4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo("Abdominales", ejercicioList);
        musculoList.add(abdominales);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina2.getKey()).child(abdominales.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        return musculoList;
    }

    private List<Musculo> crearRutina3(){
        musculoList = new ArrayList<>();
        ejercicioList = new ArrayList<>();

        //Grupo 1
        Ejercicio pressBarra = new Ejercicio("Press inclinado con barra",4, 12, 2);
        ejercicioList.add(pressBarra);
        Ejercicio pressMancuernas = new Ejercicio("Press banca con mancuernas", 3, 10, 2);
        ejercicioList.add(pressMancuernas);
        Ejercicio aperturasInclinadas = new Ejercicio("Aperturas inclinadas 15º", 3, 15, 1.30);
        ejercicioList.add(aperturasInclinadas);
        Ejercicio peckDeck = new Ejercicio("Peck deck", 3, 12, 1.30);
        ejercicioList.add(peckDeck);

        Musculo pectoral = new Musculo("Pectoral", ejercicioList);
        musculoList.add(pectoral);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(pectoral.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

//        //Grupo 2
        ejercicioList = new ArrayList<>();

        Ejercicio pressFrontal = new Ejercicio("Press Frontal en Maquina", 4, 12, 2);
        ejercicioList.add(pressFrontal);
        Ejercicio elevacionesLateralPolea = new Ejercicio("Elevaciones Laterales en Polea", 4, 10, 0);
        ejercicioList.add(elevacionesLateralPolea);
        Ejercicio pressNuca = new Ejercicio("Press Tras Nuca", 3, 10, 2);
        ejercicioList.add(pressNuca);

        Musculo hombroFrontal = new Musculo("Hombro Frontal", ejercicioList);
        musculoList.add(hombroFrontal);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(hombroFrontal.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 3
        ejercicioList = new ArrayList<>();

        Ejercicio tricepsPolea = new Ejercicio("Triceps en Polea", 4, 12, 1.30);
        ejercicioList.add(tricepsPolea);
        Ejercicio pressFrances = new Ejercicio("Press Frances en Polea", 4, 12, 1.30);
        ejercicioList.add(pressFrances);

        Musculo triceps = new Musculo("Triceps", ejercicioList);
        musculoList.add(triceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(triceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 4
        ejercicioList = new ArrayList<>();

        Ejercicio jalonPecho = new Ejercicio("Jalon al Pecho", 4, 12, 2);
        ejercicioList.add(jalonPecho);
        Ejercicio remoBajoPolea = new Ejercicio("Remo Bajo en Polea", 4, 10, 1.30);
        ejercicioList.add(remoBajoPolea);
        Ejercicio jalonPechoInvertido = new Ejercicio("Jalon al Pecho Invertido", 4, 10, 2);
        ejercicioList.add(jalonPechoInvertido);
        Ejercicio jalonNuca = new Ejercicio("Jalon tras Nuca", 3, 12, 2);
        ejercicioList.add(jalonNuca);

        Musculo dorsal = new Musculo("Dorsal", ejercicioList);
        musculoList.add(dorsal);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(dorsal.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 5
        ejercicioList = new ArrayList<>();

        Ejercicio pajarosMaquina = new Ejercicio("Pajaros en MAquina", 4, 12, 1.30);
        ejercicioList.add(pajarosMaquina);
        Ejercicio encogimientosMancuernas = new Ejercicio("Encogimientos con Mancuernas", 3, 12, 1.15);
        ejercicioList.add(encogimientosMancuernas);

        Musculo hombrosPosterior = new Musculo("Hombro Posterior", ejercicioList);
        musculoList.add(hombrosPosterior);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(hombrosPosterior.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 6
        ejercicioList = new ArrayList<>();

        Ejercicio curlBarraPolea = new Ejercicio("Curl con Barra en Polea", 4, 12, 1);
        ejercicioList.add(curlBarraPolea);
        Ejercicio curlMancuernas = new Ejercicio("Curl Alterno con Mancuernas", 4, 12, 1);
        ejercicioList.add(curlMancuernas);

        Musculo biceps = new Musculo("Biceps", ejercicioList);
        musculoList.add(biceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(biceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 7
        ejercicioList = new ArrayList<>();

        Ejercicio extensionCuadriceps = new Ejercicio("Extension de Cuadriceps", 4, 15, 2);
        ejercicioList.add(extensionCuadriceps);
        Ejercicio sentadilla = new Ejercicio("Sentadillas", 4, 12, 2);
        ejercicioList.add(sentadilla);
        Ejercicio prensa = new Ejercicio("Prensa", 3, 12, 2);
        ejercicioList.add(prensa);
        Ejercicio zancadasMultipower = new Ejercicio("Jalon tras Nuca", 3, 10, 0);
        ejercicioList.add(zancadasMultipower);

        Musculo cuadriceps = new Musculo("Cuadriceps", ejercicioList);
        musculoList.add(cuadriceps);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(cuadriceps.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 8
        ejercicioList = new ArrayList<>();

        Ejercicio femoralTumbado = new Ejercicio("Femoral Tumbado", 5, 12, 2);
        ejercicioList.add(femoralTumbado);

        Musculo femoral = new Musculo("Femoral", ejercicioList);
        musculoList.add(femoral);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(femoral.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 9
        ejercicioList = new ArrayList<>();

        Ejercicio gemelosMaquina = new Ejercicio("Gemelos sentado en Maquina", 5, 12, 1);
        ejercicioList.add(gemelosMaquina);

        Musculo gemelos = new Musculo("Gemelos", ejercicioList);
        musculoList.add(gemelos);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(gemelos.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        //Grupo 10
        ejercicioList = new ArrayList<>();

        Ejercicio elevacionesTronco = new Ejercicio("Elevaciones de Tronco", 4, 20, 1);
        ejercicioList.add(elevacionesTronco);
        Ejercicio elevacionesPiernas = new Ejercicio("Elevaciones de Piernas", 4, 20, 1);
        ejercicioList.add(elevacionesPiernas);

        Musculo abdominales = new Musculo("Abdominales", ejercicioList);
        musculoList.add(abdominales);

        for(Ejercicio ejercicio: ejercicioList)
            rutinas.child(rutina3.getKey()).child(abdominales.getNombre()).child(ejercicio.getNombre()).setValue(ejercicio);

        return musculoList;
    }

}