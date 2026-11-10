package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Guarderia;
import com.example.Paquete;
import com.example.Paseo;


/** * Pruebas unitarias para la clase Paquete */ 
public class PaqueteTest {
    @Test
    @DisplayName("PU-05 - Paquete debe calcular precio con descuento")
    void calcularPrecioConDescuento() throws Exception {
        Paquete paquete = new Paquete();

        Guarderia guarderia = new Guarderia(20.0);
        Paseo paseo = new Paseo(10.0);

        paquete.agregarServicio(guarderia);
        paquete.agregarServicio(paseo);

        // Aplicar descuento del 10%
        paquete.setDescuento(0.10);

        // Precio base = 20 + 10 = 30
        // Descuento = 10% de 30 = 3
        // Precio final = 27
        assertEquals(
                27.0,
                paquete.calcularPrecio(),
                0.001
        );
        

    }
}
