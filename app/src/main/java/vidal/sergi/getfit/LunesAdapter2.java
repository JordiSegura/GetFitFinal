package vidal.sergi.getfit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class LunesAdapter2 extends RecyclerView.Adapter<LunesAdapter2.LunesViewHolder> {
    public static final String TAG = "LunesAdapter2";
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef,refe;
    private Context mcontext;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef3 = database.getReference("limpiar");

    public int contDesayuno = 0;
    public int contAlmuerzo = 0;
    public int contComida = 0;
    public int contMerienda = 0;
    public int contCena = 0;
    public int contRutina = 0;


    List<String> actividades;
    public String valor;
    public String dia;

    public LunesAdapter2(List<String> actividades, String valor, String dia) {
        this.actividades = actividades;
        this.valor = valor;
        this.dia = dia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public int getContDesayuno() {
        return contDesayuno;
    }

    public void setContDesayuno(int contDesayuno) {
        this.contDesayuno = contDesayuno;
    }

    public int getContAlmuerzo() {
        return contAlmuerzo;
    }

    public void setContAlmuerzo(int contAlmuerzo) {
        this.contAlmuerzo = contAlmuerzo;
    }

    public int getContComida() {
        return contComida;
    }

    public void setContComida(int contComida) {
        this.contComida = contComida;
    }

    public int getContMerienda() {
        return contMerienda;
    }

    public void setContMerienda(int contMerienda) {
        this.contMerienda = contMerienda;
    }

    public int getContCena() {
        return contCena;
    }

    public void setContCena(int contCena) {
        this.contCena = contCena;
    }

    public int getContRutina() {
        return contRutina;
    }

    public void setContRutina(int contRutina) {
        this.contRutina = contRutina;
    }

    public LunesAdapter2(List<String> actividades) {
        this.actividades = actividades;
    }

    public LunesAdapter2() {
    }

    public LunesAdapter2(Context mcontext, List<String> actividades) {
        this.mcontext = mcontext;
        this.actividades = actividades;
    }
    public static class LunesViewHolder extends RecyclerView.ViewHolder{
        TextView txt_lunes;
        CheckBox cb1;
        TextView Lunes;

        public LunesViewHolder(View itemView) {
            super(itemView);
            txt_lunes = itemView.findViewById(R.id.textview_lunes3);
            cb1 = itemView.findViewById(R.id.checkbox4);
            Lunes = itemView.findViewById(R.id.txtLunes);

        }
    }

    @NonNull
    @Override
    public LunesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyler_lunes,parent,false);
        return new LunesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LunesViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: //////////////////////////////////////////////////////");
        holder.txt_lunes.setText("rutina");
        Log.d(TAG, "onBindViewHolder: GETTER "+getDia());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Seguimiento/"+getDia());


        holder.cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (position){
                    case 0:
                        if (isChecked){
                            contAlmuerzo =1;
                            // myRef.child("almuerzo").setValue(true);
                            Log.d(TAG, "onCheckedChanged: CB1 Está chekeado");
                        }else{
                            contAlmuerzo =0;
                            //  myRef.child("almuerzo").setValue(false);
                            Log.d(TAG, "onCheckedChanged: CB1 No está checkeado");
                        }

                        break;
                    case 1:
                        if (isChecked){
                            contCena =1;
                            myRef.child("cena").setValue(true);
                            Log.d(TAG, "onCheckedChanged: CB2 Está chekeado");
                        }else{
                            contCena =0;
                            myRef.child("cena").setValue(false);
                            Log.d(TAG, "onCheckedChanged: CB2 No está checkeado");
                        }
                        break;
                    case 2:
                        if (isChecked){
                            contComida =1;
                            //       myRef.child("comida").setValue(true);
                            Log.d(TAG, "onCheckedChanged: CB3 Está chekeado");
                        }else{
                            contComida =0;
                            //      myRef.child("comida").setValue(false);
                            Log.d(TAG, "onCheckedChanged: CB3 No está checkeado");
                        }
                        break;
                    case 3:
                        if (isChecked){
                            contDesayuno =1;
                            //  myRef.child("desayuno").setValue(true);
                            Log.d(TAG, "onCheckedChanged: CB4 Está chekeado");
                        }else{
                            contDesayuno =0;
                            //myRef.child("desayuno").setValue(false);
                            Log.d(TAG, "onCheckedChanged: CB4 No está checkeado");
                        }
                        break;
                    case 4:
                        if (isChecked){
                            contMerienda=1;
                            //  myRef.child("merienda").setValue(true);
                            Log.d(TAG, "onCheckedChanged: CB5 Está chekeado");
                        }else{
                            contMerienda=0;
                            //  myRef.child("merienda").setValue(false);
                            Log.d(TAG, "onCheckedChanged: CB5 No está checkeado");
                        }
                        break;
                    case 5:
                        if (isChecked){
                            contRutina=1;

                            //   myRef.child("rutina").setValue(true);
                            Log.d(TAG, "onCheckedChanged: CB6 Está chekeado");
                        }else{contRutina=0;
                            //  myRef.child("rutina").setValue(false);
                            Log.d(TAG, "onCheckedChanged: CB6 No está checkeado");
                        }
                        break;

                }
                Integer suma = contDesayuno+contAlmuerzo+contComida+contMerienda+contCena+contRutina;
                Log.d(TAG, "onCheckedChanged: SUMAAAA "+suma);
                if (suma>=1) {
                    refe = mFirebaseDatabase.getReference().child("Totales/" + getDia());
                    refe.child(getDia()).setValue("true");
                }
                else{
                    refe = mFirebaseDatabase.getReference().child("Totales/" + getDia()).child(getDia());
                    refe.setValue("false");
                }
                Log.d(TAG, "onCheckedChanged: DEDEDEDEWDDEASF "+getDia());
                LunesActivity ah = new LunesActivity(getDia());
                ah.setDd(getDia());

                setContDesayuno(contDesayuno);
                setContAlmuerzo(contAlmuerzo);
                setContComida(contComida);
                setContMerienda(contMerienda);
                setContCena(contCena);
                setContRutina(contRutina);


            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {
                    case 0:
                      /*  Intent intent = new Intent(mcontext,LunesActivity.class);
                        mcontext.startActivity(intent);*/

                        Log.d(TAG, "onClick: Pos0");
                        break;

                    case 1:
                        Log.d(TAG, "onClick: Pos1");
                        break;
                    case 2:
                        Log.d(TAG, "onClick: Pos2");
                        break;
                    case 3:
                        Log.d(TAG, "onClick: Pos3");
                        break;
                    case 4:
                        Log.d(TAG, "onClick: Pos4");
                        break;
                    case 5:
                        Log.d(TAG, "onClick: Pos5");
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: Actividades"+actividades.size());

        return /*actividades.size()*/1;
    }




}
