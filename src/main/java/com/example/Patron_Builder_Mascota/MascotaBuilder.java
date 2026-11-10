package com.example.Patron_Builder_Mascota;

import com.example.TamanoMascota;

public class MascotaBuilder implements IMascotaBuilder{
    private Mascota mascota;

    public MascotaBuilder() {
        this.reset();
    }

    @Override
    public IMascotaBuilder reset() {
        this.mascota = new Mascota();
        return this;
    }

    @Override
    public IMascotaBuilder setId(int id) {
        this.mascota.setId(id);
        return this;
    }

    @Override
    public IMascotaBuilder setNombre(String nombre) {
        this.mascota.setNombre(nombre);
        return this;
    }

    @Override
    public IMascotaBuilder setEspecie(String especie) {
        this.mascota.setEspecie(especie);
        return this;
    }

    @Override
    public IMascotaBuilder setRaza(String raza) {
        this.mascota.setRaza(raza);
        return this;
    }

    @Override
    public IMascotaBuilder setTamano(TamanoMascota tamano) { 
        this.mascota.setTamano(tamano);
        return this;
    }

    @Override
    public IMascotaBuilder setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad de la mascota no puede ser negativa.");
        }
        this.mascota.setEdad(edad);
        return this;
    }

    @Override
    public IMascotaBuilder agregarNecesidadEspecial(String necesidad) {

        this.mascota.agregarNecesidadEspecial(necesidad);
        return this;
    }

    @Override
    public IMascotaBuilder agregarPreferenciasTrato(String preferencia) {

        this.mascota.agregarPreferenciaTrato(preferencia);
        return this;
    }

    @Override
    public Mascota build() {
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            throw new IllegalStateException("No se puede registrar una mascota sin nombre.");
        }
        if (mascota.getEspecie() == null || mascota.getEspecie().trim().isEmpty()) {
            throw new IllegalStateException("Debe especificar la especie de la mascota (ej. Perro, Gato).");
        }

        Mascota mascotaConstruida = this.mascota;
        this.reset();
        return mascotaConstruida;
    }
}
