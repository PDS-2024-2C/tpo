public class TarjetaCredito extends Tarjeta {
    public TarjetaCredito(double recargo) {
        super(recargo);
    }

    @Override
    public double calcularPrecio(double total) {
        return total * (1 + recargo / 100);
    }
}