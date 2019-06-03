package com.example.mysudokito.entidades;

public class Usuario {

    private int id;
    private String nombre;
    private int puntuacionSegundos;
    private int puntuacionMinutos;
    private String fecha;

    public Usuario(int id, String nombre, int puntuacionSegundos, int puntuacionMinutos, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.puntuacionSegundos = puntuacionSegundos;
        this.puntuacionMinutos = puntuacionMinutos;
        this.fecha = fecha;
    }

    public Usuario() {
    }

    public int getPuntuacionMinutos() {
        return puntuacionMinutos;
    }

    public void setPuntuacionMinutos(int puntuacionMinutos) {
        this.puntuacionMinutos = puntuacionMinutos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacionSegundos() {
        return puntuacionSegundos;
    }

    public void setPuntuacionSegundos(int puntuacionSegundos) {
        this.puntuacionSegundos = puntuacionSegundos;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
