package com.streetdog.kidosmart.Animals_Modules;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.firestore.*;
import com.streetdog.kidosmart.R;
import java.util.ArrayList;

public class Animal_Home extends AppCompatActivity {

    ProgressBar loading;
    RecyclerView recyclerView;
    ArrayList<Animal_Model> arrayList=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_home);

        loading=findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
        recyclerView=findViewById(R.id.animal_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AnimalAdapter adapter=new AnimalAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        CollectionReference ref= FirebaseFirestore.getInstance().collection("Animals");
        ref.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                loading.setVisibility(View.GONE);
                for(QueryDocumentSnapshot document: task.getResult()){
                    String name=document.getString("name");
                    String image=document.getString("image");
                    String audio=document.getString("audio");

                    arrayList.add(new Animal_Model(name,audio,image));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}