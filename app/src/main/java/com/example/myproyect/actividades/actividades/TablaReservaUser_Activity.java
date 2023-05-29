package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.Fecha;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TablaReservaUser_Activity extends AppCompatActivity {
    TableLayout tb1,tb2;
    CheckBox chkL1,chkL2,chkL3;
    CheckBox chkM1,chkM2,chkM3;
    CheckBox chkMi1,chkMi2,chkMi3;
    CheckBox chkJ1,chkJ2,chkJ3;
    CheckBox chkV1,chkV2,chkV3;
    CheckBox chkS1, chkS2,chkS3;
    TextView lblSemana;
    TextView txtv_cl1,txtv_cl2,txtv_cl3,txtv_cl4,txtv_cl5,txtv_cl6;
    int numDia1, numDia6;

    Button btnReservar;
    List<CheckBox> listaChk = new ArrayList<>();
    List<TextView> listaTxtv = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        asginarReferencias();
        agregarListaChk();
        agregarListaTxtv();
        updateTxtv();
        clickChk();

        validarChk();

        lblSemana.setText(Fecha.lblTablaReserva);
    }
    private void agregarListaTxtv(){
        listaTxtv.add(txtv_cl1);
        listaTxtv.add(txtv_cl2);
        listaTxtv.add(txtv_cl3);
        listaTxtv.add(txtv_cl4);
        listaTxtv.add(txtv_cl5);
        listaTxtv.add(txtv_cl6);
    }

    private void updateTxtv(){
        List<String> lista = Fecha.obtenerDiasSemanaProximos();

        for(int i=0; i<listaTxtv.size(); i++){
            listaTxtv.get(i).setText(lista.get(i));
        }
    }

    private void clickChk(){
        View.OnClickListener checkBoxListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Manejar el evento de clic del CheckBox
                CheckBox checkBox = (CheckBox) view;
                int selectedCheckBoxId = checkBox.getId();
                // Obtener el ID del CheckBox seleccionado

                for(int i=0 ; i<listaChk.size(); i++){
                    if(selectedCheckBoxId == listaChk.get(i).getId()){
                        if(listaChk.get(i).isChecked()){
                            listaChk.get(i).setText("ELEGIDO");
                            int color = ContextCompat.getColor(TablaReservaUser_Activity.this, R.color.purple_500);
                            checkBox.setTextColor(color); // Establecer el color del texto utilizando un recurso de color
                        }else{
                            listaChk.get(i).setText("LIBRE");
                            int color = ContextCompat.getColor(TablaReservaUser_Activity.this, R.color.black);
                            checkBox.setTextColor(color); // Establecer el color del texto utilizando un recurso de color
                        }
                    }
                }
            }
        };
        for(int i=0 ; i<listaChk.size(); i++){
            listaChk.get(i).setOnClickListener(checkBoxListener);
        }

    }
    private void reservar(){
        //PROCESO DE RESERVA EN BD
    }
    private void asginarReferencias(){
        tb1 = findViewById(R.id.tabla1_reserva);
        tb2 = findViewById(R.id.tabla2_reserva);

        txtv_cl1 = findViewById(R.id.txtv_cl1_TRU);
        txtv_cl2 = findViewById(R.id.txtv_cl2_TRU);
        txtv_cl3 = findViewById(R.id.txtv_cl3_TRU);
        txtv_cl4 = findViewById(R.id.txtv_cl4_TRU);
        txtv_cl5 = findViewById(R.id.txtv_cl5_TRU);
        txtv_cl6 = findViewById(R.id.txtv_cl6_TRU);

        lblSemana = findViewById(R.id.lblSemana_TablaReserva);
        btnReservar = findViewById(R.id.btnReservarTablaUser);
        btnReservar.setOnClickListener(view -> {
            reservar();
        });

        chkL1 = findViewById(R.id.chkLunes_3pm_TRU);
        chkL2 = findViewById(R.id.chkLunes_5pm_TRU);
        chkL3 = findViewById(R.id.chkLunes_7pm_TRU);

        chkM1 = findViewById(R.id.chkMartes_3pm_TRU);
        chkM2 = findViewById(R.id.chkMartes_5pm_TRU);
        chkM3 = findViewById(R.id.chkMartes_7pm_TRU);

        chkMi1 = findViewById(R.id.chKMiercoles_3pm_TRU);
        chkMi2 = findViewById(R.id.chKMiercoles_5pm_TRU);
        chkMi3 = findViewById(R.id.chkMiercoles_7pm_TRU);

        chkJ1 = findViewById(R.id.chkJueves_3pm_TRU);
        chkJ2 = findViewById(R.id.chkJueves_5pm_TRU);
        chkJ3 = findViewById(R.id.chkJueves_7pm_TRU);

        chkV1 = findViewById(R.id.chkViernes_3pm_TRU);
        chkV2 = findViewById(R.id.chkViernes_5pm_TRU);
        chkV3 = findViewById(R.id.chkViernes_7pm_TRU);

        chkS1 = findViewById(R.id.chkSabado_3pm_TRU);
        chkS2 = findViewById(R.id.chkSabado_5pm_TRU);
        chkS3 = findViewById(R.id.chkSabado_7pm_TRU);

    }
    private void agregarListaChk(){
        listaChk.add(chkL1);
        listaChk.add(chkL2);
        listaChk.add(chkL3);

        listaChk.add(chkM1);
        listaChk.add(chkM2);
        listaChk.add(chkM3);

        listaChk.add(chkMi1);
        listaChk.add(chkMi2);
        listaChk.add(chkMi3);

        listaChk.add(chkJ1);
        listaChk.add(chkJ2);
        listaChk.add(chkJ3);

        listaChk.add(chkV1);
        listaChk.add(chkV2);
        listaChk.add(chkV3);

        listaChk.add(chkS1);
        listaChk.add(chkS2);
        listaChk.add(chkS3);

    }
    private void validarChk(){

        for(int i=0 ; i<listaChk.size(); i++){
            if(listaChk.get(i).isChecked())
            listaChk.get(i).setClickable(false);
        }

    }
}