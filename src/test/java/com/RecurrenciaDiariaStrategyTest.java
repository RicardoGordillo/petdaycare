package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.recurrenciastrategy.IRecurrenciaStrategy;
import com.recurrenciastrategy.RecurrenciaDiariaStrategy;

public class RecurrenciaDiariaStrategyTest {

    private IRecurrenciaStrategy estrategia;
    

    @BeforeEach
    void setUp() {
        estrategia = new RecurrenciaDiariaStrategy();
    }


    @Test
    @DisplayName("PU-07 - Debe generar las fechas diarias del rango")
    void calcularFechasDiarias() {

        LocalDate inicio =
                LocalDate.of(2026, 9, 1);

        LocalDate fin =
                LocalDate.of(2026, 9, 3);

        List<LocalDate> fechas =
                estrategia.calcularFechas(inicio, fin);

        assertEquals(3, fechas.size());
        assertEquals(
                LocalDate.of(2026, 9, 1),
                fechas.get(0)
        );
        assertEquals(
                LocalDate.of(2026, 9, 2),
                fechas.get(1)
        );
        assertEquals(
                LocalDate.of(2026, 9, 3),
                fechas.get(2)
        );
    }

}
