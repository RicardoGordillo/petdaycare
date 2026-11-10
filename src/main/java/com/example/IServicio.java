package com.example;

import java.time.LocalDate;


public interface IServicio {

    public void cambiarEstadoPorFecha(LocalDate fecha, EstadoDisponibilidad nuevoEstado);

    public double calcularPrecio();

    public boolean verificarDisponibilidad(LocalDate fecha);

    public String obtenerDescripcion();

    public String getNombre();
}
