package com.example.Patron_Decorator;

import com.example.IServicio;

public class DecoradorPeluqueria extends ServicioDecorator {
    private final double costoExtra;

    public DecoradorPeluqueria(IServicio servicioEnvuelto, double costoExtra) {
        super(servicioEnvuelto);
        this.costoExtra = costoExtra;
    }

    @Override
    public double calcularPrecio() {
        return super.calcularPrecio() + costoExtra;
    }

    @Override
    public String obtenerDescripcion() {
        return super.obtenerDescripcion() + " + Peluquería";
    }
}