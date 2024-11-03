public class TarjetaDebito extends Tarjeta {
    public TarjetaDebito() {
        super(0); // Defaults to no surcharge
    }

    @Override
    public double calcularPrecio(double total) {
        return total;
    }
}