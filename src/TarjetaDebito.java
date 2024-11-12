public class TarjetaDebito extends Tarjeta {
    @Override
    public double calcularPrecio(double total) {
        return total;
    }

    @Override
    public double getRecargo() {
        return 0;
    }
}