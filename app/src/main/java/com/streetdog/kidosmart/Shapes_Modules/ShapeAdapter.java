package com.streetdog.kidosmart.Shapes_Modules;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.*;
import android.widget.*;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.streetdog.kidosmart.R;
import java.util.*;

public class ShapeAdapter extends RecyclerView.Adapter<ShapeAdapter.ViewHolder> {

    Context context;
    ArrayList<Shape_Model> arrayList;

    public ShapeAdapter(Context context, ArrayList<Shape_Model> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.shape_tile, parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).name);
        holder.description.setText(arrayList.get(position).description);
        Glide.with(context).load(arrayList.get(position).image).into(holder.image);
        holder.ttsbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                holder.textToSpeech.speak("This is a "+arrayList.get(position).name,TextToSpeech.QUEUE_FLUSH,null,null);
            }
        });
    }
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name,description;
        TextToSpeech textToSpeech;
        CardView ttsbutton;
        public ViewHolder(View itemView){
            super(itemView);

            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);
            image=itemView.findViewById(R.id.image);
            ttsbutton=itemView.findViewById(R.id.ttsbutton);

            textToSpeech=new TextToSpeech(context, status -> {
                textToSpeech.setLanguage(Locale.US);
                textToSpeech.setSpeechRate(0.9f);
                textToSpeech.setPitch(1.1f);
            });
        }
    }
}
