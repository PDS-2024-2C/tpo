import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Teatro teatro = Teatro.getInstance("Teatro Colón");

        // Crear datos de prueba
        inicializarDatos(teatro);

        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("tomas", "tomas@gmail.com"));
        clientes.add(new Cliente("federico", "federicomartos@gmail.com"));

        boolean ejecutar = true;
        while (ejecutar) {
            System.out.println("\n=== Bienvenido al Teatro Colón ===\n");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ingresar como Cliente");
            System.out.println("2. Ingresar como Administrador");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    Cliente cliente = manejarCliente(scanner, clientes);
                    if (cliente != null) {
                        manejarCompra(scanner, teatro, cliente);
                    }
                }
                case 2 -> manejarAdministrador(scanner, teatro);
                case 3 -> {
                    ejecutar = false;
                    System.out.println("\nGracias por usar el sistema del Teatro Colón. ¡Hasta luego!\n");
                }
                default -> System.out.println("\nOpción no válida. Intente nuevamente.\n");
            }
        }
        scanner.close();
    }

    private static void inicializarDatos(Teatro teatro) {
        GrupoDeActores grupo1 = new GrupoDeActores();
        grupo1.agregarActor(new Actor("Juan Pérez"));
        grupo1.agregarActor(new Actor("Ana Gómez"));

        GrupoDeActores grupo2 = new GrupoDeActores();
        grupo2.agregarActor(new Actor("Luis Martinez"));
        grupo2.agregarActor(new Actor("Maria Sanchez"));
        grupo2.agregarActor(new Actor("Jose Perez"));

        teatro.addGrupoDeActores(grupo1);
        teatro.addGrupoDeActores(grupo2);

        Obra obra1 = teatro.cargarObra("Hamlet");
        obra1.asignarGrupo(grupo1);
        obra1.setDuracion(120);

        Obra obra2 = teatro.cargarObra("La Casa de Bernarda Alba");
        obra2.asignarGrupo(grupo2);
        obra2.setDuracion(100);

        Funcion funcion1 = teatro.cargarFuncion(obra1);
        funcion1.setFecha(new Date());
        funcion1.setPrecio(500);
        inicializarLugaresFuncion(funcion1);

        Funcion funcion2 = teatro.cargarFuncion(obra1);
        funcion2.setFecha(new Date(System.currentTimeMillis() + 86400000));
        funcion2.setPrecio(550);
        inicializarLugaresFuncion(funcion2);

        Funcion funcion3 = teatro.cargarFuncion(obra2);
        funcion3.setFecha(new Date());
        funcion3.setPrecio(600);
        inicializarLugaresFuncion(funcion3);

        Funcion funcion4 = teatro.cargarFuncion(obra2);
        funcion4.setFecha(new Date(System.currentTimeMillis() + 86400000 * 2));
        funcion4.setPrecio(650);
        inicializarLugaresFuncion(funcion4);
    }

    private static void inicializarLugaresFuncion(Funcion funcion) {
        funcion.agregarLugar(new Platea(50), 50);
        funcion.agregarLugar(new PalcoAlto(50), 50);
        funcion.agregarLugar(new PalcoBajo(50), 50);
        funcion.agregarLugar(new Cazuela(50), 50);
        funcion.agregarLugar(new Tertulia(50), 50);
        funcion.agregarLugar(new Paraiso(50), 50);
    }

    private static void manejarCompra(Scanner scanner, Teatro teatro, Cliente cliente) {
        System.out.println("\nObras disponibles: \n");
        ArrayList<Obra> obras = teatro.getObras();
        for (int i = 0; i < obras.size(); i++) {
            System.out.println(i + ". " + obras.get(i).getNombre());
        }

        System.out.print("\nSeleccione el número de la obra: ");
        int inputObra = scanner.nextInt();
        if (inputObra < 0 || inputObra >= obras.size()) {
            System.out.println("\nOpción inválida.\n");
            return;
        }

        Obra obraSeleccionada = obras.get(inputObra);
        ArrayList<Funcion> funcionesObra = teatro.obtenerFuncionesDeObra(obraSeleccionada);

        if (funcionesObra.isEmpty()) {
            System.out.println("\nNo hay funciones disponibles para esta obra.\n");
            return;
        }

        System.out.println("\nFunciones disponibles:\n");
        for (int i = 0; i < funcionesObra.size(); i++) {
            Funcion funcion = funcionesObra.get(i);
            System.out.println(i + ". " + funcion.getFecha() + " - $" + funcion.getPrecio());
        }

        System.out.print("\nSeleccione el número de la función: ");
        int inputFuncion = scanner.nextInt();
        if (inputFuncion < 0 || inputFuncion >= funcionesObra.size()) {
            System.out.println("\nOpción inválida.\n");
            return;
        }

        Funcion funcionSeleccionada = funcionesObra.get(inputFuncion);
        PaqueteEntrada entradas = new PaqueteEntrada();

        System.out.print("\nIngrese la cantidad de entradas a comprar: ");
        int cantidadEntradas = scanner.nextInt();

        for (int j = 1; j <= cantidadEntradas; j++) {
            System.out.println("\nSeleccione el tipo de lugar para la entrada " + j + ":");
            int index = 1;
            for (Lugar lugar : funcionSeleccionada.getLugaresDisponibles().keySet()) {
                int disponibles = funcionSeleccionada.getLugaresDisponibles().get(lugar);
                System.out.println(index + ": " + lugar.getClass().getSimpleName() + " (" + disponibles + " disponibles)");
                index++;
            }
            System.out.print("Opción: ");
            int tipoLugar = scanner.nextInt() - 1;

            Lugar lugarSeleccionado = null;
            index = 0;
            for (Lugar lugar : funcionSeleccionada.getLugaresDisponibles().keySet()) {
                if (index == tipoLugar) {
                    lugarSeleccionado = lugar;
                    break;
                }
                index++;
            }

            if (lugarSeleccionado == null || !funcionSeleccionada.ocuparLugar(lugarSeleccionado)) {
                System.out.println("\nNo hay lugares disponibles para esta opción. Intente con otra ubicación.\n");
                j--; // Permitir reintento
            } else {
                EntradaSimple entrada = new EntradaSimple(funcionSeleccionada, lugarSeleccionado);
                entradas.agregarEntrada(entrada);
            }
        }

        System.out.println("\nSeleccione el medio de pago:");
        System.out.println("1. Efectivo\n2. Tarjeta Crédito\n3. Tarjeta Débito");
        System.out.print("Opción: ");
        int opcionPago = scanner.nextInt();
        MedioDePago medioDePago = switch (opcionPago) {
            case 1 -> new Efectivo();
            case 2 -> {
                System.out.print("\nIngrese la cantidad de cuotas para la tarjeta de crédito (2, 3, 6): ");
                int cuotas = scanner.nextInt();
                yield new TarjetaCredito(cuotas);
            }
            case 3 -> new TarjetaDebito();
            default -> {
                System.out.println("\nOpción inválida, se usará Efectivo por defecto.");
                yield new Efectivo();
            }
        };

        Venta venta = new Venta(cliente, medioDePago);
        venta.agregarEntrada(entradas);

        System.out.println("\nPrecio final con " + medioDePago.getClass().getSimpleName() + ": $" + venta.calcularPrecioFinal());
        venta.confirmar();
        System.out.println("\nCompra realizada exitosamente.\n");
    }

    private static void manejarAdministrador(Scanner scanner, Teatro teatro) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Opciones de Administrador ===");
            System.out.println("1. Ver todas las obras");
            System.out.println("2. Ver todas las funciones");
            System.out.println("3. Ver todos los grupos de actores");
            System.out.println("4. Cargar una nueva obra");
            System.out.println("5. Cargar un nuevo grupo de actores");
            System.out.println("6. Cargar una nueva función");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    ArrayList<Obra> obras = teatro.getObras();
                    System.out.println("\n=== Lista de Obras ===\n");
                    for (Obra obra : obras) {
                        System.out.println("Obra: " + obra.getNombre() + " - Duración: " + obra.getDuracion() + " minutos");
                    }
                    System.out.println();
                }
                case 2 -> {
                    ArrayList<Funcion> funciones = teatro.getFunciones();
                    System.out.println("\n=== Lista de Funciones ===\n");
                    for (Funcion funcion : funciones) {
                        System.out.println("Obra: " + funcion.getObra().getNombre() + " - Fecha: " + funcion.getFecha() + " - Precio: $" + funcion.getPrecio());
                    }
                    System.out.println();
                }
                case 3 -> {
                    ArrayList<GrupoDeActores> grupos = teatro.getGruposDeActores();
                    System.out.println("\n=== Lista de Grupos de Actores ===\n");
                    for (int i = 0; i < grupos.size(); i++) {
                        System.out.println("Grupo " + (i + 1) + ": " + grupos.get(i).getResumenActores());
                    }
                    System.out.println();
                }
                case 4 -> cargarObra(scanner, teatro);
                case 5 -> cargarGrupoDeActores(scanner, teatro);
                case 6 -> cargarFuncion(scanner, teatro);
                case 7 -> {
                    System.out.println("\nSaliendo del menú de administrador.\n");
                    continuar = false;
                }
                default -> System.out.println("\nOpción no válida. Intente nuevamente.\n");
            }
        }
    }


    private static Cliente manejarCliente(Scanner scanner, ArrayList<Cliente> clientes) {
        System.out.println("\nIngrese 1 para registrarse / 2 para iniciar sesión:");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        Cliente cliente = null;

        switch (opcion) {
            case 1 -> {
                System.out.println("\n=== Registro de Cliente ===");
                String nombre = "";
                String email = "";

                // Validar entrada de nombre
                while (nombre.isEmpty()) {
                    System.out.print("Ingrese su nombre: ");
                    nombre = scanner.nextLine().trim();
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío. Intente nuevamente.\n");
                    }
                }

                // Validar entrada de email
                boolean emailValido = false;
                while (!emailValido) {
                    System.out.print("Ingrese su email: ");
                    email = scanner.nextLine().trim();
                    if (email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                        emailValido = true;
                    } else {
                        System.out.println("El email ingresado no es válido. Intente nuevamente.\n");
                    }
                }

                cliente = new Cliente(nombre, email);
                clientes.add(cliente);
                System.out.println("\nCliente registrado exitosamente.\n");
            }
            case 2 -> {
                System.out.println("\n=== Inicio de Sesión ===");
                System.out.print("Ingrese su email: ");
                String email = scanner.nextLine().trim();

                for (Cliente clienteAux : clientes) {
                    if (clienteAux.getEmail().equalsIgnoreCase(email)) {
                        cliente = clienteAux;
                        break;
                    }
                }

                if (cliente == null) {
                    System.out.println("\nEl email ingresado no está registrado. Intente nuevamente o regístrese como nuevo cliente.\n");
                } else {
                    System.out.println("\nInicio de sesión exitoso. Bienvenido, " + cliente.getNombre() + "!\n");
                }
            }
            default -> System.out.println("\nOpción no válida. Por favor, seleccione 1 para registrarse o 2 para iniciar sesión.\n");
        }

        return cliente;
    }

    private static void cargarObra(Scanner scanner, Teatro teatro) {
        System.out.println("\n=== Cargar una Nueva Obra ===");

        System.out.print("Ingrese el nombre de la obra: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la duración de la obra (en minutos): ");
        int duracion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Selección o creación de un grupo de actores
        ArrayList<GrupoDeActores> grupos = teatro.getGruposDeActores();
        GrupoDeActores grupoAsignado;

        if (grupos.isEmpty()) {
            System.out.println("\nNo hay grupos de actores registrados. Se debe crear uno.\n");
            grupoAsignado = crearGrupoDeActores(scanner, teatro);
        } else {
            System.out.println("\nSeleccione un grupo de actores para asignar a la obra:");
            for (int i = 0; i < grupos.size(); i++) {
                System.out.println(i + ". Grupo " + (i + 1) + " - " + grupos.get(i).getResumenActores());
            }
            System.out.println(grupos.size() + ". Crear un nuevo grupo");

            System.out.print("Opción: ");
            int opcionGrupo = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (opcionGrupo == grupos.size()) {
                grupoAsignado = crearGrupoDeActores(scanner, teatro);
            } else if (opcionGrupo >= 0 && opcionGrupo < grupos.size()) {
                grupoAsignado = grupos.get(opcionGrupo);
            } else {
                System.out.println("\nOpción no válida. Creando un nuevo grupo.\n");
                grupoAsignado = crearGrupoDeActores(scanner, teatro);
            }
        }

        // Crear la obra y asignar el grupo
        Obra nuevaObra = teatro.cargarObra(nombre);
        nuevaObra.setDuracion(duracion);
        nuevaObra.asignarGrupo(grupoAsignado);

        System.out.println("\nObra cargada exitosamente: " + nuevaObra.getNombre() +
                " - " + nuevaObra.getDuracion() + " minutos, Grupo: " + grupoAsignado.getResumenActores() + ".\n");
    }

    private static GrupoDeActores crearGrupoDeActores(Scanner scanner, Teatro teatro) {
        System.out.println("\n=== Crear un Nuevo Grupo de Actores ===");

        GrupoDeActores nuevoGrupo = new GrupoDeActores();
        boolean agregarActores = true;

        while (agregarActores) {
            System.out.print("Ingrese el nombre del actor: ");
            String nombreActor = scanner.nextLine();

            nuevoGrupo.agregarActor(new Actor(nombreActor));
            System.out.println("Actor agregado: " + nombreActor);

            System.out.print("¿Desea agregar otro actor? (s/n): ");
            String opcion = scanner.nextLine();
            agregarActores = opcion.equalsIgnoreCase("s");
        }

        teatro.addGrupoDeActores(nuevoGrupo);
        System.out.println("\nGrupo de actores creado exitosamente.\n");
        return nuevoGrupo;
    }

    private static void cargarGrupoDeActores(Scanner scanner, Teatro teatro) {
        System.out.println("\n=== Cargar un Nuevo Grupo de Actores ===");

        GrupoDeActores nuevoGrupo = new GrupoDeActores();
        boolean agregarActores = true;

        while (agregarActores) {
            System.out.print("Ingrese el nombre del actor: ");
            String nombreActor = scanner.nextLine();

            nuevoGrupo.agregarActor(new Actor(nombreActor));
            System.out.println("Actor agregado: " + nombreActor);

            System.out.print("¿Desea agregar otro actor? (s/n): ");
            String opcion = scanner.nextLine();
            agregarActores = opcion.equalsIgnoreCase("s");
        }
        teatro.addGrupoDeActores(nuevoGrupo);
        System.out.println("\nGrupo de actores cargado exitosamente.\n");
    }

    private static void cargarFuncion(Scanner scanner, Teatro teatro) {
        System.out.println("\n=== Cargar una Nueva Función ===");

        ArrayList<Obra> obras = teatro.getObras();
        if (obras.isEmpty()) {
            System.out.println("\nNo hay obras registradas. Debe cargar una obra antes de agregar una función.\n");
            return;
        }

        System.out.println("Seleccione la obra para la función:");
        for (int i = 0; i < obras.size(); i++) {
            System.out.println(i + ". " + obras.get(i).getNombre());
        }

        System.out.print("Opción: ");
        int opcionObra = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (opcionObra < 0 || opcionObra >= obras.size()) {
            System.out.println("\nOpción no válida.\n");
            return;
        }

        Obra obraSeleccionada = obras.get(opcionObra);

        System.out.print("Ingrese la fecha y hora de la función (formato: yyyy-MM-dd HH:mm): ");
        String fechaHoraInput = scanner.nextLine();
        Date fecha;
        try {
            fecha = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaHoraInput);
        } catch (Exception e) {
            System.out.println("\nFormato de fecha y hora inválido. Intente nuevamente.\n");
            return;
        }

        System.out.print("Ingrese el precio de la función: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer

        Funcion nuevaFuncion = teatro.cargarFuncion(obraSeleccionada);
        nuevaFuncion.setFecha(fecha);
        nuevaFuncion.setPrecio(precio);

        // Inicializar los lugares de la nueva función
        inicializarLugaresFuncion(nuevaFuncion);

        System.out.println("\nFunción cargada exitosamente: " + obraSeleccionada.getNombre() + " - " + fecha + " - $" + precio + ".\n");
    }


}
