package com.streetdog.kidosmart.Alphabtes_Modules;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.firestore.*;
import com.streetdog.kidosmart.R;
import java.util.ArrayList;

public class Alphabet_Home extends AppCompatActivity {
    ArrayList<Alphabet_Model> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    FirebaseFirestore firestore;
    ProgressBar loading;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_home);

        recyclerView=findViewById(R.id.alphabet_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.loading=findViewById(R.id.loading);

        AlphabetAdapter adapter=new AlphabetAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        firestore=FirebaseFirestore.getInstance();
        CollectionReference ref=firestore.collection("Alphabets");
        loading.setVisibility(View.VISIBLE);
        ref.orderBy("char", Query.Direction.ASCENDING).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                loading.setVisibility(View.GONE);
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String alphaChar = document.getString("char");
                    String alphaWord = document.getString("word");
                    String imageUrl = document.getString("image");
                    arrayList.add(new Alphabet_Model(imageUrl,alphaChar, alphaWord));
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(Alphabet_Home.this, "Failed to load data", Toast.LENGTH_LONG);
        });
    }
}