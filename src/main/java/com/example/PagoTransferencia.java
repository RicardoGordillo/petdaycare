package com.example;

public class PagoTransferencia implements IMetodoPago {

    @Override
    public boolean procesarPago(double total) {
        System.out.println("Procesando pago por transferencia por: $" + total);
        return true;
    }
}
