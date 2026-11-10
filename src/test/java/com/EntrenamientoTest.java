package com;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Entrenamiento;

public class EntrenamientoTest {
    @Test
    @DisplayName("PU-25 - Entrenamiento debe devolver false para fecha nula")
    void fechaNula() {

        Entrenamiento entrenamiento =
                new Entrenamiento();

        assertFalse(
                entrenamiento.verificarDisponibilidad(null)
        );
    }
}
