package com.example;

public class PagoTarjeta implements IMetodoPago {

    @Override
    public boolean procesarPago(double total) {
        System.out.println("Procesando pago con tarjeta por: $" + total);
        return true;
    }
}
