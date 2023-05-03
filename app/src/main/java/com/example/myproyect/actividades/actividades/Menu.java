package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.InterfaceMenu;
import com.example.myproyect.actividades.fragmentos.Losa1Fragment;
import com.example.myproyect.actividades.fragmentos.Losa2Fragment;
import com.example.myproyect.actividades.fragmentos.Losa3Fragment;
import com.example.myproyect.actividades.fragmentos.Losa4Fragment;

public class Menu extends AppCompatActivity implements InterfaceMenu {
    Fragment[] fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fragments = new Fragment[4];
        fragments[0] = new Losa1Fragment();
        fragments[1] = new Losa2Fragment();
        fragments[2] = new Losa3Fragment();
        fragments[3] = new Losa4Fragment();
        int idBoton = getIntent().getIntExtra("idBoton", -1);
        onClickMenu(idBoton);
    }

    @Override
    public void onClickMenu(int idBoton) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.menRelMenu, fragments[idBoton]);
        ft.commit();
    }
}