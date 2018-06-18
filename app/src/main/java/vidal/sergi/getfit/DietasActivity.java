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
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vidal.sergi.getfit.Objetos.Alimento;
import vidal.sergi.getfit.Objetos.Comida;
import vidal.sergi.getfit.Objetos.Dieta;
import vidal.sergi.getfit.Objetos.Ejercicio;
import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Musculo;

/**
 * Created by alu2011543 on 12/03/2018.
 */

public class DietasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvNombreDieta;
    Intent intent;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dietas = database.getReference(FirebaseReferences.DIETAS);
    DatabaseReference dieta1 = database.getReference(FirebaseReferences.DIETA1);
    DatabaseReference dieta2 = database.getReference(FirebaseReferences.DIETA2);
    DatabaseReference dieta3 = database.getReference(FirebaseReferences.DIETA3);
    List<Comida> comidaList;
    List<Alimento> alimentoList;
    List<Dieta> dietaList;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dietas);

        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DietasActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rvDieta);
        tvNombreDieta = findViewById(R.id.tvNombreDieta);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_dietas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(DietasActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(DietasActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(DietasActivity.this, DietasActivity.class);
                        startActivity(intent);;
                        break;

                }
                return true;
            }
        });

        final Dieta ganarMusculo = new Dieta(1, R.drawable.dieta3, "Ganar Musculo", crearDieta1());
        final Dieta subirPeso = new Dieta(2, R.drawable.dieta1, "Subir Peso", crearDieta2());
        final Dieta bajarPeso = new Dieta(3, R.drawable.dieta2, "Bajar Peso", crearDieta3());
        dietaList = new ArrayList<>();
        dietaList.add(ganarMusculo);
        dietaList.add(subirPeso);
        dietaList.add(bajarPeso);

        DietasListAdapter adapter = new DietasListAdapter(dietaList);
        recyclerView.setLayoutManager (new LinearLayoutManager(DietasActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private List<Comida> crearDieta1(){
        comidaList = new ArrayList<>();
        alimentoList = new ArrayList<>();

        //Comida 1
        Alimento tortilla = new Alimento("Tortilla", 250, 100);
        alimentoList.add(tortilla);

        Comida desayuno = new Comida("Desayuno", alimentoList);
        comidaList.add(desayuno);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(desayuno.getNombre()).child(alimento.getNombre()).setValue(alimento);

        //Comida 2
        alimentoList = new ArrayList<>();

        Alimento miniBocadillo = new Alimento("Bocadillo", 200, 100);
        alimentoList.add(miniBocadillo);

        Alimento piezasFrutas = new Alimento("Fruta", 100, 50);
        alimentoList.add(piezasFrutas);

        Comida almuerzo = new Comida("Almuerzo", alimentoList);
        comidaList.add(almuerzo);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(almuerzo.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 3
        alimentoList = new ArrayList<>();

        Alimento hidratos = new Alimento("Arroz o Pasta o Legumbres", 300, 200);
        alimentoList.add(hidratos);

        Alimento proteinas = new Alimento("Pescado o Carne o Verduras", 100, 150);
        alimentoList.add(proteinas);

        Comida comida = new Comida("Comida", alimentoList);
        comidaList.add(comida);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(comida.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 4
        alimentoList = new ArrayList<>();

        Alimento tortasDeArroz = new Alimento("Tortas de Arroz", 80, 100);
        alimentoList.add(tortasDeArroz);

        Alimento pavo = new Alimento("Pavo", 100, 120);
        alimentoList.add(pavo);

        Comida merienda = new Comida("Merienda", alimentoList);
        comidaList.add(merienda);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(merienda.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 5
        alimentoList = new ArrayList<>();

        Alimento arroz = new Alimento("Arroz", 120, 70);
        alimentoList.add(arroz);

        Alimento pollo = new Alimento("Pollo", 250, 250);
        alimentoList.add(pollo);

        Comida cena = new Comida("Cena", alimentoList);
        comidaList.add(cena);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta1.getKey()).child(cena.getNombre()).child(alimento.getNombre()).setValue(alimento);

        return comidaList;
    }

    private List<Comida> crearDieta2(){
        comidaList = new ArrayList<>();
        alimentoList = new ArrayList<>();

        //Comida 1
        Alimento tortilla = new Alimento("Tortilla", 250, 100);
        alimentoList.add(tortilla);

        Comida desayuno = new Comida("Desayuno", alimentoList);
        comidaList.add(desayuno);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(desayuno.getNombre()).child(alimento.getNombre()).setValue(alimento);

        //Comida 2
        alimentoList = new ArrayList<>();

        Alimento miniBocadillo = new Alimento("Bocadillo", 200, 100);
        alimentoList.add(miniBocadillo);

        Alimento piezasFrutas = new Alimento("Fruta", 100, 50);
        alimentoList.add(piezasFrutas);

        Comida almuerzo = new Comida("Almuerzo", alimentoList);
        comidaList.add(almuerzo);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(almuerzo.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 3
        alimentoList = new ArrayList<>();

        Alimento hidratos = new Alimento("Arroz o Pasta o Legumbres", 300, 200);
        alimentoList.add(hidratos);

        Alimento proteinas = new Alimento("Pescado o Carne o Verduras", 100, 150);
        alimentoList.add(proteinas);

        Comida comida = new Comida("Comida", alimentoList);
        comidaList.add(comida);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(comida.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 4
        alimentoList = new ArrayList<>();

        Alimento tortasDeArroz = new Alimento("Tortas de Arroz", 80, 100);
        alimentoList.add(tortasDeArroz);

        Alimento pavo = new Alimento("Pavo", 100, 120);
        alimentoList.add(pavo);

        Comida merienda = new Comida("Merienda", alimentoList);
        comidaList.add(merienda);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(merienda.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 5
        alimentoList = new ArrayList<>();

        Alimento arroz = new Alimento("Arroz", 120, 70);
        alimentoList.add(arroz);

        Alimento pollo = new Alimento("Pollo", 250, 250);
        alimentoList.add(pollo);

        Comida cena = new Comida("Cena", alimentoList);
        comidaList.add(cena);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta2.getKey()).child(cena.getNombre()).child(alimento.getNombre()).setValue(alimento);

        return comidaList;
    }

    private List<Comida> crearDieta3(){
        comidaList = new ArrayList<>();
        alimentoList = new ArrayList<>();

        //Comida 1
        Alimento tortilla = new Alimento("Tortilla", 250, 100);
        alimentoList.add(tortilla);

        Comida desayuno = new Comida("Desayuno", alimentoList);
        comidaList.add(desayuno);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(desayuno.getNombre()).child(alimento.getNombre()).setValue(alimento);

        //Comida 2
        alimentoList = new ArrayList<>();

        Alimento miniBocadillo = new Alimento("Bocadillo", 200, 100);
        alimentoList.add(miniBocadillo);

        Alimento piezasFrutas = new Alimento("Fruta", 100, 50);
        alimentoList.add(piezasFrutas);

        Comida almuerzo = new Comida("Almuerzo", alimentoList);
        comidaList.add(almuerzo);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(almuerzo.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 3
        alimentoList = new ArrayList<>();

        Alimento hidratos = new Alimento("Arroz o Pasta o Legumbres", 300, 200);
        alimentoList.add(hidratos);

        Alimento proteinas = new Alimento("Pescado o Carne o Verduras", 100, 150);
        alimentoList.add(proteinas);

        Comida comida = new Comida("Comida", alimentoList);
        comidaList.add(comida);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(comida.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 4
        alimentoList = new ArrayList<>();

        Alimento tortasDeArroz = new Alimento("Tortas de Arroz", 80, 100);
        alimentoList.add(tortasDeArroz);

        Alimento pavo = new Alimento("Pavo - 120g", 100);
        alimentoList.add(pavo);

        Comida merienda = new Comida("Merienda", alimentoList);
        comidaList.add(merienda);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(merienda.getNombre()).child(alimento.getNombre()).setValue(alimento);


        //Comida 5
        alimentoList = new ArrayList<>();

        Alimento arroz = new Alimento("Arroz", 120, 70);
        alimentoList.add(arroz);

        Alimento pollo = new Alimento("Pollo", 250, 250);
        alimentoList.add(pollo);

        Comida cena = new Comida("Cena", alimentoList);
        comidaList.add(cena);

        for(Alimento alimento: alimentoList)
            dietas.child(dieta3.getKey()).child(cena.getNombre()).child(alimento.getNombre()).setValue(alimento);

        return comidaList;
    }
}