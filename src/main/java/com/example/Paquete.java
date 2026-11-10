package com.example;

import java.util.ArrayList;
import java.util.List;

public class Paquete {

    private int id;
    private String nombre;
    private String tipo;
    private double descuento;
    

    private List<IServicio> servicios = new ArrayList<>();

    public double calcularPrecio() {
        double totalServicios = 0.0;

        for (IServicio servicio : servicios) {
            totalServicios += servicio.calcularPrecio();
        }

        double montoDescuento = totalServicios * this.descuento;
        return totalServicios - montoDescuento;
    }

    public void agregarServicio(IServicio servicio) {
        if (servicio == null) {
            return;
        }

        if (!this.servicios.contains(servicio)) {
            this.servicios.add(servicio);
        }
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}
