package com.example.Patron_Observer;

import com.example.ReservaBuilder.Reserva;

public class NotificacionSMS implements IObserverReserva {
    String numeroTelefono;

    public NotificacionSMS(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void actualizar(Reserva reserva, String evento) {
        System.out.println("Enviando notificación por SMS a " + numeroTelefono + " para el evento: " + evento + " para la reserva: " + reserva);
    }
}
