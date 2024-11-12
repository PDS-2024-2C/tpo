public class PalcoBajo extends Lugar {
    private final double costoAdicional = 80.10;
    private final String nombre = "Palco Bajo";

    @Override
    public double getCostoAdicional() {
        return this.costoAdicional;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
}
