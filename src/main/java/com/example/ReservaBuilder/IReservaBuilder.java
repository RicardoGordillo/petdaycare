package com.example.ReservaBuilder;

import java.time.LocalDate;

import com.example.Cuidador;
import com.example.IMetodoPago;
import com.example.IServicio;
import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Observer.IObserverReserva;
import com.example.Usuario;
import com.recurrenciastrategy.IRecurrenciaStrategy;

public interface IReservaBuilder {

    IReservaBuilder reset();
    IReservaBuilder setCliente(Usuario cliente);
    IReservaBuilder setMascota(Mascota mascota);
    IReservaBuilder setServicioBase(IServicio servicio);
    IReservaBuilder setFechas(LocalDate inicio, LocalDate fin);
    IReservaBuilder asignarCuidador(Cuidador cuidador);
    IReservaBuilder agregarCamaraEnVivo(boolean activar);
    IReservaBuilder agregarReporteTiempoReal(boolean activar);
    IReservaBuilder agregarAtencionVeterinaria(boolean activar);
    IReservaBuilder agregarServicioComplementario(IServicio servicio);
    IReservaBuilder setRecurrencia(IRecurrenciaStrategy estrategia);
    IReservaBuilder setMetodoPago(IMetodoPago metodoPago);
    IReservaBuilder agregarObservador(IObserverReserva observador);
    Reserva build();
    
}
