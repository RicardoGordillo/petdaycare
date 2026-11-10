package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Guarderia;
import com.example.IServicio;
import com.example.Patron_Decorator.DecoradorBano;

/** * Pruebas unitarias para la clase DecoratorBano */ 
public class DecoradorBanoTest {

    @Test
    @DisplayName("PU-06 - Baño debe sumar el costo extra")
    void calcularPrecio() {

        IServicio guarderia =
                new Guarderia(20.0);

        IServicio decorado =
                new DecoradorBano(
                        guarderia,
                        5.0
                );

        assertEquals(
                25.0,
                decorado.calcularPrecio(),
                0.001
        );
    }   

}