package com.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Administrador {

    private int id;
    private String nombre;
    private List<Centro> centrosAdministrados=new ArrayList<>();

    public Administrador() {
    }

    public Administrador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void agregarCentro(Centro centro) {
        centrosAdministrados.add(centro);
    }

    public void crearPromocion(Promocion promocion, Centro centro) {
        if (!this.centrosAdministrados.contains(centro)) {
            System.out.println("Error: Este administrador no gestiona el centro " + centro.getNombre());
            return;
        }
        centro.agregarPromocion(promocion);
        System.out.println("¡Promoción agregada con éxito al centro: " + centro.getNombre() + "!");
    }

    public void crearRestriccion(Restriccion restriccion, Centro centro) {
        if (!this.centrosAdministrados.contains(centro)) {
            System.out.println("Error: Este administrador no gestiona el centro " + centro.getNombre());
            return;
        }
        centro.agregarRestriccion(restriccion);
        System.out.println("¡Restricción añadida con éxito al centro: " + centro.getNombre() + "!");
    }

    public void crearPoliticaCancelacion(PoliticaCancelacion politica, Centro centro) {
        if (!this.centrosAdministrados.contains(centro)) {
            System.out.println("Error: Este administrador no gestiona el centro " + centro.getNombre());
            return;
        }
        centro.agregarPoliticaCancelacion(politica);
        System.out.println("¡Política de cancelación asignada con éxito al centro: " + centro.getNombre() + "!");
    }

    public void registrarServicio(IServicio servicio, Centro centro) {
        if (!this.centrosAdministrados.contains(centro)) {
            System.out.println("Error: Este administrador no gestiona el centro " + centro.getNombre());
            return;
        }
        if (centro.getServicios().contains(servicio)) {
            System.out.println("Error: El centro '" + centro.getNombre() + "' ya ofrece el servicio");
            return;
        }
        centro.agregarServicio(servicio);
        System.out.println("¡Servicio registrado con éxito en el centro: " + centro.getNombre() + "!");
    }

    public void actualizarHorario(LocalTime horaApertura, LocalTime horaCierre, Centro centro) {
        if (!this.centrosAdministrados.contains(centro)) {
            System.out.println("Error: Este administrador no gestiona el centro " + centro.getNombre());
            return;
        }

        centro.setHorario(horaApertura, horaCierre);
    }
}
