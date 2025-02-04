package com.streetdog.kidosmart;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Flash_Screen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        Thread thread=new Thread(
                () -> {
                    try{
                        Thread.sleep(3000);
                    } catch (Exception e){}
                    finally {
                        Intent intent=new Intent(Flash_Screen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
        thread.start();
    }
}