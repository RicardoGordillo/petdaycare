package com.example.Patron_Decorator;

import com.example.IServicio;

public class DecoradorMasaje extends ServicioDecorator {
    private final double costoExtra;

    public DecoradorMasaje(IServicio servicioEnvuelto, double costoExtra) {
        super(servicioEnvuelto);
        this.costoExtra = costoExtra;
    }

    @Override
    public double calcularPrecio() {
        return super.calcularPrecio() + costoExtra;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " + Masaje";
    }
}