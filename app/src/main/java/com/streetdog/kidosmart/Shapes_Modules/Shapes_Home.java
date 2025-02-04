package com.streetdog.kidosmart.Shapes_Modules;
import android.os.Bundle;
import android.view.*;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.firestore.*;
import com.streetdog.kidosmart.R;
import java.util.ArrayList;

public class Shapes_Home extends AppCompatActivity {

    ArrayList<Shape_Model> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    ProgressBar progressBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_home);

        progressBar=findViewById(R.id.loading);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView=findViewById(R.id.shape_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ShapeAdapter adapter=new ShapeAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);

        CollectionReference ref= FirebaseFirestore.getInstance().collection("Shapes");
        ref.orderBy("id", Query.Direction.ASCENDING).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                progressBar.setVisibility(View.GONE);
                for (QueryDocumentSnapshot document: task.getResult()){
                    String name= document.getString("name");
                    String description= document.getString("des");
                    String image=document.getString("image");

                    arrayList.add(new Shape_Model(name,description,image));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}