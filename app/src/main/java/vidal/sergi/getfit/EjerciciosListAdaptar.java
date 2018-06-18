package vidal.sergi.getfit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

import vidal.sergi.getfit.Objetos.Ejercicio;

import static vidal.sergi.getfit.EjerciciosActivity.getTextFromTextView1;
import static vidal.sergi.getfit.EjerciciosActivity.tvNM;

public class EjerciciosListAdaptar extends BaseAdapter {

    ValueEventListener context;
    List<Ejercicio> ejerciciosList;
    TextView tvNombreEjercicio, tvSeries, tvRepeticiones, tvDescanso, tvNombreMusculo;
    FrameLayout frameLayout;
    String nombreRutina;

    public EjerciciosListAdaptar(ValueEventListener context, List<Ejercicio> ejerciciosList, String nombreRutina) {
        this.context = context;
        this.ejerciciosList = ejerciciosList;
        this.nombreRutina = nombreRutina;
    }

    @Override
    public int getCount() {
        return ejerciciosList.size();
    }

    @Override
    public Ejercicio getItem(int position) {
        return ejerciciosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_rutinas, parent, false);

        final Ejercicio ejercicio = getItem(position);
        tvNombreEjercicio = view.findViewById(R.id.tvNombreEjercicio);
        tvNombreEjercicio.setText(ejercicio.getNombre());

        tvNombreMusculo = view.findViewById(R.id.tvNM);

        frameLayout = view.findViewById(R.id.frameLayoutEjercicio);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("svm", ejercicio.toString());

                Context context = view.getContext();
                Intent intent = new Intent(context, DetalleEjerciciosActivity.class);
                intent.putExtra("nombreEjercicio", ejercicio.getNombre());
                intent.putExtra("nombreMusculo", getTextFromTextView1(tvNM));
                intent.putExtra("nombreRutina", nombreRutina);

                context.startActivity(intent);
            }
        });
        return view;
    }
}