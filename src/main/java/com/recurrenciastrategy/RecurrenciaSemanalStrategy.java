package com.recurrenciastrategy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecurrenciaSemanalStrategy implements IRecurrenciaStrategy {
    
    private Set<DayOfWeek> diasPermitidos;

    public RecurrenciaSemanalStrategy(Set<DayOfWeek> diasPermitidos) {
        this.diasPermitidos = diasPermitidos;
    }

    @Override
    public List<LocalDate> calcularFechas(LocalDate inicio, LocalDate fin) {
        List<LocalDate> fechas = new ArrayList<>();
        if (inicio == null || fin == null || fin.isBefore(inicio) || diasPermitidos == null) {
            return fechas;
        }

        LocalDate actual = inicio;
        while (!actual.isAfter(fin)) {
            if (diasPermitidos.contains(actual.getDayOfWeek())) {
                fechas.add(actual);
            }
            actual = actual.plusDays(1);
        }
        return fechas;
    }
}
