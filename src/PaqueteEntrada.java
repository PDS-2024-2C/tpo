import java.util.ArrayList;
import java.util.List;

public class PaqueteEntrada extends Entrada {
    private List<Entrada> entradas = new ArrayList<Entrada>();

    public void agregarEntrada(Entrada entrada) {
        entradas.add(entrada);
    }

    public void eliminarEntrada(Entrada entrada) {
        entradas.remove(entrada);
    }

    @Override
    public double total() {
        double total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.total();
        }
        return total;
    }

    public int getCantidadEntradas() {
        return entradas.size();
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    @Override
    public void detalleEntrada() {
        System.out.println("    Paquete de Entradas:");
        entradas.forEach(Entrada::detalleEntrada);
    }

    @Override
    public void confirmarLugar() {
        entradas.forEach(Entrada::confirmarLugar);
    }
}