import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String email;
    private List<Venta> compras = new ArrayList<Venta>();

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Venta> getCompras() {
        return compras;
    }
}