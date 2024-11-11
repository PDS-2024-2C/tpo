public class Efectivo extends MedioDePago {
    private static double descuento = 10;

    @Override
    public double calcularPrecio(double total) {
        return total * (1 - Efectivo.descuento / 100);
    }
}