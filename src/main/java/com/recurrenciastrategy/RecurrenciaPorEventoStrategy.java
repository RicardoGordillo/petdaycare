package com.recurrenciastrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecurrenciaPorEventoStrategy implements IRecurrenciaStrategy {

    private List<LocalDate> fechasDelEvento;

    public RecurrenciaPorEventoStrategy(List<LocalDate> fechasDelEvento) {
        this.fechasDelEvento = (fechasDelEvento == null) ? new ArrayList<>() : new ArrayList<>(fechasDelEvento);
    }

    @Override
    public List<LocalDate> calcularFechas(LocalDate inicio, LocalDate fin) {
        List<LocalDate> fechas = new ArrayList<>();
        if (inicio == null || fin == null || fin.isBefore(inicio) || fechasDelEvento == null) {
            return fechas;
        }

        for (LocalDate fecha : fechasDelEvento) {
            if (!fecha.isBefore(inicio) && !fecha.isAfter(fin)) {
                fechas.add(fecha);
            }
        }
        return fechas;
    }
}