package com.example.myproyect.actividades.entidades;

public class CanchaDeportiva {
    private String direccion;
    private String nombre;
    private String horarios;
    private boolean mantenimiento;
    private double precio;

    public CanchaDeportiva(String direccion, String nombre, String horarios, boolean mantenimiento, double precio) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.horarios = horarios;
        this.mantenimiento = mantenimiento;
        this.precio = precio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public boolean isMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
