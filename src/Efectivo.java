public class Efectivo extends MedioDePago {
    private double descuento = 0;

    Efectivo(double descuento) {
        this.descuento = descuento;
    }
    @Override
    public double calcularPrecio(double total) {
        return total * (1 - this.descuento / 100);
    }
}