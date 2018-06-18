package vidal.sergi.getfit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import static vidal.sergi.getfit.DetalleRutinaActivity.getTextFromTextView;
import static vidal.sergi.getfit.DetalleRutinaActivity.tvNR;
import static vidal.sergi.getfit.EjerciciosActivity.getTextFromTextView1;

public class DetalleRutinaListAdapter extends RecyclerView.Adapter<DetalleRutinaListAdapter.ViewHolder> {

    private List<String> musculosList;
    private List<Integer> fotosList;

    DetalleRutinaListAdapter(List<String> musculosList, List<Integer> fotosList){
        super();
        this.musculosList = musculosList;
        this.fotosList = fotosList;
    }

    //Crear i asignar el ViewHolder amb els components
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreMusculo;
        FrameLayout frameLayout;

        public ViewHolder(View itemVIew) {
            super(itemVIew);
            tvNombreMusculo = itemVIew.findViewById(R.id.tvNombreMusculo);
            frameLayout = itemVIew.findViewById(R.id.frameLayoutRutinas);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, EjerciciosActivity.class);
                    intent.putExtra("nombreMusculo", tvNombreMusculo.getText());
                    intent.putExtra("nombreRutina", getTextFromTextView(tvNR));
                    context.startActivity(intent);
                }
            });
        }
    }

    //Al crear el ViewHolder, inflar el layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalle_rutina, parent, false);
        return new ViewHolder(view);
    }

    //Bindejar l'informació del JSON als components
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNombreMusculo.setText(musculosList.get(position));
        holder.frameLayout.setBackgroundResource(fotosList.get(position));
    }

    //Retornar la cantitad de players
    @Override
    public int getItemCount() {
        return musculosList.size();
    }
}
