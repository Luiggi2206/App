package com.example.myproyect.actividades.clases;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Fecha {
    public static  String dia1,dia6;
    public static String lblTablaReserva;

    public static  List<String> obtenerDiasSemanaProximos() {
        List<String> listaDiasSemana = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Avanzar al día de mañana
        int anioActual = calendar.get(Calendar.YEAR);
        int mesActual = calendar.get(Calendar.MONTH);

        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es", "ES"));

        String[] nombresMesesAbreviados = dfs.getShortMonths();
        String nombreMesAbreviado = nombresMesesAbreviados[mesActual];
        String[] nombresDiasSemana = dfs.getShortWeekdays();

        for (int i = 0; i < 6; i++) {

            int numeroDiaSemana = calendar.get(Calendar.DAY_OF_WEEK);
            String diaSemana = nombresDiasSemana[numeroDiaSemana];
            String numeroDia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

            String cadenaDiaSemana = diaSemana + " " + numeroDia;
            listaDiasSemana.add(cadenaDiaSemana);
            if(i==0){
                dia1 = numeroDia;
            }else if(i==5){
                dia6= numeroDia;
                lblTablaReserva = "SEMANA "+dia1+" - "+dia6+" -> "+nombreMesAbreviado.toUpperCase()+" "+String.valueOf(anioActual);
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Avanzar al siguiente día
        }

        return listaDiasSemana;
    }


}
