package com.recurrenciastrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecurrenciaDiariaStrategy implements IRecurrenciaStrategy {
    
    public RecurrenciaDiariaStrategy() {}

    @Override
    public List<LocalDate> calcularFechas(LocalDate inicio, LocalDate fin) {
        List<LocalDate> fechas = new ArrayList<>();
        if (inicio == null || fin == null || fin.isBefore(inicio)) {
            return fechas;
        }

        LocalDate actual = inicio;
        while (!actual.isAfter(fin)) {
            fechas.add(actual);
            actual = actual.plusDays(1);
        }
        return fechas;
    }
}