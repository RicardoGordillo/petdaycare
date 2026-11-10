package com;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Restriccion;
import com.example.TamanoMascota;
import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Builder_Mascota.MascotaBuilder;

public class RestriccionTest {
    @Test
    @DisplayName("PU-23 - Restricción debe rechazar mascota de raza prohibida")
    void validarRaza() {

        Mascota pitbull =
                new MascotaBuilder()
                        .setNombre("Rocky")
                        .setEspecie("Perro")
                        .setRaza("Pitbull")
                        .setTamano(TamanoMascota.GRANDE)
                        .setEdad(4)
                        .build();

        Restriccion restriccion =
                new Restriccion(
                        1,
                        "no se aceptan pitbull"
                );

        assertFalse(
                restriccion.validar(pitbull)
        );
    }
}
