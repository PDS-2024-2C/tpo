public class PalcoAlto extends Lugar {
    private final double costoAdicional = 112.3;
    private final String nombre = "Palco Alto";

    public PalcoAlto(int cantidadDisponible) {
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
