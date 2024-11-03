public abstract class Tarjeta extends MedioDePago {
    protected double recargo;

    public Tarjeta(double recargo) {
        this.recargo = recargo;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }
}