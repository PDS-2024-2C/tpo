import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Venta {
    private PaqueteEntrada entradas = new PaqueteEntrada();
    private Cliente cliente;
    private MedioDePago medioPago;
    private static DecimalFormat df = new DecimalFormat("#.00");

    public Venta(Cliente cliente, MedioDePago medioPago) {
        this.cliente = cliente;
        this.medioPago = medioPago;
    }

    public String calcularPrecioFinal() {
        double total = 0;
        for (Entrada entrada : entradas.getEntradas()) {
            total += entrada.total();
        }
        return Venta.df.format(medioPago.calcularPrecio(total));
    }

    public void cambiarMedioDePago(MedioDePago nuevoMedio) {
        this.medioPago = nuevoMedio;
    }

    public void confirmar() {
        cliente.efectuarPago(this);
    }

    public List<Entrada> getEntradas() {
        return entradas.getEntradas();
    }

    public int getCantidadEntradas() {
        return entradas.getCantidadEntradas();
    }

    public void agregarEntrada(Entrada nuevaEntrada) {
        entradas.agregarEntrada(nuevaEntrada);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public MedioDePago getMedioPago() {
        return medioPago;
    }
}