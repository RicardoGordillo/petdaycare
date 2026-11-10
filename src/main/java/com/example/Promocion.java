package com.example;

import java.time.LocalDate;

public class Promocion {

    private int id;
    private String nombre;
    private double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Promocion(int id, String nombre, double descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public double aplicarPromocion(double precio) {
        return precio * descuento;
    }
}
