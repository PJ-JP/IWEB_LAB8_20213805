package com.example.iweb_lab8.beans;

public class Actor {
    private int idActor;
    private String nombre;
    private String apellido;
    private String anoNacimiento;
    private int premioOscar;

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
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

    public String getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(String anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public int getPremioOscar() {
        return premioOscar;
    }

    public void setPremioOscar(int premioOscar) {
        this.premioOscar = premioOscar;
    }
}
