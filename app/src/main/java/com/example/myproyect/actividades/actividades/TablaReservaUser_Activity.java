package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.Fecha;
import com.example.myproyect.actividades.entidades.Reserva;
import com.example.myproyect.actividades.entidades.Usuario;
import com.example.myproyect.actividades.modelos.DAO_Reserva;

import java.util.ArrayList;
import java.util.List;


public class TablaReservaUser_Activity extends AppCompatActivity {
    TableLayout tb1,tb2;
    CheckBox chkL1,chkL2,chkL3;
    CheckBox chkM1,chkM2,chkM3;
    CheckBox chkMi1,chkMi2,chkMi3;
    CheckBox chkJ1,chkJ2,chkJ3;
    CheckBox chkV1,chkV2,chkV3;
    CheckBox chkS1, chkS2,chkS3;
    TextView lblSemana, lblCantidadPagar;
    TextView txtv_cl1,txtv_cl2,txtv_cl3,txtv_cl4,txtv_cl5,txtv_cl6;
    int numDia1, numDia6;
    Double cantidadPagar=0.0;
    int cantidadReservas=0;
    Usuario usuario = Login_Activity.getUsuario();

    Button btnReservar,btnVolver;
    List<CheckBox> listaChk = new ArrayList<>();
    List<Integer> listaChkS = new ArrayList<>();
    List<TextView> listaTxtv = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        asginarReferencias();
        agregarListaChk();
        agregarListaTxtv();
        updateTxtv();
        updateChk(); //consultar a la BD
        clickChk(); //actualizar visualización

        lblSemana.setText(Fecha.lblTablaReserva);
    }
    private void updateChk(){
        //consultar BD
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ArrayList<Reserva> lista = new ArrayList<>();
        lista = DAO_Reserva.listarReserva();
        if(lista.size() == 0 ){
            Toast.makeText(this, "Lista vacía: "+lista.size(), Toast.LENGTH_SHORT).show();
        }else{
            int index = 0,cantidadDias= 6,cantidadHoras=3;

            for (int i = 0; i < cantidadDias; i++) {
                for (int j = 0; j < cantidadHoras; j++) {
                    if(lista.get(i).getArrayB()[j]){
                        //true
                        listaChk.get(index).setChecked(true);
                        listaChk.get(index).setText("OCUPADO");
                        listaChk.get(index).setEnabled(false);
                    }else{
                        listaChk.get(index).setChecked(false);
                        listaChk.get(index).setText("Libre");
                        listaChk.get(index).setEnabled(true);
                    }
                    index++;
                }
            }

        }


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
                            cantidadPagar += 50;
                            listaChkS.add(i);
                            lblCantidadPagar.setText("Pagar: S/"+cantidadPagar);
                        }else{
                            listaChk.get(i).setText("LIBRE");
                            int color = ContextCompat.getColor(TablaReservaUser_Activity.this, R.color.black);
                            checkBox.setTextColor(color); // Establecer el color del texto utilizando un recurso de color
                            cantidadPagar -= 50;
                            if(listaChkS.size()!=0){//buscar y borrar de la lista
                                for(int j=0; j<listaChkS.size(); j++){
                                    if(listaChkS.get(j) == i){
                                     listaChkS.remove(j);
                                    }
                                }
                            }
                            lblCantidadPagar.setText("Pagar: S/"+cantidadPagar);
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
        String msg = null;
        cantidadReservas = listaChkS.size();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String dni = usuario.getDNI();
        for(int i=0 ; i<listaChkS.size(); i++){
            int numOrden = listaChkS.get(i);
            int dia = 0;
            if(numOrden<=2){//dia 1 - columna 1
                dia =1;
                switch (numOrden){
                    case 0:
                        msg = DAO_Reserva.insertarRSV(true,dia,3);
                        break;
                    case 1:
                        msg = DAO_Reserva.insertarRSV(true,dia,5);
                        break;
                    case 2:
                        msg = DAO_Reserva.insertarRSV(true,dia,7);
                        break;
                }
            }else if(numOrden<=5){
                dia =2;
                switch (numOrden){
                    case 3:
                        msg = DAO_Reserva.insertarRSV(true,dia,3);
                        break;
                    case 4:
                        msg = DAO_Reserva.insertarRSV(true,dia,5);
                        break;
                    case 5:
                        msg = DAO_Reserva.insertarRSV(true,dia,7);
                        break;
                }
            }else if(numOrden<=8){
                dia =3;
                switch (numOrden){
                    case 6:
                        msg = DAO_Reserva.insertarRSV(true,dia,3);
                        break;
                    case 7:
                        msg = DAO_Reserva.insertarRSV(true,dia,5);
                        break;
                    case 8:
                        msg = DAO_Reserva.insertarRSV(true,dia,7);
                        break;
                }
            }else if(numOrden<=11) {
                dia = 4;
                switch (numOrden) {
                    case 9:
                        msg = DAO_Reserva.insertarRSV(true, dia, 3);
                        break;
                    case 10:
                        msg = DAO_Reserva.insertarRSV(true, dia, 5);
                        break;
                    case 11:
                        msg = DAO_Reserva.insertarRSV(true, dia, 7);
                        break;
                }
            }else if(numOrden<=14){
                dia =5;
                switch (numOrden){
                    case 12:
                        msg = DAO_Reserva.insertarRSV(true,dia,3);
                        break;
                    case 13:
                        msg = DAO_Reserva.insertarRSV(true,dia,5);
                        break;
                    case 14:
                        msg = DAO_Reserva.insertarRSV(true,dia,7);
                        break;
                }
            }else{
                dia =6;
                switch (numOrden){
                    case 15:
                        msg = DAO_Reserva.insertarRSV(true,dia,3);
                        break;
                    case 16:
                        msg = DAO_Reserva.insertarRSV(true,dia,5);
                        break;
                    case 17:
                        msg = DAO_Reserva.insertarRSV(true,dia,7);
                        break;
                }
            }
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        updateChk();

    }
    private void asginarReferencias(){

        txtv_cl1 = findViewById(R.id.txtv_cl1_TRU);
        txtv_cl2 = findViewById(R.id.txtv_cl2_TRU);
        txtv_cl3 = findViewById(R.id.txtv_cl3_TRU);
        txtv_cl4 = findViewById(R.id.txtv_cl4_TRU);
        txtv_cl5 = findViewById(R.id.txtv_cl5_TRU);
        txtv_cl6 = findViewById(R.id.txtv_cl6_TRU);

        lblSemana = findViewById(R.id.lblSemana_TablaReserva);
        lblCantidadPagar = findViewById(R.id.lblCantidadPagar_TRU);

        btnVolver = findViewById(R.id.btnRegresar_TRU);
        btnVolver.setOnClickListener(view -> {
            super.onBackPressed();

        });
        btnReservar = findViewById(R.id.btnReservarTablaUser);
        btnReservar.setOnClickListener(view -> {
            reservar();
        });

        chkL1 = findViewById(R.id.chkLunes_3pm_TRU);//0
        chkL2 = findViewById(R.id.chkLunes_5pm_TRU);//1
        chkL3 = findViewById(R.id.chkLunes_7pm_TRU);//2

        chkM1 = findViewById(R.id.chkMartes_3pm_TRU);//3
        chkM2 = findViewById(R.id.chkMartes_5pm_TRU);//4
        chkM3 = findViewById(R.id.chkMartes_7pm_TRU);//5

        chkMi1 = findViewById(R.id.chKMiercoles_3pm_TRU);//6
        chkMi2 = findViewById(R.id.chKMiercoles_5pm_TRU);//7
        chkMi3 = findViewById(R.id.chkMiercoles_7pm_TRU);//8

        chkJ1 = findViewById(R.id.chkJueves_3pm_TRU);//9
        chkJ2 = findViewById(R.id.chkJueves_5pm_TRU);//10
        chkJ3 = findViewById(R.id.chkJueves_7pm_TRU);//11

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
}