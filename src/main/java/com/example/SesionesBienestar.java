package com.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;



public class SesionesBienestar implements IServicio {

    private double precioBase;
    private String nombre = "Sesiones de Bienestar";

    private Map<LocalDate, EstadoDisponibilidad> historialEstados = new HashMap<>();

    public SesionesBienestar(double precioBase) {
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
        if (fecha == null) return false;

        EstadoDisponibilidad estado = this.historialEstados.get(fecha);

        return estado == null || estado == EstadoDisponibilidad.DISPONIBLE;
    }

    @Override
    public double calcularPrecio() {
        return precioBase;
    }

    @Override
    public String obtenerDescripcion() {
        return "Sesiones de bienestar para mascota";
    }

    @Override
    public String getNombre() {
        return nombre;
    }

}
