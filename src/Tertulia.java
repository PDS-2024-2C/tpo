public class Tertulia extends Lugar {
    private final double costoAdicional = 50.2;
    private final String nombre = "Tertulia";

    public Tertulia(int cantidadDisponible) {
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
