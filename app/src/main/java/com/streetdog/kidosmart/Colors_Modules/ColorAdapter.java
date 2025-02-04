package com.streetdog.kidosmart.Colors_Modules;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.streetdog.kidosmart.R;

import java.util.ArrayList;
import java.util.Locale;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    Context context;
    ArrayList<Color_Model> arrayList;
    public ColorAdapter(Context context, ArrayList<Color_Model> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.color_tile,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.colorCode.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(arrayList.get(position).colorCode)));
        holder.colorName.setText(arrayList.get(position).colorName);
        holder.ttsbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                holder.textToSpeech.speak(arrayList.get(position).colorName+" color", TextToSpeech.QUEUE_FLUSH, null,null);
            }
        });
    }
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView colorCode,ttsbutton;
        TextView colorName;
        TextToSpeech textToSpeech;
        public ViewHolder(View itemView){
            super(itemView);

            colorName=itemView.findViewById(R.id.colorName);
            colorCode=itemView.findViewById(R.id.colorCode);
            ttsbutton=itemView.findViewById(R.id.ttsbutton);

            textToSpeech=new TextToSpeech(context, status -> {
                textToSpeech.setLanguage(Locale.US);
                textToSpeech.setSpeechRate(0.9f);
                textToSpeech.setPitch(1.1f);
            });
        }
    }
}
