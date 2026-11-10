package com.example;

public class PagoPaypal implements IMetodoPago {

    @Override
    public boolean procesarPago(double total) {
        System.out.println("Procesando pago con Paypal por: $" + total);
        return true;
    }

}
