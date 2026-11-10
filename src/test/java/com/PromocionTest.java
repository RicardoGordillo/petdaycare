package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Promocion;

public class PromocionTest {
    @Test
    @DisplayName("PU-12 - Promoción debe calcular correctamente el descuento")
    void aplicarPromocion() {

        Promocion promocion = new Promocion(
                1,
                "Promoción 20%",
                0.20,
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30)
        );

        assertEquals(
                10.0,
                promocion.aplicarPromocion(50.0),
                0.001
        );
    }
}
