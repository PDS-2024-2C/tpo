import java.util.Date;

public class Main {
    public static void main(String[] args) {
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

        Lugar platea = new Lugar("Platea", 100);
        Lugar vip = new Lugar("VIP", 200);
        Lugar galeria = new Lugar("Galería", 50);

        Cliente cliente1 = new Cliente("Carlos", "López");
        Cliente cliente2 = new Cliente("Mariana", "Rojas");
        Cliente cliente3 = new Cliente("Diego", "Fernandez");

        EntradaSimple entrada1Cliente1 = new EntradaSimple(funcion1, platea);
        EntradaSimple entrada2Cliente1 = new EntradaSimple(funcion2, vip);

        PaqueteEntrada paqueteCliente1 = new PaqueteEntrada();
        paqueteCliente1.agregarEntrada(entrada1Cliente1);
        paqueteCliente1.agregarEntrada(entrada2Cliente1);

        Venta ventaCliente1 = new Venta(cliente1, new Efectivo(10));
        ventaCliente1.agregarEntrada(entrada1Cliente1);
        ventaCliente1.agregarEntrada(paqueteCliente1);
        System.out.println("Cliente 1 - Precio final con Efectivo: " + ventaCliente1.calcularPrecioFinal());

        ventaCliente1.cambiarMedioDePago(new TarjetaCredito(20)); // Recargo del 20
        System.out.println("Cliente 1 - Precio final con Tarjeta de Crédito: " + ventaCliente1.calcularPrecioFinal());
        ventaCliente1.confirmar();

        EntradaSimple entrada1Cliente2 = new EntradaSimple(funcion3, galeria);
        EntradaSimple entrada2Cliente2 = new EntradaSimple(funcion4, vip);

        Venta ventaCliente2 = new Venta(cliente2, new TarjetaDebito());
        ventaCliente2.agregarEntrada(entrada1Cliente2);
        ventaCliente2.agregarEntrada(entrada2Cliente2);
        System.out.println("Cliente 2 - Precio final con Tarjeta de Débito: " + ventaCliente2.calcularPrecioFinal());
        ventaCliente2.confirmar();

        EntradaSimple entrada1Cliente3 = new EntradaSimple(funcion1, galeria);
        EntradaSimple entrada2Cliente3 = new EntradaSimple(funcion2, platea);

        PaqueteEntrada paqueteCliente3 = new PaqueteEntrada();
        paqueteCliente3.agregarEntrada(entrada1Cliente3);
        paqueteCliente3.agregarEntrada(entrada2Cliente3);

        Venta ventaCliente3 = new Venta(cliente3, new Efectivo(10));
        ventaCliente3.agregarEntrada(paqueteCliente3);
        System.out.println("Cliente 3 - Precio final con Efectivo: " + ventaCliente3.calcularPrecioFinal());
        ventaCliente3.confirmar();

        System.out.println("Cliente 1 - Compras realizadas: " + cliente1.getCompras().size());
        System.out.println("Cliente 2 - Compras realizadas: " + cliente2.getCompras().size());
        System.out.println("Cliente 3 - Compras realizadas: " + cliente3.getCompras().size());

        Venta nuevaVentaCliente3 = new Venta(cliente3, new Efectivo(10));
        nuevaVentaCliente3.agregarEntrada(entrada1Cliente3);
        nuevaVentaCliente3.confirmar();
        System.out.println("Cliente 3 - Nueva Compra Precio final con Efectivo: " + nuevaVentaCliente3.calcularPrecioFinal());
        System.out.println("Cliente 3 - Compras realizadas: " + cliente3.getCompras().size());


        System.out.println("Detalles de compras realizadas:");
        printEntradasCompradasPorCliente(cliente1);
        printEntradasCompradasPorCliente(cliente2);
        printEntradasCompradasPorCliente(cliente3);
    }
    private static void printEntradasCompradasPorCliente(Cliente cliente) {
        System.out.println("Entradas compradas por " + cliente.getNombre() + " " + cliente.getApellido() + ":");
        cliente.getCompras().forEach(venta -> {
            System.out.println("  Venta con " + venta.getMedioPago().getClass().getSimpleName());
            venta.getEntradas().forEach(Entrada::detalleEntrada);
        });
    }
}