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

import vidal.sergi.getfit.Objetos.Alimento;
import vidal.sergi.getfit.Objetos.Ejercicio;

import static vidal.sergi.getfit.AlimentosActivity.tvNC;
import static vidal.sergi.getfit.EjerciciosActivity.getTextFromTextView1;
import static vidal.sergi.getfit.EjerciciosActivity.tvNM;

public class AlimentosListAdaptar extends BaseAdapter {

    ValueEventListener context;
    List<Alimento> alimentoList;
    TextView tvNombreAlimento, tvCalorias, tvGramos, tvDescanso, tvNombreComida;
    FrameLayout frameLayout;
    String nombreDieta;

    public AlimentosListAdaptar(ValueEventListener context, List<Alimento> alimentoList, String nombreDieta) {
        this.context = context;
        this.alimentoList = alimentoList;
        this.nombreDieta = nombreDieta;
    }

    @Override
    public int getCount() {
        return alimentoList.size();
    }

    @Override
    public Alimento getItem(int position) {
        return alimentoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_dietas, parent, false);

        final Alimento alimento = getItem(position);
        tvNombreAlimento = view.findViewById(R.id.tvNombreAlimento);
        tvNombreAlimento.setText(alimento.getNombre());

        tvNombreComida = view.findViewById(R.id.tvNC);

        frameLayout = view.findViewById(R.id.frameLayoutAlimento);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("svm", alimento.toString());

                Context context = view.getContext();
                Intent intent = new Intent(context, DetalleAlimentosActivity.class);
                intent.putExtra("nombreAlimento", alimento.getNombre());
                intent.putExtra("nombreComida", getTextFromTextView1(tvNC));
                intent.putExtra("nombreDieta", nombreDieta);

                context.startActivity(intent);
            }
        });
        return view;
    }
}

