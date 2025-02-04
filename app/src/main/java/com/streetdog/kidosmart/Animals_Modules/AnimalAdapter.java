package com.streetdog.kidosmart.Animals_Modules;
import android.content.Context;
import android.media.*;
import android.speech.tts.TextToSpeech;
import android.view.*;
import android.widget.*;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.streetdog.kidosmart.R;
import java.util.*;
public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    Context context;
    ArrayList<Animal_Model> arrayList;
    AnimalAdapter(Context context, ArrayList<Animal_Model> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.animal_tile, parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(arrayList.get(position).image).into(holder.image);
        holder.name.setText(arrayList.get(position).name);
        holder.ttsbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                holder.textToSpeech.speak(arrayList.get(position).name,TextToSpeech.QUEUE_FLUSH,null,null);
            }
        });
        holder.audio.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                holder.loading.setVisibility(View.VISIBLE);
                holder.audioIcon.setVisibility(View.GONE);
                try {
                    if(holder.mediaPlayer.isPlaying())
                        holder.mediaPlayer.stop();
                    holder.mediaPlayer.reset();
                    holder.mediaPlayer.setDataSource(arrayList.get(position).audio);
                    holder.mediaPlayer.prepareAsync();
                    holder.mediaPlayer.setOnPreparedListener(mp -> {
                        holder.audioIcon.setVisibility(View.VISIBLE);
                        holder.loading.setVisibility(View.GONE);
                        holder.mediaPlayer.start();
                    });
                }catch(Exception e){} finally {

                }
            }
        });
    }
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView ttsbutton,audio;
        TextView name;
        ImageView image,audioIcon;
        TextToSpeech textToSpeech;
        MediaPlayer mediaPlayer;
        ProgressBar loading;
        public ViewHolder(View itemView){
            super(itemView);

            ttsbutton=itemView.findViewById(R.id.ttsbutton);
            audio=itemView.findViewById(R.id.audio);
            name=itemView.findViewById(R.id.name);
            image=itemView.findViewById(R.id.image);
            loading=itemView.findViewById(R.id.loading);
            audioIcon=itemView.findViewById(R.id.audioIcon);
            mediaPlayer=new MediaPlayer();

            textToSpeech=new TextToSpeech(context, status -> {
                textToSpeech.setLanguage(Locale.US);
                textToSpeech.setSpeechRate(0.9f);
                textToSpeech.setPitch(1.1f);
            });
        }
    }
}
