package com.streetdog.kidosmart.Numbers_Modules;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.streetdog.kidosmart.R;
import java.util.ArrayList;

public class Numbers_Home extends AppCompatActivity {

    ProgressBar loading;
    RecyclerView recyclerView;
    ArrayList<Number_Model> arrayList=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_home);

        loading=findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
        recyclerView=findViewById(R.id.number_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NumberAdapter adapter=new NumberAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        CollectionReference ref= FirebaseFirestore.getInstance().collection("Numbers");
        ref.orderBy("id", Query.Direction.ASCENDING).get().addOnCompleteListener(task ->{
            if(task.isSuccessful()){
                loading.setVisibility(View.GONE);
                for(QueryDocumentSnapshot document: task.getResult()){
                    String name=document.getString("name");
                    String word=document.getString("word");
                    String image1=document.getString("image1");
                    String image2=document.getString("image2");

                    arrayList.add(new Number_Model(image1, image2, word, name));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}