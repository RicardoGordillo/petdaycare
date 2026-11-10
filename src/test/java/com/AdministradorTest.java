package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Administrador;
import com.example.Centro;
import com.example.Guarderia;
import com.example.Promocion;

public class AdministradorTest {
    @Test
    @DisplayName("PU-16 - Administrador debe crear promoción en centro administrado")
    void crearPromocion() throws Exception {

        Administrador administrador = new Administrador();

        Centro centro = new Centro();

        Promocion promocion = new Promocion(
                1,
                "Promoción 20%",
                0.20,
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30)
        );

        // Agregamos el centro al administrador
        administrador.agregarCentro(centro);

        // El administrador crea la promoción
        administrador.crearPromocion(
                promocion,
                centro
        );

        // Verificamos que la promoción fue agregada
        assertEquals(
                1,
                centro.getPromocionesVigentes().size()
        );

        assertTrue(
                centro.getPromocionesVigentes().contains(promocion)
        );
    }


    @Test
    @DisplayName("PU-24 - Administrador no debe registrar servicio en centro no administrado")
    void registrarServicioCentroNoAdministrado() {

        Administrador administrador =
                new Administrador();

        Centro centro =
                new Centro();

        Guarderia servicio =
                new Guarderia(20.0);

        ByteArrayOutputStream salida =
                new ByteArrayOutputStream();

        PrintStream salidaOriginal =
                System.out;

        try {

            System.setOut(
                    new PrintStream(salida)
            );

            administrador.registrarServicio(
                    servicio,
                    centro
            );

        } finally {

            System.setOut(salidaOriginal);
        }

        String mensaje =
                salida.toString();

        assertTrue(
                mensaje.contains("Error")
        );

        assertTrue(
                centro.getServicios().isEmpty()
        );
    }

}
