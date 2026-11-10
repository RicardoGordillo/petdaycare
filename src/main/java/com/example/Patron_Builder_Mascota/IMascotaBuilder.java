package com.example.Patron_Builder_Mascota;

import com.example.TamanoMascota;

public interface IMascotaBuilder {
    IMascotaBuilder reset();
    IMascotaBuilder setId(int id);
    IMascotaBuilder setNombre(String nombre);
    IMascotaBuilder setEspecie(String especie);
    IMascotaBuilder setRaza(String raza);
    IMascotaBuilder setTamano(TamanoMascota tamano);
    IMascotaBuilder setEdad(int edad);

    IMascotaBuilder agregarNecesidadEspecial(String necesidad);

    IMascotaBuilder agregarPreferenciasTrato(String preferencia);

    Mascota build();
}
