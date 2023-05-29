package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import com.example.myproyect.R;


public class Prueba_Activity extends AppCompatActivity {

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

    btn = findViewById(R.id.btn_prueba);
    btn.setOnClickListener(view -> {
            new Task().execute();

    });
    }

    class  Task extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}