package com.example;

public class Incidente {

    private int id;
    private String descripcion;
    private EstadoIncidente estado;

    public void resolver() {
        this.estado = EstadoIncidente.RESUELTO;
    }

    public void escalar() {
        this.estado = EstadoIncidente.ESCALADO;
    }

    public void reportar() {
        System.out.println("Reporte de incidente en proceso...");
    }

    public EstadoIncidente getEstado() {
        return estado;
    }
}
