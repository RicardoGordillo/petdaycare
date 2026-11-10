package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.AlertaSalud;

public class AlertaSaludTest {
    @Test
    @DisplayName("PU-15 - AlertaSalud debe devolver correctamente su descripción")
    void getDescripcion() throws Exception {

        AlertaSalud alerta = new AlertaSalud();

        alerta.setDescripcion("Vacuna de rabia pendiente");

        assertEquals(
                "Vacuna de rabia pendiente",
                alerta.getDescripcion()
        );
    }
}
