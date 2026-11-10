package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Builder_Mascota.MascotaBuilder;
import com.example.ReservaBuilder.ReservaBuilder;

public class ReservaBuilderTest {
    
    private Mascota crearMascota() {

        return new MascotaBuilder()
                .setNombre("Firulais")
                .setEspecie("Perro")
                .setRaza("Poodle")
                .setEdad(3)
                .build();
    }

    @Test
    @DisplayName("PU-19 - Debe rechazar fecha final anterior a fecha inicial")
    void fechaFinAnterior() {

        ReservaBuilder builder =
                new ReservaBuilder();

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> builder
                                .setMascota(crearMascota())
                                .setFechas(
                                        LocalDate.of(2026, 9, 1),
                                        LocalDate.of(2026, 8, 31)
                                )
                                .build()
                );

        assertEquals(
                "La fecha de fin no puede ser anterior a la fecha de inicio.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("PU-20 - Debe rechazar reserva sin servicios")
    void reservaSinServicios() {

        ReservaBuilder builder =
                new ReservaBuilder();

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> builder
                                .setMascota(crearMascota())
                                .setFechas(
                                        LocalDate.of(2026, 9, 1),
                                        LocalDate.of(2026, 9, 5)
                                )
                                .build()
                );

        assertEquals(
                "Debe agregar al menos un servicio.",
                exception.getMessage()
        );
    }
}
