public class Cazuela extends Lugar {
    private final double costoAdicional = 90.0;
    private final String nombre = "Cazuela";

    public Cazuela(int cantidadDisponible) {
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
