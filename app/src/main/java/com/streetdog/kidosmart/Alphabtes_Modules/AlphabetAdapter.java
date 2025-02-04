package com.streetdog.kidosmart.Alphabtes_Modules;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.*;
import android.widget.*;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.streetdog.kidosmart.R;
import java.util.ArrayList;
import java.util.Locale;

import com.bumptech.glide.Glide;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> {
    Context context;
    ArrayList<Alphabet_Model> arrayList;
    AlphabetAdapter(Context context, ArrayList<Alphabet_Model> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.alphabet_tile, parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.alphaChar.setText(arrayList.get(position).alphaChar);
        holder.alphaWord.setText(arrayList.get(position).alphaWord);
        Glide.with(context).load(arrayList.get(position).alphaImage).into(holder.alphaImage);
        holder.ttsbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                holder.textToSpeech.speak(arrayList.get(position).alphaWord, TextToSpeech.QUEUE_FLUSH, null,null);
            }
        });
    }
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView alphaChar,alphaWord;
        ImageView alphaImage;
        TextToSpeech textToSpeech;
        CardView ttsbutton;
        public ViewHolder(View itemView){
            super(itemView);
            alphaChar=itemView.findViewById(R.id.alpha_char);
            alphaWord=itemView.findViewById(R.id.alpha_word);
            alphaImage=itemView.findViewById(R.id.alpha_img);
            ttsbutton=itemView.findViewById(R.id.ttsbutton);

            textToSpeech=new TextToSpeech(context, status -> {
                textToSpeech.setLanguage(Locale.US);
                textToSpeech.setSpeechRate(0.9f);
                textToSpeech.setPitch(1.1f);
            });
        }
    }
}
