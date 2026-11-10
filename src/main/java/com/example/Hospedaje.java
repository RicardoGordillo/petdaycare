package com.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Hospedaje implements IServicio {

    private double precioBase;
    private String nombre = "Hospedaje";

    private Map<LocalDate, EstadoDisponibilidad> historialEstados = new HashMap<>();

    public Hospedaje() {
    }

    public Hospedaje(double precioBase) {
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
        EstadoDisponibilidad estado = this.historialEstados.getOrDefault(fecha, EstadoDisponibilidad.DISPONIBLE);

        if (estado == EstadoDisponibilidad.DISPONIBLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double calcularPrecio() {
        return precioBase;
    }

    @Override
    public String obtenerDescripcion() {
        return "Hospedaje para mascotas";
    }

    @Override
    public String getNombre() {
        return nombre;
    }

}
