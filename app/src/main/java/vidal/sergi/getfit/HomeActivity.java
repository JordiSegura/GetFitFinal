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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import vidal.sergi.getfit.Objetos.FirebaseReferences;
import vidal.sergi.getfit.Objetos.Usuario;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG ="HomeActivity";
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference(FirebaseReferences.CURRENT_USER);
    DatabaseReference usersRef2 = database.getReference(FirebaseReferences.CURRENT_DAY);
    DatabaseReference usersRef3 = database.getReference(FirebaseReferences.USERS);


    Intent intent;
    public String semana;
    public String day;
    public String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    ImageView ivLogo,imgDieta,imgRutina;
    TextView txtDieta,txtRutina,txtSeguimiento,txtDietas,txtProgreso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home2);
        txtProgreso = findViewById(R.id.txtProgreso);
        txtDieta = findViewById(R.id.txtDieta);
        txtRutina = findViewById(R.id.txtRutina);
        //imgDieta = findViewById(R.id.imgDieta);
        imgRutina = findViewById(R.id.imgRutina);
        txtSeguimiento = findViewById(R.id.txtSeguimiento);
        txtDietas = findViewById(R.id.txtDietas);
        Log.d(TAG, "onCreate: started.");
       // initImageBitmaps();
            Bundle extras2 = getIntent().getExtras();
          if (extras2!=null) {
                String username = extras2.getString("nombreUsuario");
                Log.d(TAG, "onCreate: UserName " + username);
                txtProgreso.append(" "+username+"!");

                try{
                if (!username.equals(null)) {
                    usersRef.removeValue();

                    usersRef.child(username).setValue(username);
               }}
                catch (Exception e){
                    e.printStackTrace();
                }
          }
        Bundle extras3 = getIntent().getExtras();
        if (extras3!=null) {
            String username = extras3.getString("dia");
            Log.d(TAG, "onCreate: fdsgdfgfdga " + username);
            try {
                if (!username.equals(null)) {

                    usersRef2.removeValue();
                    usersRef2.child(username).setValue(username);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
            database.getReference().child("CONTADOR_FALSE/").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int count = 0;
                    Log.d(TAG, "onDataChange: jbfugewuf82t8r18 "+dataSnapshot.getValue());
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Log.d(TAG, "onDataChange: VALOR e23423423 "+snapshot.getValue().toString());
                        String e=snapshot.getValue().toString();
                        Pattern pattern = Pattern.compile("bien");
                        Matcher matcher = pattern.matcher(e);

                        while (matcher.find()){
                            count++;
                        }
                     /*   if (count>=10){
                            txtProgreso.setVisibility(View.VISIBLE);
                            txtProgreso.setText("Enhorabuena vas por buen camino!");
                        }
                        else if (count<=9){
                            txtProgreso.setVisibility(View.VISIBLE);
                            txtProgreso.setText("Procura ajustarte un poco más nuestras indicaciones");

                        }*/
                        Log.d(TAG, "onDataChange: 152dp152dp152dp152dp "+count);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        database.getReference().child("CurrentUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    final String usu = snapshot.getValue().toString();

                    database.getReference().child("users/"+usu+"/idDieta").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue().equals("1")){
                                txtDieta.setText("Dieta proteica");
                                txtDieta.setBackgroundResource(R.drawable.dieta1);


                            }else   if (dataSnapshot.getValue().equals("2")){

                                txtDieta.setText("Dieta subir peso");
                                txtDieta.setBackgroundResource(R.drawable.dieta2);

                            }else   if (dataSnapshot.getValue().equals("3")){

                                txtDieta.setText("Dieta bajar peso");
                                txtDieta.setBackgroundResource(R.drawable.dieta3);

                            }else   if (dataSnapshot.getValue().equals("")){
                                txtDieta.setText("No sigues ninguna dieta :(");

                            }
                            else{
                                txtDieta.setText("No sigues ninguna dieta :(");

                            }



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    database.getReference().child("users/"+usu+"/idRutina").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue().equals("1")){
                               // imgRutina.setImageResource(R.drawable.gym1);
                                txtRutina.setText("Rutina-definición");
                                txtRutina.setBackgroundResource(R.drawable.gym1);

                            }else   if (dataSnapshot.getValue().equals("2")){
                               // imgRutina.setImageResource(R.drawable.gym2);
                                txtRutina.setText("Rutina-volumen");
                                txtRutina.setBackgroundResource(R.drawable.gym2);

                            }else   if (dataSnapshot.getValue().equals("3")){
                               // imgRutina.setImageResource(R.drawable.gym3);
                                txtRutina.setText("Rutina-fuerza");
                                txtRutina.setBackgroundResource(R.drawable.gym3);

                            }else   if (dataSnapshot.getValue().equals("")){
                                txtRutina.setText("No sigues ninguna rutina :(");

                            }else{
                                txtRutina.setBackgroundResource(R.drawable.biceps);

                                txtRutina.setText("No sigues ninguna rutina :(");

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
       final String usuario = email.split("@")[0];

        database.getReference().child("users/"+usuario+"/finRutina").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //txtRutina.append(" hasta el "+dataSnapshot.getValue().toString());
                String hoy = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                String fechaSetteada = dataSnapshot.getValue().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                try {

                    Date date1= sdf.parse(hoy);
                    Date date2= sdf.parse(fechaSetteada);
                    DateTime dt1 = new DateTime(date1);
                    DateTime dt2 = new DateTime(date2);

                    long diff = date1.getTime() - date2.getTime();
                    DateFormat format2=new SimpleDateFormat("EEEE");
                    final String finalDay=format2.format(date1);
                    final String finalDay2=format2.format(date2);


                   // usersRef2.child(usuario).child("finRutina").setValue(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                    Log.d(TAG, "onDataChange: BTWWW "+Days.daysBetween(dt1,dt2).getDays());




                } catch (ParseException e) {
                    e.printStackTrace();

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
     txtSeguimiento.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String email = user.getEmail();
                            final String usuario = email.split("@")[0];

                            database.getReference().child("users/"+usuario+"/diferenciaDias").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Log.d(TAG, "onDataChange: finRutina "+dataSnapshot.getValue().toString());

                                    intent = new Intent(HomeActivity.this, SeguimientoRutinasActivity.class);
                                    intent.putExtra("diferencia",dataSnapshot.getValue().toString());
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                    });

        txtDietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = user.getEmail();
                final String usuario = email.split("@")[0];

               database.getReference().child("users/"+usuario+"/diferenciaDias2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "onDataChange: finRutina "+dataSnapshot.getValue().toString());

                        intent = new Intent(HomeActivity.this, SeguimientoDietasActivity.class);
                        intent.putExtra("diferencia2",dataSnapshot.getValue().toString());
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        ivLogo = findViewById(R.id.ivLogo2);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HomeActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation1);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_rutinas:
                        intent = new Intent(HomeActivity.this, RutinasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_dietas:
                        intent = new Intent(HomeActivity.this, DietasActivity.class);
                        startActivity(intent);;
                        break;
                }
                return true;
            }
        });
    }


}
