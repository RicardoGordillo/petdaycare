package com.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Entrenamiento implements IServicio {

    private double precioBase;
    private String nombre = "Entrenamiento";

    private Map<LocalDate, EstadoDisponibilidad> historialEstados = new HashMap<>();

    public Entrenamiento() {
    }

    public Entrenamiento(double precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public void cambiarEstadoPorFecha(LocalDate fecha, EstadoDisponibilidad nuevoEstado) {
        if (fecha != null && nuevoEstado != null) {
            this.historialEstados.put(fecha, nuevoEstado);
        }
    }

    @Override
    public boolean verificarDisponibilidad(LocalDate fecha) {
        if (fecha == null) {
            return false;
        }
        EstadoDisponibilidad estado =historialEstados.getOrDefault(fecha, EstadoDisponibilidad.DISPONIBLE);

        return estado == EstadoDisponibilidad.DISPONIBLE;
    }

    @Override
    public double calcularPrecio() {
        return precioBase;
    }

    @Override
    public String obtenerDescripcion() {
        return "Entrenamiento para mascota";
    }

    public String getNombre() {
        return nombre;
    }

}
