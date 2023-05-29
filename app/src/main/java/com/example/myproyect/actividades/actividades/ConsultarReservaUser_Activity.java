package com.example.myproyect.actividades.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproyect.R;
import com.example.myproyect.actividades.clases.Fecha;
import com.example.myproyect.actividades.entidades.Reserva;
import com.example.myproyect.actividades.modelos.DAO_Reserva;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ConsultarReservaUser_Activity extends AppCompatActivity {
    Button btnVolver;
    TextView txtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reserva_user);

        asignarReferencias();
        mostrar();

    }
    private void asignarReferencias(){
        btnVolver = findViewById(R.id.btnVolver_ConsultRsvUser);
        btnVolver.setOnClickListener(view -> {
            super.onBackPressed();
        });
        txtv = findViewById(R.id.txtv_consultasRsv_user);

    }
    private void mostrar(){
        txtv.setLines(10);
        txtv.setEllipsize(TextUtils.TruncateAt.END);
        txtv.setMovementMethod(new ScrollingMovementMethod());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        List<Reserva> listaRsv = DAO_Reserva.ConsultarRsv();
        if(listaRsv.size() == 0 ){
            txtv.setText("NO TIENE RESERVAS");
        }else{
            txtv.setText("");

            for(int i=0 ; i<listaRsv.size(); i++){
                List<String> listaDias = Fecha.obtenerDiasSemanaProximos();
                List<String> listaHoras = new ArrayList<>();
                String dni = Login_Activity.getUsuario().getDNI();
                String hora =  null;

                for(int j=0 ; j<3; j++){
                    if(listaRsv.get(i).getArrayDni()[j].equals(dni)){
                        switch (j){
                            case 0:
                                hora = "3pm";
                                listaHoras.add(hora);
                                break;
                            case 1:
                                hora = "5pm";
                                listaHoras.add(hora);
                                break;
                            case 2:
                                hora = "7pm";
                                listaHoras.add(hora);
                                break;
                        }

                    }
                }
                int dia = listaRsv.get(i).getDia();
                txtv.setText(
                        txtv.getText()+"\n"+
                        "DIA: "+ dia+" -> "+listaDias.get(dia-1)+"\n"+
                        "HORA: "+listaHoras.get(i)+"\n"
                        );
            }
            Toast.makeText(this, "Tiene "+listaRsv.size()+" reservas", Toast.LENGTH_SHORT).show();

        }

    }
}