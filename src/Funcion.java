import java.util.Date;
import java.util.HashMap;

public class Funcion {
    private Date fecha;
    private double precio;
    private Obra obra;
    private HashMap<Lugar, Integer> lugaresDisponibles = new HashMap<>();

    public Funcion() {}

    public Funcion(Date fecha, double precio, Obra obra) {
        this.fecha = fecha;
        this.precio = precio;
        this.obra = obra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    private double calcularRecargoPorDuracion() {
        // Es un recargo por el uso del teatro. A mayor duración, mayor recargo.
    	return this.obra.getDuracion() * 0.05;
    }
    
    public void setPrecio(double precioBase) {
        this.precio = precioBase + this.calcularRecargoPorDuracion();
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public void agregarLugar(Lugar lugar, int cantidad) {
        lugaresDisponibles.put(lugar, cantidad);
    }

    public HashMap<Lugar, Integer> getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    public boolean ocuparLugar(Lugar lugar) {
        int disponibles = lugaresDisponibles.getOrDefault(lugar, 0);
        if (disponibles > 0) {
            lugaresDisponibles.put(lugar, disponibles - 1);
            return true;
        }
        return false;
    }
}