package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Paseo;

/** * Pruebas unitarias para la clase Paseo */ 
public class PaseoTest {
    @Test
    @DisplayName("PU-04 - Paseo debe devolver su precio base")
    void calcularPrecio() {

        Paseo paseo = new Paseo(15.0);

        assertEquals(
                15.0,
                paseo.calcularPrecio(),
                0.001
        );
    }
}
