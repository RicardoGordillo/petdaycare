package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.EstadoIncidente;
import com.example.Incidente;

public class IncidenteTest {
    @Test
    @DisplayName("PU-11 - Incidente debe cambiar su estado a RESUELTO")
    void resolverIncidente() throws Exception {

        Incidente incidente = new Incidente();

        incidente.resolver();

        assertEquals(
                EstadoIncidente.RESUELTO,
                incidente.getEstado()
        );
    
    }
}
