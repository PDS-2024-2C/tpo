import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Teatro teatro = Teatro.getInstance("Teatro Colón");

        GrupoDeActores grupo1 = new GrupoDeActores();
        grupo1.agregarActor(new Actor("Juan Pérez"));
        grupo1.agregarActor(new Actor("Ana Gómez"));

        GrupoDeActores grupo2 = new GrupoDeActores();
        grupo2.agregarActor(new Actor("Luis Martinez"));
        grupo2.agregarActor(new Actor("Maria Sanchez"));
        grupo2.agregarActor(new Actor("Jose Perez"));

        Obra obra1 = teatro.cargarObra("Hamlet");
        obra1.asignarGrupo(grupo1);
        obra1.setDuracion(120);

        Obra obra2 = teatro.cargarObra("La Casa de Bernarda Alba");
        obra2.asignarGrupo(grupo2);
        obra2.setDuracion(100);

        Funcion funcion1 = teatro.cargarFuncion(obra1);
        funcion1.setFecha(new Date());
        funcion1.setPrecio(500);

        Funcion funcion2 = teatro.cargarFuncion(obra1);
        funcion2.setFecha(new Date(System.currentTimeMillis() + 86400000));
        funcion2.setPrecio(550);

        Funcion funcion3 = teatro.cargarFuncion(obra2);
        funcion3.setFecha(new Date());
        funcion3.setPrecio(600);

        Funcion funcion4 = teatro.cargarFuncion(obra2);
        funcion4.setFecha(new Date(System.currentTimeMillis() + 86400000 * 2));
        funcion4.setPrecio(650);
        

        ArrayList<Cliente> clientes = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            System.out.println("Bienvenido nuevo cliente! Ingrese su nombre:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese su apellido:");
            String apellido = scanner.nextLine();
            clientes.add(new Cliente(nombre, apellido));
            System.out.println();
        }


        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println("Para el cliente " + cliente.getNombre() + " " + cliente.getApellido() + ":");

            System.out.println("Ingrese el medio de pago (1: Efectivo, 2: Tarjeta Crédito, 3: Tarjeta Débito):");
            int opcionPago = Integer.parseInt(scanner.nextLine());
            MedioDePago medioDePago;
            if (opcionPago == 1) {
                medioDePago = new Efectivo();
            } else if (opcionPago == 2) {
                System.out.println("Ingrese la cantidad de cuotas para la Tarjeta de Crédito:");
                int cuotas = Integer.parseInt(scanner.nextLine());
                medioDePago = new TarjetaCredito(cuotas);
            } else {
                medioDePago = new TarjetaDebito();
            }

            Venta venta = new Venta(cliente, medioDePago);


            System.out.println("Ingrese la cantidad de entradas que comprará este cliente:");
            int cantidadEntradas = Integer.parseInt(scanner.nextLine());
            for (int j = 1; j <= cantidadEntradas; j++) {
                System.out.println("Seleccione la función para la entrada " + j + " (1 - " + teatro.getFunciones().size() + "):");
                int funcionSeleccionada = Integer.parseInt(scanner.nextLine()) - 1;

                System.out.println("Seleccione el tipo de lugar para la entrada " + j + ":");
                System.out.println("1: Platea");
                System.out.println("2: Palco Alto");
                System.out.println("3: Palco Bajo");
                System.out.println("4: Cazuela");
                System.out.println("5: Tertulia");
                System.out.println("6: Paraíso");
                
                int tipoLugar = Integer.parseInt(scanner.nextLine());
                Lugar lugar;
                switch (tipoLugar) {
                    case 1 -> lugar = new Platea(1);
                    case 2 -> lugar = new PalcoAlto(10);
                    case 3 -> lugar = new PalcoBajo(10);
                    case 4 -> lugar = new Cazuela(10);
                    case 5 -> lugar = new Tertulia(10);
                    case 6 -> lugar = new Paraiso(10);
                    default -> {
                        System.out.println("Opción de lugar no válida, se asignará Platea por defecto.");
                        lugar = new Platea(10);
                    }
                }
                System.out.println(lugar.getLugaresDisponibles());

                EntradaSimple entrada = new EntradaSimple(teatro.getFunciones().get(funcionSeleccionada), lugar);
                venta.agregarEntrada(entrada);
            }

            System.out.println("Cliente " + (i + 1) + " - Precio final con " + medioDePago.getClass().getSimpleName() + ": " + venta.calcularPrecioFinal());
            venta.confirmar();
        }

        System.out.println("Detalles de compras realizadas:");
        clientes.forEach(Main::printEntradasCompradasPorCliente);

        scanner.close();
    }

    private static void printEntradasCompradasPorCliente(Cliente cliente) {
        System.out.println("Entradas compradas por " + cliente.getNombre() + " " + cliente.getApellido() + ":");
        cliente.getCompras().forEach(venta -> {
            System.out.println("  Venta con " + venta.getMedioPago().getClass().getSimpleName());
            venta.getEntradas().forEach(Entrada::detalleEntrada);
        });
    }
}
