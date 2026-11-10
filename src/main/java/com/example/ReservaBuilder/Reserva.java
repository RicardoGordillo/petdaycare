package com.example.ReservaBuilder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.example.Cuidador;
import com.example.EstadoDisponibilidad;
import com.example.EstadoReserva;
import com.example.IMetodoPago;
import com.example.IServicio;
import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Observer.IObserverReserva;
import com.example.Usuario;
import com.recurrenciastrategy.IRecurrenciaStrategy;

public class Reserva {
    private int id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean esRecurrente;
    private IRecurrenciaStrategy estrategiaRecurrencia;
    private IMetodoPago metodoPago;
    private EstadoReserva estado = EstadoReserva.EN_ESPERA;
    private Usuario cliente;
    private Mascota mascota;
    private Cuidador cuidador;
    private IServicio servicioBasico;
    private List<IServicio> servicios = new ArrayList<>();
    private List<LocalDate> fechasAsistencia = new ArrayList<>();
    private List<IObserverReserva> observadores = new ArrayList<>();

    public Reserva() {
    }

    public void confirmar() {
        if(!verificarDisponibilidadServicios()){
            notificarObservadores("No existen cupos disponibles.");
            return;
        }

        cambiarEstadoServicios(EstadoDisponibilidad.OCUPADO);

        estado = EstadoReserva.OCUPADO;

        notificarObservadores("Reserva confirmada correctamente.");
    }

    public void cancelar() {
        cambiarEstadoServicios(EstadoDisponibilidad.DISPONIBLE);

        estado = EstadoReserva.CANCELADO;

        notificarObservadores("Reserva cancelada correctamente.");
    }

    public double calcularMonto() {
        double totalServicios = 0;
        for (IServicio servicio : servicios) {
            totalServicios += servicio.calcularPrecio();
        }

        if (!esRecurrente) {
            long dias = ChronoUnit.DAYS.between(fechaInicio,fechaFin) + 1;
            return totalServicios * dias;
        }

        return totalServicios * fechasAsistencia.size();
    }

    public void programarFechasRecurrentes() {
        if (this.estrategiaRecurrencia == null || this.fechaInicio == null || this.fechaFin == null) {
            return;
        }
        this.esRecurrente = true;
        this.fechasAsistencia.clear();
        
        List<LocalDate> fechasCalculadas = this.estrategiaRecurrencia.calcularFechas(this.fechaInicio, this.fechaFin);
        this.fechasAsistencia.addAll(fechasCalculadas);
    }

    public void agregarObservador(IObserverReserva obs) {
        if (obs != null && !this.observadores.contains(obs)) {
            this.observadores.add(obs);
        }
    }

    public void removerObservador(IObserverReserva obs) {
        this.observadores.remove(obs);
    }

    public void notificarObservadores(String evento) {
        for (IObserverReserva obs : observadores) {
            obs.actualizar(this, evento);
        }
    }
    
    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Mascota getMascota() {
        return mascota;
    }

    void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public IServicio getServicioBasico() {
        return servicioBasico;
    }

    void setServicioBasico(IServicio servicioBasico) {
        this.servicioBasico = servicioBasico;
        if (servicioBasico != null && !this.servicios.contains(servicioBasico)) {
            this.servicios.add(servicioBasico);
        }
    }

    public boolean isEsRecurrente() {
        return esRecurrente;
    }

    void setEsRecurrente(boolean esRecurrente) {
        this.esRecurrente = esRecurrente;
    }

    public IRecurrenciaStrategy getEstrategiaRecurrencia() {
        return estrategiaRecurrencia;
    }

    void setEstrategiaRecurrencia(IRecurrenciaStrategy estrategiaRecurrencia) {
        this.estrategiaRecurrencia = estrategiaRecurrencia;
    }

    public IMetodoPago getMetodoPago() {
        return metodoPago;
    }

    void setMetodoPago(IMetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<LocalDate> getFechasAsistencia() {
        return fechasAsistencia;
    }

    void addFechaAsistencia(LocalDate fecha) {
        this.fechasAsistencia.add(fecha);
    }

    public List<IServicio> getServicios() {
        return servicios;
    }

    void addServicio(IServicio servicio) {
        if (servicio != null)
            this.servicios.add(servicio);
    }

    public boolean verificarDisponibilidadServicios() {
        List<LocalDate> fechas = obtenerFechasReserva();
        for (LocalDate fecha : fechas) {
            for (IServicio servicio : servicios) {
                if (!servicio.verificarDisponibilidad(fecha)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<LocalDate> obtenerFechasReserva(){
        if(esRecurrente){
            return fechasAsistencia;
        }
        List<LocalDate> fechas = new ArrayList<>();
        LocalDate actual = fechaInicio;
        while(!actual.isAfter(fechaFin)){
            fechas.add(actual);
            actual = actual.plusDays(1);
        }
        return fechas;
}

    public void cambiarEstadoServicios(EstadoDisponibilidad estado) {
        for (LocalDate fecha : obtenerFechasReserva()) {
            for (IServicio servicio : servicios) {
                servicio.cambiarEstadoPorFecha(fecha, estado);
            }
        }
    }
}
