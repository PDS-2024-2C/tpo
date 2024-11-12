public class Cazuela extends Lugar {
    private final double costoAdicional = 90.0;
    private final String nombre = "Cazuela";

    @Override
    public double getCostoAdicional() {
        return this.costoAdicional;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
}
