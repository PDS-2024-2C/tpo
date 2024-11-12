import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Venta {
    private List<Entrada> entradas = new ArrayList<Entrada>();
    private Cliente cliente;
    private MedioDePago medioPago;
    private static DecimalFormat df = new DecimalFormat("#.00");

    public Venta(Cliente cliente, MedioDePago medioPago) {
        this.cliente = cliente;
        this.medioPago = medioPago;
    }

    public String calcularPrecioFinal() {
        double total = 0;
        for (Entrada entrada : entradas) {
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
        return entradas;
    }

    public void agregarEntrada(Entrada nuevaEntrada) {
        entradas.add(nuevaEntrada);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public MedioDePago getMedioPago() {
        return medioPago;
    }
}