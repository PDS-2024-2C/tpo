public class Platea extends Lugar {
    private final double costoAdicional = 100.9;
    private final String nombre = "Platea";

    public Platea(int cantidadDisponible) {
        super(cantidadDisponible);
    }

    @Override
    public double getCostoAdicional() {
        return this.costoAdicional;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
}
