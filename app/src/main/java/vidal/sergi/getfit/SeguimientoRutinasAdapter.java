package vidal.sergi.getfit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class SeguimientoRutinasAdapter extends RecyclerView.Adapter<SeguimientoRutinasAdapter.ViewHolder> {
    private static final String TAG = "rererer";
    Intent intent;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference(FirebaseReferences.CURRENT_USER);
    DatabaseReference usersRef3 = database.getReference(FirebaseReferences.mi_dia);
    DatabaseReference usersRef4 = database.getReference(FirebaseReferences.DIA_CLIC);
    DatabaseReference usersRef5 = database.getReference(FirebaseReferences.TOTALES);
    DatabaseReference usersRef6 = database.getReference(FirebaseReferences.CONTADOR_TRUE);
    DatabaseReference usersRef7 = database.getReference(FirebaseReferences.CONTADOR_FALSE);
    public int p = 0;
    public int cc = 0;


    DatabaseReference usersRef2 = database.getReference(FirebaseReferences.USERS);
    String diferenciaDias;
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mcontext;
    LocalDate dias;
    String test;
    String test2;
    public String test3;
    public int contador = 0;
public String limpiar;

    public SeguimientoRutinasAdapter(String limpiar) {
        this.limpiar = limpiar;
    }

    public String getLimpiar() {
        return limpiar;
    }

    public void setLimpiar(String limpiar) {
        this.limpiar = limpiar;
    }

    Query lastQuery = usersRef4.orderByKey().limitToLast(1);

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public SeguimientoRutinasAdapter(LocalDate dias) {
        this.dias = dias;
    }

    public LocalDate getDias() {
        return dias;
    }

    public void setDias(LocalDate dias) {
        this.dias = dias;
    }

    public String intento;

    public String getDiferenciaDias() {
        return diferenciaDias;
    }

    public void setDiferenciaDias(String diferenciaDias) {
        this.diferenciaDias = diferenciaDias;
    }


    public SeguimientoRutinasAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mcontext, String intento, String diferenciaDias) {
        this.diferenciaDias = diferenciaDias;
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mcontext = mcontext;
        this.intento = intento;
    }

    public String getIntento() {
        return intento;
    }

    public void setIntento(String intento) {
        this.intento = intento;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_seguimiento_rutinas_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.image.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

      //  Glide.with(mcontext).asBitmap().load(mImages.get(position)).into(holder.image);
        int dias = Integer.parseInt(getDiferenciaDias());
        Log.d(TAG, "onBindViewHolder: booleano "+getLimpiar());
        for (int i = 0; i <= 3; i++) {
            Log.d(TAG, "onBindViewHolder: IVALE " + i);

        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        final String usuario = email.split("@")[0];

        database.getReference().child("CurrentUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String usu = snapshot.getValue().toString();
              database.getReference().child("users/" + usu + "/finRutina").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.d(TAG, "onDataChange: finRutina " + dataSnapshot.getValue().toString());
                            String hoy = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                            Log.d(TAG, "onDataChange: HOYYYYYYY " + hoy);
                            String otro = dataSnapshot.getValue().toString();
                            Log.d(TAG, "onDataChange: OTROOO " + otro);
                            String s = hoy;
                            String en = otro;

                            LocalDate start = LocalDate.parse(s);
                            LocalDate end = LocalDate.parse(en);
                            final int days = Days.daysBetween(start, end).getDays();
                            Log.d(TAG, "onDataChange: ssssssssssssssssssssssssssssssss " + days);
                            usersRef2.child(usuario).child("diferenciaDias").setValue(days);

                            List<LocalDate> dates = new ArrayList<LocalDate>(days);
                            for (int i = 0; i < days; i++) {
                                LocalDate d = start.withFieldAdded(DurationFieldType.days(), i);
                                dates.add(d);
                                Log.d(TAG, "onDataChange: DDDDDDDDDDDDDDDDDDDDDDDD " + d);
                                for (int x = 0; x < dates.size(); x++) {
                                    Log.d(TAG, "onDataChange: YEEYYEYEY " + dates.get(x).toString());


                                }

                            }
                            holder.imageName.setText(String.valueOf(dates.get(position)));

                            List<LocalDate> totalDates = new ArrayList<>();
                            while (!start.isAfter(end)) {
                                totalDates.add(start);
                                start = start.plusDays(1);
                                Log.d(TAG, "onDataChange: STARAT " + start);

                            }
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


                            database.getReference().child("CurrentDay").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Log.d(TAG, "onDataChange: asdnkjasdasojdnaei " + snapshot.getValue().toString());
                                        final String fDia = snapshot.getValue().toString();
                                        Log.d(TAG, "onClick: TETETEWTEWTEW "+test2);
                                        database.getReference().child("DiaClick/").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Log.d(TAG, "onDataChange: feureutreutrue "+dataSnapshot.getValue());
                                                test3 = dataSnapshot.getValue().toString();
                                                final String miDia = dataSnapshot.getValue().toString();
                                                Log.d(TAG, "onDataChange: hheheheheeheh "+miDia);
                                                usersRef5.child(dataSnapshot.getValue().toString()).setValue(dataSnapshot.getValue().toString());

                                                database.getReference().child("Totales/" + miDia).addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        int count = 0;
                                                        int count2 = 0;


                                                        String cond = dataSnapshot.getValue().toString();
                                                        Log.d(TAG, "onDataChange: ccodocodsofods "+cond);
                                                        Pattern pattern = Pattern.compile("true");
                                                        Matcher matcher = pattern.matcher(cond);
                                                        Pattern pattern2 = Pattern.compile("false");
                                                        Matcher matcher2 = pattern2.matcher(cond);
                                                        while (matcher.find()){
                                                            count++;

                                                        }
                                                        while (matcher2.find()){
                                                            count2++;

                                                        }
                                                       // usersRef6.removeValue();

                                                        usersRef6.setValue(count);
                                                        Log.d(TAG, "onDataChange: dsfjidjisidfsiji "+miDia);
                                                       database.getReference().child("CONTADOR_TRUE/").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                String x = dataSnapshot.getValue().toString();
                                                                Log.d(TAG, "onDataChange: retretretretre1 "+holder.imageName.getText());
                                                                Log.d(TAG, "onDataChange: retretretretre2 "+miDia);
                                                                if (dataSnapshot.getValue().toString().contains("1") && holder.imageName.getText().equals(miDia)   ){
                                                                    holder.imageName.setClickable(false);
                                                                    holder.image.setClickable(false);
                                                                    cc++;
                                                                  //  holder.imageName2.setText(" bien");

                                                                    holder.borde.setBackgroundColor(0xFF00FF00);
                                                                    usersRef7.child(miDia).setValue("bien");

                                                                }



                                                            }

                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {

                                                            }
                                                        });



                                                        }




                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
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




        holder.imageName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: HIHIHIHII ");

                while (true) {
                    String hola = holder.imageName.getText().toString();

                    Log.d(TAG, "onClick: TETETEWTEWTEW "+holder.imageName2.getText());
                    test2 = holder.imageName2.getText().toString();
                    //usersRef4.removeValue();

                    usersRef3.child("MiDia").setValue(test2);

                    usersRef4.setValue(holder.imageName.getText().toString());

                    setTest2(holder.imageName2.getText().toString());
                    intent = new Intent(mcontext, LunesActivity2.class);
                    intent.putExtra("c", getIntento().toString());
                    intent.putExtra("d", holder.imageName.getText());
                    mcontext.startActivity(intent);
                    break;

                }
            }
        });
        /*database.getReference().child("limpiar/limpiar" ).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().toString().contains("limpiar")){
                    holder.borde.setBackgroundColor(0x00000000);
                    holder.imageName2.setText("");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }


    public int dif = 0;

    @Override
    public int getItemCount() {

        String x = getDiferenciaDias();
        Log.d(TAG, "getItemCount: XX " + x);
        int r = Integer.parseInt(x);
        return r;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName,imageName2,borde;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgRutina);
            imageName = itemView.findViewById(R.id.grav);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            imageName2 = itemView.findViewById(R.id.image_name2);
            borde = itemView.findViewById(R.id.borde);
        }
    }
}
