package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.Patron_Builder_Mascota.Mascota;
import com.example.ReservaBuilder.Reserva;



public class Cuidador {

    private int id;
    private String nombre;
    private String especialidad;
    private Centro centro;
    private int capacidadMaximaDiaria = 3;
    private List<Reserva> reservasAsignadas = new ArrayList<>();

    public Cuidador() {}

    public Cuidador(int id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public boolean verificarDisponibilidad(LocalDate fecha) {
        if (fecha == null) return false;

        long reservasEnEsaFecha = reservasAsignadas.stream()
                .filter(r -> r.getEstado() != EstadoReserva.CANCELADO)
                .filter(r -> !fecha.isBefore(r.getFechaInicio()) && !fecha.isAfter(r.getFechaFin()))
                .count();

        return reservasEnEsaFecha < capacidadMaximaDiaria;
    }

    public void agregarReserva(Reserva reserva) {
        if (reserva != null && !reservasAsignadas.contains(reserva)) {
            reservasAsignadas.add(reserva);
        }
    }

    public void removerReserva(Reserva reserva) {
        reservasAsignadas.remove(reserva);
    }


    public void atenderMascotas(Mascota mascota) {
        System.out.println("El cuidador " + this.nombre + " (" + this.especialidad + ") esta atendiendo a: " + mascota.getNombre());
    }

    public String generarComentario(Mascota mascota, String observacion) {
        return "Cuidador [" + this.nombre + "] reporta sobre [" + mascota.getNombre() + "]: " + observacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public Centro getCentro() { return centro; }
    public void setCentro(Centro centro) { this.centro = centro; }

    public int getCapacidadMaximaDiaria() { return capacidadMaximaDiaria; }
    public void setCapacidadMaximaDiaria(int capacidadMaximaDiaria) { this.capacidadMaximaDiaria = capacidadMaximaDiaria; }

    public List<Reserva> getReservasAsignadas() { return reservasAsignadas; }
}