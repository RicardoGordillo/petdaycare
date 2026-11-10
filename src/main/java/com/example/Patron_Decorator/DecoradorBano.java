package com.example.Patron_Decorator;

import com.example.IServicio;

public class DecoradorBano extends ServicioDecorator {
    private final double costoExtra;

    public DecoradorBano(IServicio servicioEnvuelto, double costoExtra) {
        super(servicioEnvuelto);
        this.costoExtra = costoExtra;
    }

    @Override
    public double calcularPrecio() {
        return super.calcularPrecio() + costoExtra;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " + Baño";
    }
}