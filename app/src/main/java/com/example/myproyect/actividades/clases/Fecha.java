package com.example.myproyect.actividades.clases;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Fecha {
    public static String diaSemana(){
        Calendar calendar = Calendar.getInstance();
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        SimpleDateFormat formatoDiaSemana = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
        String diaSemanaTexto = formatoDiaSemana.format(calendar.getTime());

        return diaSemanaTexto;
    }
    public static int obtenerDiaDelMes() {
        Calendar calendar = Calendar.getInstance();
        int diaMes = calendar.get(Calendar.DAY_OF_MONTH);
        return diaMes;
    }
    public static int obtenerNumeroSemanaLunes() {
        Calendar calendar = Calendar.getInstance();
        int diaSemanaActual = calendar.get(Calendar.DAY_OF_WEEK);
        if (diaSemanaActual == Calendar.MONDAY) {
            return calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return calendar.get(Calendar.DAY_OF_MONTH);
        }
    }
    public static  int obtenerDiasHastaProximoSabado() {
        Calendar calendar = Calendar.getInstance();
        int diaSemanaActual = calendar.get(Calendar.DAY_OF_WEEK);

        int diasHastaProximoSabado = (Calendar.SATURDAY - diaSemanaActual + 7) % 7;
        return diasHastaProximoSabado;
    }
    public static int obtenerNumeroDiaProximosDias(int numeroDias) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, obtenerNumeroDiaAnioActual()+numeroDias);
        int numeroDia = calendar.get(Calendar.DAY_OF_WEEK);
        return numeroDia;
    }
    public static int obtenerNumeroDiaAnioActual() {
        Calendar calendar = Calendar.getInstance();
        int numeroDiaAnio = calendar.get(Calendar.DAY_OF_YEAR);
        return numeroDiaAnio;
    }
    public static String obtenerNombreMesActual() {
        Calendar calendar = Calendar.getInstance();
        int mesActual = calendar.get(Calendar.MONTH);

        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es", "ES"));
        String[] nombresMeses = dfs.getMonths();
        String nombreMes = nombresMeses[mesActual];

        return nombreMes;
    }
    public static  int obtenerAnioActual() {
        Calendar calendar = Calendar.getInstance();
        int anioActual = calendar.get(Calendar.YEAR);
        return anioActual;
    }

}
