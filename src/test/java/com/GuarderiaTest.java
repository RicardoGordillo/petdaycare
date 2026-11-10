package com;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Guarderia;

/** * Pruebas unitarias para la clase Guarderia */ 
public class GuarderiaTest {
    
    @Test
    @DisplayName("PU-03 - Guardería debe estar disponible sin bloqueos previos")
    void verificarDisponibilidad() {

        Guarderia guarderia = new Guarderia(20.0);

        LocalDate fecha =
                LocalDate.of(2026, 9, 15);

        assertTrue(
                guarderia.verificarDisponibilidad(fecha)
        );
    }
}
