package vidal.sergi.getfit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder>  {
    private static final String TAG ="RecyclerViewAdapter";
    Intent intent;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef,refe;

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mcontext;
    public String intento;

    public DiasAdapter(Context mcontext, String intento) {
        this.mcontext = mcontext;
        this.intento = intento;
    }

    public DiasAdapter() {
    }

    public DiasAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mcontext, String intento) {
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
        View view = LayoutInflater.from(parent. getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        LunesAdapter lunesAdapter=new LunesAdapter();
        Log.d(TAG, "onBindViewHolder: called");
        holder.image.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        Glide.with(mcontext).asBitmap().load(mImages.get(position)).into(holder.image);
        holder.imageName.setText(mImageNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ mImageNames.get(position));
                Toast.makeText(mcontext,mImageNames.get(position), Toast.LENGTH_SHORT).show();

                switch (position){
                    case 0: intent = new Intent(mcontext,LunesActivity.class); intent.putExtra("c",getIntento().toString());intent.putExtra("d",mImageNames.get(position)); mcontext.startActivity(intent);break;
                    case 1: intent = new Intent(mcontext,LunesActivity.class); intent.putExtra("c",getIntento().toString()); intent.putExtra("d",mImageNames.get(position));mcontext.startActivity(intent);break;
                    case 2: intent = new Intent(mcontext,LunesActivity.class); intent.putExtra("c",getIntento().toString());intent.putExtra("d",mImageNames.get(position));mcontext.startActivity(intent);break;
                    case 3: intent = new Intent(mcontext,LunesActivity.class); intent.putExtra("c",getIntento().toString());intent.putExtra("d",mImageNames.get(position));mcontext.startActivity(intent);break;
                    case 4: intent = new Intent(mcontext,LunesActivity.class); intent.putExtra("c",getIntento().toString());intent.putExtra("d",mImageNames.get(position));mcontext.startActivity(intent);break;
                    case 5: intent = new Intent(mcontext,LunesActivity.class); intent.putExtra("c",getIntento().toString()); intent.putExtra("d",mImageNames.get(position));mcontext.startActivity(intent);break;
                    case 6: intent = new Intent(mcontext,LunesActivity.class); intent.putExtra("c",getIntento().toString()); intent.putExtra("d",mImageNames.get(position));mcontext.startActivity(intent);break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgRutina);
            imageName = itemView.findViewById(R.id.grav);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
