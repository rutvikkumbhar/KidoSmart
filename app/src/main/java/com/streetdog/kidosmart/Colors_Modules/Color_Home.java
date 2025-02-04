package com.streetdog.kidosmart.Colors_Modules;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.firestore.*;
import com.streetdog.kidosmart.R;
import java.util.ArrayList;

public class Color_Home extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Color_Model> arrayList=new ArrayList<>();

    ProgressBar loading;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_home);

        loading=findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
        recyclerView=findViewById(R.id.color_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ColorAdapter adapter=new ColorAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);

        CollectionReference ref= FirebaseFirestore.getInstance().collection("Colors");
        ref.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                loading.setVisibility(View.GONE);
                for(QueryDocumentSnapshot document: task.getResult()){
                    String colorName=document.getString("colorName");
                    String colorCode=document.getString("colorCode");
                    arrayList.add(new Color_Model(colorCode, colorName));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}