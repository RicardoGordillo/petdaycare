package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.TamanoMascota;
import com.example.Usuario;
import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Builder_Mascota.MascotaBuilder;

public class UsuarioTest {
    
    @Test
    @DisplayName("PU-09 - Usuario debe registrar una mascota válida")
    void registrarMascota() {

        Usuario usuario = new Usuario();

        Mascota mascota = new MascotaBuilder()
                .setNombre("Firulais")
                .setEspecie("Perro")
                .setRaza("Poodle")
                .setEdad(3)
                .setTamano(TamanoMascota.MEDIANO)
                .build();

        usuario.registrarMascota(mascota);

        assertEquals(1, usuario.getMascotas().size());
        assertTrue(usuario.getMascotas().contains(mascota));
    }
}
