package com;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Patron_Observer.NotificacionEmail;
import com.example.ReservaBuilder.Reserva;

public class NotificacionEmailTest {
    
    @Test
    @DisplayName("PU-13 - Notificación Email debe generar mensaje de correo")
    void actualizar() {

        NotificacionEmail notificacion =
                new NotificacionEmail("cliente@email.com");

        Reserva reserva = new Reserva();

        ByteArrayOutputStream salida =
                new ByteArrayOutputStream();

        PrintStream salidaOriginal = System.out;

        try {
            System.setOut(new PrintStream(salida));

            notificacion.actualizar(
                    reserva,
                    "Confirmada"
            );

        } finally {
            System.setOut(salidaOriginal);
        }

        String mensaje = salida.toString();

        assertTrue(mensaje.contains("cliente@email.com"));
        assertTrue(mensaje.contains("Confirmada"));
    }
}
