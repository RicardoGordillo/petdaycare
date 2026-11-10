package com.example;

import java.time.LocalDate;

public class AlertaSalud {

    private int id;
    private String tipo;
    private String descripcion;
    private LocalDate fecha;
    private boolean atendida;

    public void registrar() {
        System.out.println("Registrando...");
    }

    public void marcarAtendida() {
        System.out.println("Atendiendo...");
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
