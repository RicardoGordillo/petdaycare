package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.Patron_Builder_Mascota.Mascota;
import com.example.Patron_Builder_Mascota.MascotaBuilder;
import com.example.Patron_Decorator.DecoradorBano;
import com.example.Patron_Decorator.DecoradorCamaraEnVivo;
import com.example.Patron_Decorator.DecoradorMasaje;
import com.example.Patron_Decorator.DecoradorPeluqueria;
import com.example.Patron_Observer.NotificacionApp;
import com.example.Patron_Observer.NotificacionEmail;
import com.example.Patron_Observer.NotificacionSMS;
import com.example.ReservaBuilder.IReservaBuilder;
import com.example.ReservaBuilder.Reserva;
import com.example.ReservaBuilder.ReservaBuilder;

/**
 * Clase de demostracion (consola) del sistema PetDayCare.
 * Permite: registrar clientes, registrar mascotas para un cliente,
 * registrar servicios en el centro, hacer reservas (usando el
 * Decorator para agregar extras al servicio elegido) y pagar las
 * reservas pendientes de un cliente.
 */
public class Main {

    // Costos fijos de los extras (Decorator) sobre un servicio.
    private static final double COSTO_BANO = 8.0;
    private static final double COSTO_CAMARA = 5.0;
    private static final double COSTO_MASAJE = 15.0;
    private static final double COSTO_PELUQUERIA = 12.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ---------- Datos hardcodeados antes de iniciar el programa ----------
        Administrador admin = new Administrador(1, "Admin General");
        Centro centro = new Centro(1, "PetDayCare Central", "Av. Principal 123", 50);
        admin.agregarCentro(centro);

        // Cuidadores hardcodeados (creados aqui, antes del menu)
        Cuidador cuidador1 = new Cuidador(1, "Laura Gomez", "Cuidado general");
        Cuidador cuidador2 = new Cuidador(2, "Carlos Perez", "Entrenamiento canino");
        Cuidador cuidador3 = new Cuidador(3, "Maria Torres", "Estetica y bienestar");
        centro.agregarCuidador(cuidador1);
        centro.agregarCuidador(cuidador2);
        centro.agregarCuidador(cuidador3);
        List<Cuidador> cuidadores = new ArrayList<>();
        cuidadores.add(cuidador1);
        cuidadores.add(cuidador2);
        cuidadores.add(cuidador3);

        List<Usuario> clientes = new ArrayList<>();
        // -----------------------------------------------------------------------

        boolean salir = false;
        while (!salir) {
            System.out.println("\n===== PetDayCare - Menu Principal =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar mascota para un cliente");
            System.out.println("3. Registrar servicio en el centro");
            System.out.println("4. Hacer una reserva");
            System.out.println("5. Pagar reservas pendientes de un cliente");
            System.out.println("6. Ver reservas de un cliente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    registrarCliente(sc, clientes);
                    break;
                case "2":
                    registrarMascota(sc, clientes);
                    break;
                case "3":
                    registrarServicio(sc, admin, centro);
                    break;
                case "4":
                    hacerReserva(sc, clientes, centro, cuidadores);
                    break;
                case "5":
                    pagarReservas(sc, clientes);
                    break;
                case "6":
                    verReservas(sc, clientes);
                    break;
                case "0":
                    salir = true;
                    System.out.println("Saliendo del sistema. Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }

        sc.close();
    }

    // ---------------------------------------------------------------------
    // Opcion 1: Registrar cliente
    // ---------------------------------------------------------------------
    private static void registrarCliente(Scanner sc, List<Usuario> clientes) {
        System.out.println("\n-- Registrar cliente --");
        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Correo: ");
        String correo = sc.nextLine().trim();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine().trim();

        int id = clientes.size() + 1;
        Usuario usuario = new Usuario(id, nombre, correo, telefono);

        // ---- Patron Observer: elegir canales de notificacion (se pueden combinar) ----
        boolean eligiendoCanales = true;
        while (eligiendoCanales) {
            System.out.println("\nCanales de notificacion para " + nombre + " (puede elegir varios):");
            System.out.println("1. Correo electronico");
            System.out.println("2. Notificacion en la app");
            System.out.println("3. SMS");
            System.out.println("0. Continuar sin agregar mas canales");
            System.out.print("Opcion: ");
            int opcionCanal = leerEntero(sc);
            switch (opcionCanal) {
                case 1:
                    usuario.agregarCanalNotificacion(new NotificacionEmail(correo));
                    System.out.println("Canal de correo electronico agregado.");
                    break;
                case 2:
                    usuario.agregarCanalNotificacion(new NotificacionApp());
                    System.out.println("Canal de notificacion en la app agregado.");
                    break;
                case 3:
                    usuario.agregarCanalNotificacion(new NotificacionSMS(telefono));
                    System.out.println("Canal de SMS agregado.");
                    break;
                case 0:
                    eligiendoCanales = false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }

        clientes.add(usuario);
        System.out.println("Cliente registrado con exito (ID interno: " + id + ").");
    }

    // ---------------------------------------------------------------------
    // Opcion 2: Registrar mascota para un cliente
    // ---------------------------------------------------------------------
    private static void registrarMascota(Scanner sc, List<Usuario> clientes) {
        Usuario cliente = seleccionarCliente(sc, clientes);
        if (cliente == null) {
            return;
        }

        System.out.println("\n-- Registrar mascota para " + cliente.getNombre() + " --");
        System.out.print("Nombre de la mascota: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Especie (ej. Perro, Gato): ");
        String especie = sc.nextLine().trim();
        System.out.print("Raza: ");
        String raza = sc.nextLine().trim();
        System.out.print("Edad: ");
        int edad = leerEntero(sc);

        System.out.println("Tamano: 1.Pequeno 2.Mediano 3.Grande 4.Gigante");
        System.out.print("Opcion: ");
        int opcionTamano = leerEntero(sc);
        TamanoMascota tamano;
        switch (opcionTamano) {
            case 1:
                tamano = TamanoMascota.PEQUENO;
                break;
            case 2:
                tamano = TamanoMascota.MEDIANO;
                break;
            case 3:
                tamano = TamanoMascota.GRANDE;
                break;
            case 4:
                tamano = TamanoMascota.GIGANTE;
                break;
            default:
                System.out.println("Opcion invalida, se asignara tamano MEDIANO por defecto.");
                tamano = TamanoMascota.MEDIANO;
                break;
        }

        try {
            Mascota mascota = new MascotaBuilder()
                    .setId(cliente.getMascotas().size() + 1)
                    .setNombre(nombre)
                    .setEspecie(especie)
                    .setRaza(raza)
                    .setEdad(edad)
                    .setTamano(tamano)
                    .build();
            mascota.setPropietario(cliente);
            cliente.registrarMascota(mascota);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Error al registrar la mascota: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------
    // Opcion 3: Registrar servicio en el centro
    // ---------------------------------------------------------------------
    private static void registrarServicio(Scanner sc, Administrador admin, Centro centro) {
        System.out.println("\n-- Registrar servicio en " + centro.getNombre() + " --");
        System.out.println("Tipo de servicio:");
        System.out.println("1. Guarderia");
        System.out.println("2. Hospedaje");
        System.out.println("3. Paseo");
        System.out.println("4. Entrenamiento");
        System.out.println("5. Sesiones de Bienestar");
        System.out.print("Opcion: ");
        int tipo = leerEntero(sc);

        System.out.print("Precio base del servicio: ");
        double precio;
        try {
            precio = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Precio invalido.");
            return;
        }

        IServicio servicio;
        switch (tipo) {
            case 1:
                servicio = new Guarderia(precio);
                break;
            case 2:
                servicio = new Hospedaje(precio);
                break;
            case 3:
                servicio = new Paseo(precio);
                break;
            case 4:
                servicio = new Entrenamiento(precio);
                break;
            case 5:
                servicio = new SesionesBienestar(precio);
                break;
            default:
                System.out.println("Opcion invalida.");
                return;
        }

        admin.registrarServicio(servicio, centro);
    }

    // ---------------------------------------------------------------------
    // Opcion 4: Hacer una reserva (usa el Decorator para los extras)
    // ---------------------------------------------------------------------
    private static void hacerReserva(Scanner sc, List<Usuario> clientes, Centro centro, List<Cuidador> cuidadores) {
        Usuario cliente = seleccionarCliente(sc, clientes);
        if (cliente == null) {
            return;
        }

        List<Mascota> mascotas = cliente.getMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("Este cliente no tiene mascotas registradas. Registre una primero.");
            return;
        }
        System.out.println("\nSeleccione una mascota:");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i).getNombre() + " (" + mascotas.get(i).getEspecie() + ")");
        }
        int idxMascota = leerEntero(sc);
        if (idxMascota < 1 || idxMascota > mascotas.size()) {
            System.out.println("Opcion invalida.");
            return;
        }
        Mascota mascota = mascotas.get(idxMascota - 1);

        List<IServicio> servicios = centro.getServicios();
        if (servicios.isEmpty()) {
            System.out.println("El centro no tiene servicios registrados todavia. Registre uno primero.");
            return;
        }
        System.out.println("\nSeleccione un servicio base:");
        for (int i = 0; i < servicios.size(); i++) {
            System.out.println((i + 1) + ". " + servicios.get(i).getNombre() + " - $" + servicios.get(i).calcularPrecio());
        }
        int idxServicio = leerEntero(sc);
        if (idxServicio < 1 || idxServicio > servicios.size()) {
            System.out.println("Opcion invalida.");
            return;
        }
        IServicio servicioSeleccionado = servicios.get(idxServicio - 1);

        // ---- Patron Decorator: agregar extras al servicio elegido ----
        boolean agregandoExtras = true;
        while (agregandoExtras) {
            System.out.println("\nExtras disponibles para '" + servicioSeleccionado.getNombre() + "' (precio actual: $"
                    + servicioSeleccionado.calcularPrecio() + "):");
            System.out.println("1. Bano (+$" + COSTO_BANO + ")");
            System.out.println("2. Camara en vivo (+$" + COSTO_CAMARA + ")");
            System.out.println("3. Masaje (+$" + COSTO_MASAJE + ")");
            System.out.println("4. Peluqueria (+$" + COSTO_PELUQUERIA + ")");
            System.out.println("0. Continuar sin agregar mas extras");
            System.out.print("Opcion: ");
            int opcionExtra = leerEntero(sc);
            switch (opcionExtra) {
                case 1:
                    servicioSeleccionado = new DecoradorBano(servicioSeleccionado, COSTO_BANO);
                    break;
                case 2:
                    servicioSeleccionado = new DecoradorCamaraEnVivo(servicioSeleccionado, COSTO_CAMARA);
                    break;
                case 3:
                    servicioSeleccionado = new DecoradorMasaje(servicioSeleccionado, COSTO_MASAJE);
                    break;
                case 4:
                    servicioSeleccionado = new DecoradorPeluqueria(servicioSeleccionado, COSTO_PELUQUERIA);
                    break;
                case 0:
                    agregandoExtras = false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }

        LocalDate fechaInicio;
        LocalDate fechaFin;
        try {
            System.out.print("\nFecha de inicio (yyyy-MM-dd): ");
            fechaInicio = LocalDate.parse(sc.nextLine().trim());
            System.out.print("Fecha de fin (yyyy-MM-dd): ");
            fechaFin = LocalDate.parse(sc.nextLine().trim());
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha invalido. Use yyyy-MM-dd.");
            return;
        }

        Cuidador cuidadorElegido = null;
        System.out.print("Desea asignar un cuidador especifico? (s/n): ");
        String resp = sc.nextLine().trim();
        if (resp.equalsIgnoreCase("s")) {
            System.out.println("Cuidadores disponibles:");
            for (int i = 0; i < cuidadores.size(); i++) {
                System.out.println((i + 1) + ". " + cuidadores.get(i).getNombre() + " - " + cuidadores.get(i).getEspecialidad());
            }
            System.out.print("Opcion: ");
            int idxCuidador = leerEntero(sc);
            if (idxCuidador >= 1 && idxCuidador <= cuidadores.size()) {
                cuidadorElegido = cuidadores.get(idxCuidador - 1);
            } else {
                System.out.println("Opcion invalida, se continuara sin cuidador asignado.");
            }
        }

        try {
            IReservaBuilder builder = new ReservaBuilder()
                    .setCliente(cliente)
                    .setMascota(mascota)
                    .setServicioBase(servicioSeleccionado)
                    .setFechas(fechaInicio, fechaFin);

            if (cuidadorElegido != null) {
                builder.asignarCuidador(cuidadorElegido);
            }

            Reserva reserva = builder.build();
            cliente.crearReserva(reserva);
            if (cuidadorElegido != null) {
                cuidadorElegido.agregarReserva(reserva);
            }

            reserva.notificarObservadores("Reserva creada, pendiente de pago.");
            
            System.out.println("\nReserva creada con exito.");
            System.out.println("Estado: " + reserva.getEstado());
            System.out.println("Descripcion del servicio: " + reserva.getServicioBasico().obtenerDescripcion());
            System.out.println("Monto total a pagar: $" + reserva.calcularMonto());
            System.out.println("(Use la opcion 5 del menu para pagarla).");
        } catch (IllegalStateException e) {
            System.out.println("No se pudo crear la reserva: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------------
    // Opcion 5: Pagar reservas pendientes de un cliente
    // ---------------------------------------------------------------------
    private static void pagarReservas(Scanner sc, List<Usuario> clientes) {
        Usuario cliente = seleccionarCliente(sc, clientes);
        if (cliente == null) {
            return;
        }

        List<Reserva> pendientes = new ArrayList<>();
        for (Reserva r : cliente.consultarReservas()) {
            if (r.getEstado() == EstadoReserva.EN_ESPERA) {
                pendientes.add(r);
            }
        }

        if (pendientes.isEmpty()) {
            System.out.println("Este cliente no tiene reservas pendientes de pago.");
            return;
        }

        System.out.println("\nReservas pendientes de " + cliente.getNombre() + ":");
        for (int i = 0; i < pendientes.size(); i++) {
            Reserva r = pendientes.get(i);
            System.out.println((i + 1) + ". Mascota: " + r.getMascota().getNombre()
                    + " | Del " + r.getFechaInicio() + " al " + r.getFechaFin()
                    + " | Monto: $" + r.calcularMonto());
        }
        System.out.print("Seleccione la reserva a pagar: ");
        int idx = leerEntero(sc);
        if (idx < 1 || idx > pendientes.size()) {
            System.out.println("Opcion invalida.");
            return;
        }
        Reserva reserva = pendientes.get(idx - 1);

        System.out.println("Metodo de pago:");
        System.out.println("1. Tarjeta");
        System.out.println("2. Paypal");
        System.out.println("3. Transferencia");
        System.out.print("Opcion: ");
        int opcionPago = leerEntero(sc);
        IMetodoPago metodoPago;
        switch (opcionPago) {
            case 1:
                metodoPago = new PagoTarjeta();
                break;
            case 2:
                metodoPago = new PagoPaypal();
                break;
            case 3:
                metodoPago = new PagoTransferencia();
                break;
            default:
                System.out.println("Opcion invalida.");
                return;
        }

        boolean pagoExitoso = metodoPago.procesarPago(reserva.calcularMonto());
        if (pagoExitoso) {
            reserva.confirmar();
            if (reserva.getEstado() == EstadoReserva.OCUPADO) {
                System.out.println("Pago procesado y reserva confirmada.");
            } else {
                System.out.println("El pago se proceso, pero la reserva no pudo confirmarse (sin cupos disponibles).");
            }
        } else {
            System.out.println("El pago no pudo procesarse.");
        }
    }

    // ---------------------------------------------------------------------
    // Opcion 6: Ver reservas de un cliente
    // ---------------------------------------------------------------------
    private static void verReservas(Scanner sc, List<Usuario> clientes) {
        Usuario cliente = seleccionarCliente(sc, clientes);
        if (cliente == null) {
            return;
        }

        List<Reserva> reservas = cliente.consultarReservas();
        if (reservas.isEmpty()) {
            System.out.println("Este cliente no tiene reservas registradas.");
            return;
        }

        System.out.println("\nReservas de " + cliente.getNombre() + ":");
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            System.out.println((i + 1) + ". Mascota: " + r.getMascota().getNombre()
                    + " | Estado: " + r.getEstado()
                    + " | Del " + r.getFechaInicio() + " al " + r.getFechaFin()
                    + " | Monto: $" + r.calcularMonto());
        }
    }

    // ---------------------------------------------------------------------
    // Utilidades
    // ---------------------------------------------------------------------
    private static Usuario seleccionarCliente(Scanner sc, List<Usuario> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados. Registre uno primero (opcion 1).");
            return null;
        }
        System.out.println("\nSeleccione un cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre());
        }
        System.out.print("Opcion: ");
        int idx = leerEntero(sc);
        if (idx < 1 || idx > clientes.size()) {
            System.out.println("Opcion invalida.");
            return null;
        }
        return clientes.get(idx - 1);
    }

    private static int leerEntero(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
