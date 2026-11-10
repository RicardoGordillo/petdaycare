package com.recurrenciastrategy;


import java.time.LocalDate;
import java.util.List;

public interface IRecurrenciaStrategy {
    public List<LocalDate> calcularFechas(LocalDate fechaInicio, LocalDate fechaFin);
}
