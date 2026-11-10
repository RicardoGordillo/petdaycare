package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.TamanoMascota;
import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Builder_Mascota.MascotaBuilder;

/** * Pruebas unitarias para la clase MascotaBuilder */ 
public class MascotaBuilderTest {
    private MascotaBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new MascotaBuilder();
    }

    @AfterEach
    void tearDown() {
        builder = null;
    }

    @Test
    @DisplayName("PU-01 - Debe construir una mascota correctamente")
    void crearMascotaCorrectamente() {

        Mascota mascota = builder
                .setNombre("Firulais")
                .setEspecie("Perro")
                .setRaza("Poodle")
                .setEdad(3)
                .setTamano(TamanoMascota.MEDIANO)
                .build();

        assertNotNull(mascota);
        assertEquals("Firulais", mascota.getNombre());
        assertEquals("Perro", mascota.getEspecie());
        assertEquals("Poodle", mascota.getRaza());
        assertEquals(3, mascota.getEdad());
        assertEquals(TamanoMascota.MEDIANO, mascota.getTamano());
    }

    @Test
    @DisplayName("PU-17 - Debe rechazar una edad negativa")
    void noDebePermitirEdadNegativa() {

         IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> builder.setEdad(-1)
                );

        assertEquals(
                "La edad de la mascota no puede ser negativa.",
                exception.getMessage()
        );  
    }

    @Test
    @DisplayName("PU-18 - Debe rechazar una mascota sin nombre")
    void noDebePermitirMascotaSinNombre() {
        
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> builder
                                .setEspecie("Gato")
                                .build());

        assertEquals(
                "No se puede registrar una mascota sin nombre.",
                exception.getMessage()
        );
    } 
}
