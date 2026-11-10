package com.example.Patron_Decorator;

import java.time.LocalDate;

import com.example.EstadoDisponibilidad;
import com.example.IServicio;

public abstract class ServicioDecorator implements IServicio {

    protected final IServicio servicioEnvuelto;

    public ServicioDecorator(IServicio servicioEnvuelto) {
        this.servicioEnvuelto = servicioEnvuelto;
    }

    @Override
    public void cambiarEstadoPorFecha(LocalDate fecha, EstadoDisponibilidad nuevoEstado) {
        servicioEnvuelto.cambiarEstadoPorFecha(fecha, nuevoEstado);
    }

    @Override
    public double calcularPrecio() {
        return servicioEnvuelto.calcularPrecio();
    }

    @Override
    public boolean verificarDisponibilidad(LocalDate fecha) {
        return servicioEnvuelto.verificarDisponibilidad(fecha);
    }

    @Override
    public String obtenerDescripcion() {
        return servicioEnvuelto.obtenerDescripcion();
    }

    @Override
    public String getNombre() {
        return servicioEnvuelto.getNombre();
    }
}