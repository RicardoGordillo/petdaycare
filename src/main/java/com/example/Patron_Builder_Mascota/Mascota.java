package com.example.Patron_Builder_Mascota;

import java.util.ArrayList;
import java.util.List;

import com.example.AlertaSalud;
import com.example.TamanoMascota;
import com.example.Usuario;

public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private TamanoMascota tamano;

    private List<String> necesidadesEspeciales = new ArrayList<>();
    private List<String> preferenciasTrato = new ArrayList<>();
    private List<AlertaSalud> alertas = new ArrayList<>();

    private Usuario propietario;

    protected Mascota() { }

    public void actualizarPerfil(String nuevoNombre, TamanoMascota nuevoTamano, int nuevaEdad) {
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            this.nombre = nuevoNombre;
        }
        if (nuevoTamano != null) {
            this.tamano = nuevoTamano;
        }
        this.setEdad(nuevaEdad);
    }

    public void agregarNecesidadEspecial(String necesidad){
        if (necesidad != null && !necesidad.trim().isEmpty()) {
            this.necesidadesEspeciales.add(necesidad);
        }
    }

    public void agregarPreferenciaTrato(String preferencia){
        if (preferencia != null && !preferencia.trim().isEmpty()) {
            this.preferenciasTrato.add(preferencia);
        }
    }

    public void registrarAlertaSalud(AlertaSalud alerta) {
        if (alerta != null) {
            this.alertas.add(alerta);
            System.out.println("--> [Alerta de Salud registrada en " + this.nombre + "]: " + alerta.getDescripcion());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if(edad < 0){
            throw new IllegalArgumentException("Edad invalida");
        }

        this.edad = edad;

    }

    public TamanoMascota getTamano() {
        return tamano;
    }

    public void setTamano(TamanoMascota tamano) {
        this.tamano = tamano;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public List<String> getNecesidadesEspeciales() {
        return necesidadesEspeciales;
    }

    public List<String> getPreferenciasTrato() {
        return preferenciasTrato;
    }

    public List<AlertaSalud> getAlertas() {
        return alertas;
    }
}
