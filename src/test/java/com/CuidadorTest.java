package com;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Cuidador;
import com.example.ReservaBuilder.Reserva;

public class CuidadorTest {
    @Test
    @DisplayName("PU-21 - Cuidador no debe estar disponible al alcanzar 3 reservas")
    void capacidadMaxima() throws Exception {

        Cuidador cuidador =
                new Cuidador(
                        1,
                        "Carlos",
                        "General"
                );

        LocalDate fecha =
                LocalDate.of(2026, 9, 10);

        for (int i = 0; i < 3; i++) {

            Reserva reserva = new Reserva();

            reserva.setFechaInicio(fecha);
            reserva.setFechaFin(fecha);

            cuidador.agregarReserva(reserva);
        }

        assertFalse(
                cuidador.verificarDisponibilidad(fecha)
        );
    }
}
