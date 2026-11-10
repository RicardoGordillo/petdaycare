package com;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.recurrenciastrategy.IRecurrenciaStrategy;
import com.recurrenciastrategy.RecurrenciaSemanalStrategy;

public class RecurrenciaSemanalStrategyTest {

    @Test
    @DisplayName("PU-08 - Debe generar las fechas correspondientes a los martes")
    void calcularFechasSemanales() {

        Set<DayOfWeek> dias =
                Set.of(DayOfWeek.TUESDAY);

        IRecurrenciaStrategy estrategia =
                new RecurrenciaSemanalStrategy(dias);

        List<LocalDate> fechas =
                estrategia.calcularFechas(
                        LocalDate.of(2026, 9, 1),
                        LocalDate.of(2026, 9, 15)
                );

        assertEquals(3, fechas.size());

        assertEquals(
                LocalDate.of(2026, 9, 1),
                fechas.get(0)
        );

        assertEquals(
                LocalDate.of(2026, 9, 8),
                fechas.get(1)
        );

        assertEquals(
                LocalDate.of(2026, 9, 15),
                fechas.get(2)
        );
    }
}