package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.TamanoMascota;
import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Builder_Mascota.MascotaBuilder;

/** * Pruebas unitarias para la clase Mascota*/ 
public class MascotaTest {
    private Mascota mascota;

    @BeforeEach
    void setUp() {

        mascota = new MascotaBuilder()
                .setId(1)
                .setNombre("Firulais")
                .setEspecie("Perro")
                .setRaza("Poodle")
                .setTamano(TamanoMascota.MEDIANO)
                .setEdad(3)
                .build();
        
    }

    @Test
    @DisplayName("PU-02 - Debe actualizar correctamente el perfil")
    void actualizarPerfil() {

        mascota.actualizarPerfil(
                "Max",
                TamanoMascota.GRANDE,
                4
        );

        assertEquals("Max", mascota.getNombre());
        assertEquals(TamanoMascota.GRANDE, mascota.getTamano());
        assertEquals(4, mascota.getEdad());
    }

}
