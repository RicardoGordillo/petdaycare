package com.example.Patron_Observer;

import com.example.ReservaBuilder.Reserva;

public class NotificacionApp implements IObserverReserva {

    @Override
    public void actualizar(Reserva reserva, String evento) {
        System.out.println("Enviando notificación en la app para el evento: " + evento + " para la reserva: " + reserva);
    }
}
