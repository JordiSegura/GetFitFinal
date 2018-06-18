package vidal.sergi.getfit;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

import vidal.sergi.getfit.Objetos.Dieta;
import vidal.sergi.getfit.Objetos.FirebaseReferences;

public class DietasListAdapter extends RecyclerView.Adapter<DietasListAdapter.ViewHolder> {

    private List<Dieta> dietaList;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference(FirebaseReferences.USERS);
    public   Context mContext;
    int day,month,year,hour,minute;
    int dayFinal,montFinal,yearFinal,hourFinal,minuteFinal;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    DietasListAdapter(List<Dieta> dietaList){
        super();
        this.dietaList = dietaList;
    }

    //Crear i asignar el ViewHolder amb els components
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombreDieta;
        FrameLayout frameLayout;
        FloatingActionButton btnIdDieta;

        public ViewHolder(final View itemVIew){
            super(itemVIew);
            tvNombreDieta = itemVIew.findViewById(R.id.tvNombreDieta);
            btnIdDieta = itemVIew.findViewById(R.id.btnIdDieta);
            frameLayout = itemVIew.findViewById(R.id.frameLayoutDietas);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetalleDietaActivity.class);
                    intent.putExtra("nombreDieta", tvNombreDieta.getText());
                    context.startActivity(intent);
                }
            });

            btnIdDieta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnIdDieta.setRippleColor(Color.RED);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String email = user.getEmail();
                    usersRef.child(email.split("@")[0]).child("idDieta").setValue(String.valueOf(btnIdDieta.getTag()));
                    Toast.makeText(itemVIew.getContext(), "Ahora sigues esta dieta.", Toast.LENGTH_SHORT).show();


                    Calendar c  = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);
                    Log.d("TAG", "onDateSet: TIME1");

                    DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Holo_Dialog_MinWidth,mOnDateSetListener,year,month,day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    datePickerDialog.show();
                }
            });
            mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    montFinal = month+1;

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String email = user.getEmail();
                    if (montFinal<10){
                        int a = montFinal;
                        String with3digits = String.format("%02d", a);
                        Log.d("TAG", "onDateSet: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA "+with3digits);
                        usersRef.child(email.split("@")[0]).child("finDieta").setValue(year+"-"+montFinal+"-"+dayOfMonth);

                    }else{

                        usersRef.child(email.split("@")[0]).child("finDieta").setValue(year+"-"+montFinal+"-"+dayOfMonth);
                    }

                }
            };
        }
    }

    //Al crear el ViewHolder, inflar el layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dieta, parent, false);
        return new ViewHolder(view);
    }

    //Bindejar l'informació del JSON als components
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNombreDieta.setText(dietaList.get(position).getNombre());
        holder.frameLayout.setBackgroundResource(dietaList.get(position).getImg());
        holder.btnIdDieta.setTag(String.valueOf(dietaList.get(position).getId()));
    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return dietaList.size();
    }
}
