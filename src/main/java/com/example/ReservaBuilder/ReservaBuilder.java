package com.example.ReservaBuilder;

import java.time.LocalDate;

import com.example.Cuidador;
import com.example.IMetodoPago;
import com.example.IServicio;
import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Decorator.DecoradorCamaraEnVivo;
import com.example.Patron_Observer.IObserverReserva;
import com.example.Usuario;
import com.recurrenciastrategy.IRecurrenciaStrategy;

public class ReservaBuilder implements IReservaBuilder {
    private Reserva reserva;
    private IServicio servicioBase;

    public ReservaBuilder() {
        this.reset();
    }

    @Override
    public IReservaBuilder reset() {
        this.reserva = new Reserva();
        this.servicioBase = null;
        return this;
    }

    @Override
    public IReservaBuilder setCliente(Usuario cliente) {
        this.reserva.setCliente(cliente);
        return this;
    }

    @Override
    public IReservaBuilder setMascota(Mascota mascota) {
        this.reserva.setMascota(mascota);
        return this;
    }

    @Override
    public IReservaBuilder setServicioBase(IServicio servicio) {
        this.servicioBase = servicio;
        return this;
    }

    @Override
    public IReservaBuilder agregarCamaraEnVivo(boolean activar) {
        if (activar && this.servicioBase != null) {
            this.servicioBase = new DecoradorCamaraEnVivo(this.servicioBase, 5.0);
        }
        return this;
    }

    @Override
    public IReservaBuilder agregarReporteTiempoReal(boolean activar) {
        if (activar) {
            System.out.println("Reporte en tiempo real activado para la reserva.");
        }
        return this;
    }

    @Override
    public IReservaBuilder agregarAtencionVeterinaria(boolean activar) {
        if (activar) {
            System.out.println("Atencion veterinaria basica incluida en la reserva.");
        }
        return this;
    }

    @Override
    public IReservaBuilder setFechas(LocalDate inicio, LocalDate fin) {
        this.reserva.setFechaInicio(inicio);
        this.reserva.setFechaFin(fin);
        return this;
    }

    @Override
    public IReservaBuilder asignarCuidador(Cuidador cuidador) {
        this.reserva.setCuidador(cuidador);
        return this;
    }

    @Override
    public IReservaBuilder agregarServicioComplementario(IServicio servicio) {
        this.reserva.addServicio(servicio);
        return this;
    }

    @Override
    public IReservaBuilder setRecurrencia(IRecurrenciaStrategy estrategia) {
        if (estrategia != null) {
            this.reserva.setEsRecurrente(true);
            this.reserva.setEstrategiaRecurrencia(estrategia);
        }
        return this;
    }

    @Override
    public IReservaBuilder setMetodoPago(IMetodoPago metodoPago) {
        this.reserva.setMetodoPago(metodoPago);
        return this;
    }

    @Override
    public IReservaBuilder agregarObservador(IObserverReserva observador) {
        if (observador != null) {
            this.reserva.agregarObservador(observador);
        }
        return this;
    }

    @Override
    public Reserva build() {
        if (this.servicioBase != null) {
            this.reserva.setServicioBasico(this.servicioBase);
        }
        if (reserva.getMascota() == null || reserva.getFechaInicio() == null || reserva.getFechaFin() == null) {
            throw new IllegalStateException("Faltan datos obligatorios (Mascota o Fechas) para construir la reserva.");
        }
        if (reserva.getFechaFin().isBefore(reserva.getFechaInicio())) {
            throw new IllegalStateException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if (reserva.getCuidador() != null) {
            boolean disponible = reserva.getCuidador().verificarDisponibilidad(reserva.getFechaInicio());
            if (!disponible) {
                throw new IllegalStateException("El cuidador seleccionado no está disponible para esa fecha.");
            }
        }
        if (reserva.getServicios().isEmpty()) {
            throw new IllegalStateException("Debe agregar al menos un servicio.");
        }
        reserva.programarFechasRecurrentes();
        Reserva reservaTerminada = this.reserva;
        this.reset();
        return reservaTerminada;
    }
}
