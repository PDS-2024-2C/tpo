public class TarjetaCredito extends Tarjeta {
    protected static double recargo = 20.0;

    public TarjetaCredito() {
        super(TarjetaCredito.recargo);
    }

    @Override
    public double calcularPrecio(double total) {
        return total * (1 + TarjetaCredito.recargo / 100);
    }
}