package com.example.Patron_Observer;

import com.example.ReservaBuilder.Reserva;

public interface IObserverReserva {
    public void actualizar(Reserva reserva, String evento);
}
