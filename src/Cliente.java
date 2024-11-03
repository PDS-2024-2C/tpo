import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String apellido;
    private List<Venta> compras = new ArrayList<Venta>();

    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void efectuarPago(Venta venta) {
        compras.add(venta);
    }

    public Venta comprar() {
        return compras.isEmpty() ? null : compras.get(compras.size() - 1);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Venta> getCompras() {
        return compras;
    }
}