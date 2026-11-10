package com;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.PagoTarjeta;

public class PagoTarjetaTest {
    @Test
    @DisplayName("PU-14 - Pago con tarjeta debe procesarse correctamente")
    void procesarPago() {

        PagoTarjeta pago =
                new PagoTarjeta();

        boolean resultado =
                pago.procesarPago(45.0);

        assertTrue(resultado);
    }
}
