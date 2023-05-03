package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

import com.example.myproyect.R;

public class MainActivity extends AppCompatActivity {
    CheckBox chk1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myproyect.R.layout.activity_main);

        asignarReferencias();

    }
    private void asignarReferencias(){
        chk1 = findViewById(R.id.checkBox);
        hola();
    }

    private void hola(){

        chk1.isChecked();
    }
}