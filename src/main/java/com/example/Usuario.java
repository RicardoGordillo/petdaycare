package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Observer.IObserverReserva;
import com.example.ReservaBuilder.Reserva;

public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String telefono;
    private List<Reserva> reservas= new ArrayList<>();
    private List<Mascota> mascotas= new ArrayList<>();
    private List<IObserverReserva> canalesNotificacion = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(int id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public void registrarMascota(Mascota mascota) {
        if (mascota == null) {
            return;
        }

        if (!this.mascotas.contains(mascota)) {
            this.mascotas.add(mascota);
            System.out.println("Mascota '" + mascota.getNombre() + "' registrada con exito para el usuario " + this.nombre);
        } else {
            System.out.println("La mascota ya se encuentra registrada.");
        }
    }

    public void agregarCanalNotificacion(IObserverReserva canal) {
        if (canal != null && !this.canalesNotificacion.contains(canal)) {
            this.canalesNotificacion.add(canal);
        }
    }

    public List<IObserverReserva> getCanalesNotificacion() {
        return this.canalesNotificacion;
    }

    public void crearReserva(Reserva reserva) {
        if (reserva == null) {
            return;
        }

        if (!this.reservas.contains(reserva)) {
            this.reservas.add(reserva);
            System.out.println("Reserva ID: " + reserva.getId() + " anadida al historial del usuario.");
        }
    }

    public void cancelarReserva(Reserva reserva) {
        if (reserva == null) {
            return;
        }

        if (this.reservas.contains(reserva)) {
            reserva.cancelar();
            System.out.println("La reserva ha sido cancelada desde la cuenta del usuario.");
        } else {
            System.out.println("Error: Esta reserva no pertenece a este usuario.");
        }
    }

    public void reportarIncidente(String descripcionIncidente) {
        if (descripcionIncidente == null || descripcionIncidente.trim().isEmpty()) {
            System.out.println("El reporte no puede estar vacío.");
            return;
        }

        System.out.println("Incidente reportado por " + this.nombre + ": " + descripcionIncidente);
    }

    public List<Reserva> consultarReservas() {
        return this.reservas;
    }

    public List<Mascota> getMascotas() {
        return this.mascotas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
