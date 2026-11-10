package com.example;

import com.example.Patron_Builder_Mascota.Mascota;

public class Restriccion {

    private int id;
    private String descripcion;

    public Restriccion(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public boolean validar(Mascota mascota) {
        if (mascota == null) {
            return false;
        }
        String desc = this.descripcion.toLowerCase();
        if (desc.contains("no se aceptan") && desc.contains(mascota.getRaza().toLowerCase())) {
            return false;
        }
        if (desc.contains("solo tamaño") && !desc.contains(mascota.getTamano().toString().toLowerCase())) {
            return false;
        }
        return true;
    }
}
