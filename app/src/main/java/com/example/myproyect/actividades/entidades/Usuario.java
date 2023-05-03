package com.example.myproyect.actividades.entidades;

public class Usuario {
    private String DNI;
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private String fecha_naci;
    private String sexo;
    private String celular;


    public String getCelular() {
        return celular;
    }

    public Usuario(String DNI, String nombre, String apellido, String correo, String clave, String fecha_naci, String sexo, String celular) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.correo = correo;
        this.fecha_naci = fecha_naci;
        this.sexo = sexo;
        this.celular = celular;

    }
    public Usuario(String correo, String clave){
        this.correo = correo;
        this.clave = clave;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getFecha_naci() {
        return fecha_naci;
    }

    public void setFecha_naci(String fecha_naci) {
        this.fecha_naci = fecha_naci;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
