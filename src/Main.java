import java.util.Date;
import java.util.List;
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
        

        Cliente cliente = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("tomas", "tomas@gmail.com"));
        clientes.add(new Cliente("federico", "federicomartos@gmail.com"));
        
        System.out.println("Bienvenido! \n ingrese 1 para registrarse / ingrese 2 para logearse )");
        if (scanner.nextInt() == 1) {
            System.out.println("Ingrese su nombre: ");
            String nombre = scanner.next();
            System.out.println("Ingrese su email: ");
            String email = scanner.next();
            cliente = new Cliente(nombre, email); 
            clientes.add(cliente);
            System.out.println();

        }
        else {
            System.out.println("Ingrese su email: ");
            String email = scanner.next();
            for (Cliente clienteAux : clientes) {     
                if (clienteAux.getEmail().equalsIgnoreCase(email)) 
                    cliente = clienteAux;
                }
        }


        if (cliente != null) {
            
       

        System.out.println("Obras disponibles: ");

        ArrayList<Obra> obras = teatro.getObras();   
        for(int i = 0; i < obras.size(); i++) {
            Obra obra = obras.get(i);
            System.out.println(i + ". " + obra.getNombre());
        }

        int inputObra  = scanner.nextInt();


        ArrayList<Funcion> funcionesObra = null;


        int inputFuncion = 0;
        if (obras.get(inputObra) != null) {
            funcionesObra = teatro.obtenerFuncionesDeObra(obras.get(inputObra));

            for(int i = 0; i < funcionesObra.size(); i++) {
                Funcion funcion = funcionesObra.get(i);
                System.out.println(i + ". " + funcion.getFecha() + "--- " + " $" + funcion.getPrecio());
            }
    
            inputFuncion = scanner.nextInt();

        }



        if (funcionesObra == null) {
            System.out.println("no hay funciones disponibles");
            scanner.close();
            return;
        }

        PaqueteEntrada entradas = new PaqueteEntrada();
        
        System.out.println("Ingrese la cantidad de entradas que comprará este cliente:");
        int cantidadEntradas = scanner.nextInt();


            for (int j = 1; j <= cantidadEntradas; j++) {
            
                System.out.println("Seleccione el tipo de lugar para la entrada " + j + ":");
                System.out.println("1: Platea");
                System.out.println("2: Palco Alto");
                System.out.println("3: Palco Bajo");
                System.out.println("4: Cazuela");
                System.out.println("5: Tertulia");
                System.out.println("6: Paraíso");
                
                int tipoLugar = scanner.nextInt();
                Lugar lugar;
                switch (tipoLugar) {
                    case 1 -> lugar = new Platea();
                    case 2 -> lugar = new PalcoAlto();
                    case 3 -> lugar = new PalcoBajo();
                    case 4 -> lugar = new Cazuela();
                    case 5 -> lugar = new Tertulia();
                    case 6 -> lugar = new Paraiso();
                    default -> {
                        System.out.println("Opción de lugar no válida, se asignará Platea por defecto.");
                        lugar = new Platea();
                    }
                }


                EntradaSimple entrada = new EntradaSimple(funcionesObra.get(inputFuncion), lugar);

                entradas.agregarEntrada(entrada);
            }

            System.out.println("Ingrese el medio de pago (1: Efectivo, 2: Tarjeta Crédito, 3: Tarjeta Débito):");
            int opcionPago = scanner.nextInt();
            MedioDePago medioDePago;
            if (opcionPago == 1) {
                medioDePago = new Efectivo();
            } else if (opcionPago == 2) {
                System.out.println("Ingrese la cantidad de cuotas para la Tarjeta de Crédito (2-3-6):");
                int cuotas = scanner.nextInt();
                medioDePago = new TarjetaCredito(cuotas);
            } else {
                medioDePago = new TarjetaDebito();
            }

            
            Venta venta = new Venta(cliente, medioDePago);
            venta.agregarEntrada(entradas);

            

            System.out.println("Precio final con " + medioDePago.getClass().getSimpleName() + ": " + venta.calcularPrecioFinal());
            venta.confirmar();
            printEntradasCompradasPorCliente(cliente, entradas.getCantidadEntradas());
            scanner.close();
        }

    }

    
    

    private static void printEntradasCompradasPorCliente(Cliente cliente, int cantidadEntradas) {
        System.out.println("Entradas compradas por " + cliente.getNombre() + " " + cliente.getEmail() + ":");

        for(Venta venta:cliente.getCompras()) {
            System.out.println("  Venta con " + venta.getMedioPago().getClass().getSimpleName());
            System.out.println("Cantidad de entradas adquiridas: " + cantidadEntradas);

        }


           
    
    }
}


