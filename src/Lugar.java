public abstract class Lugar {
    private double costoAdicional;
    private String nombre;
    public abstract double getCostoAdicional();
    public abstract String getNombre();
    private int lugaresDisponibles;

    public Lugar(int cantidadDisponible) {
        this.lugaresDisponibles = cantidadDisponible;
    }

    public void usarLugar() {
        if (lugaresDisponibles <= 0) {
            throw new IllegalStateException("No hay más lugares disponibles en " + nombre);
        }
        lugaresDisponibles--;
    }

    public int getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    public void liberarLugar() {
        lugaresDisponibles++;
    }

}