public class Paraiso extends Lugar {
    private final double costoAdicional = 190.75;
    private final String nombre = "Paraiso";

    @Override
    public double getCostoAdicional() {
        return this.costoAdicional;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
}
