package com.streetdog.kidosmart.Numbers_Modules;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.*;
import android.widget.*;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.streetdog.kidosmart.R;
import java.util.ArrayList;
import java.util.Locale;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {
    Context context;
    ArrayList<Number_Model> arrayList;
    NumberAdapter(Context context, ArrayList<Number_Model> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.number_tile, parent,false);
        ViewHolder viewholder=new ViewHolder(view);
        return viewholder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).name);
        holder.word.setText(arrayList.get(position).word);
        Glide.with(context).load(arrayList.get(position).image1).into(holder.image1);
        Glide.with(context).load(arrayList.get(position).image2).into(holder.image2);
        holder.ttsbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                holder.textToSpeech.speak(arrayList.get(position).word,TextToSpeech.QUEUE_FLUSH,null,null);
            }
        });
    }

    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,word;
        ImageView image1,image2;
        CardView ttsbutton;
        TextToSpeech textToSpeech;
        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            word=itemView.findViewById(R.id.word);
            image1=itemView.findViewById(R.id.image1);
            image2=itemView.findViewById(R.id.image2);
            ttsbutton=itemView.findViewById(R.id.ttsbutton);

            textToSpeech=new TextToSpeech(context, status -> {
                textToSpeech.setLanguage(Locale.US);
                textToSpeech.setSpeechRate(0.9f);
                textToSpeech.setPitch(1.1f);
            });
        }
    }
}
