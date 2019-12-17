package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        Integer i = intent.getIntExtra("key", 1);
        double a = Math.PI;
        //finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Toast.makeText(this, "HELLLLLLLL", Toast.LENGTH_SHORT).show();
    }
}
