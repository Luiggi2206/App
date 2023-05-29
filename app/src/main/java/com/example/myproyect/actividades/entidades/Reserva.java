package com.example.myproyect.actividades.entidades;
//HORARIO1: 3PM - 4PM
//HORARIO2: 5PM - 6PM
//HORARIO3: 7PM - 8PM
public class Reserva {

    private int dia;
    private boolean[] arrayB = new boolean[3]; // true= ocupado - false = libre;
    private String[] arrayDni = new String[3];

    public Reserva(int dia, boolean[] arrayB, String[] arrayDni) {
        this.dia = dia;
        this.arrayB = arrayB;
        this.arrayDni = arrayDni;
    }

    public Reserva(int dia, boolean[] arrayB) {
        this.dia = dia;
        this.arrayB = arrayB;
    }

    public Reserva(){

    }

    public String[] getArrayDni() {
        return arrayDni;
    }

    public void setArrayDni(String[] arrayDni) {
        this.arrayDni = arrayDni;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public boolean[] getArrayB() {
        return arrayB;
    }

    public void setArrayB(boolean[] arrayB) {
        this.arrayB = arrayB;
    }
}
