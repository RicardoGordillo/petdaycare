package com;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Centro;

public class CentroTest {
    @Test
    @DisplayName("PU-10 - Centro debe actualizar correctamente el horario")
    void setHorario() throws Exception {

        Centro centro = new Centro();

        assertDoesNotThrow(() ->
                centro.setHorario(
                        LocalTime.of(8, 0),
                        LocalTime.of(18, 0)
                )
        );

        assertEquals(
                LocalTime.of(8, 0),
                centro.getHoraApertura()
        );

        assertEquals(
                LocalTime.of(18, 0),
                centro.getHoraCierre()
        );
    }
}
