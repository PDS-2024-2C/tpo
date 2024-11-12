public class TarjetaCredito extends Tarjeta {
    protected double recargo;

    public TarjetaCredito(int cantCuotas) {
        if(cantCuotas == 2) { this.recargo = Cuotas.DOS.getValor(); };
        if(cantCuotas == 3) { this.recargo = Cuotas.TRES.getValor(); };
        if(cantCuotas == 6) { this.recargo = Cuotas.SEIS.getValor(); };
    }

    @Override
    public double calcularPrecio(double total) {
        return total * (1 + this.recargo / 100);
    }

    @Override
    public double getRecargo() {
        return this.recargo;
    }
}