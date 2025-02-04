package com.streetdog.kidosmart;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.Toolbar;

import com.streetdog.kidosmart.Alphabtes_Modules.Alphabet_Home;
import com.streetdog.kidosmart.Animals_Modules.Animal_Home;
import com.streetdog.kidosmart.Numbers_Modules.Numbers_Home;

import com.streetdog.kidosmart.Colors_Modules.Color_Home;
import com.streetdog.kidosmart.Shapes_Modules.Shapes_Home;

public class MainActivity extends AppCompatActivity {

    ImageView alphabet,number,color,animal,shape,quiz;
    Toolbar appbar;
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        alphabet=findViewById(R.id.alphabet);
        number=findViewById(R.id.number);
        color=findViewById(R.id.color);
        animal=findViewById(R.id.animal);
        shape=findViewById(R.id.shape);
        quiz=findViewById(R.id.quiz);

        alphabet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, Alphabet_Home.class);
                startActivity(intent);
            }
        });
        number.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, Numbers_Home.class);
                startActivity(intent);
            }
        });
        color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, Color_Home.class);
                startActivity(intent);
            }
        });
        animal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, Animal_Home.class);
                startActivity(intent);
            }
        });
        shape.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, Shapes_Home.class);
                startActivity(intent);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Currently not available", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
