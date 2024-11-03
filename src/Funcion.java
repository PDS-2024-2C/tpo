import java.util.Date;

public class Funcion {
    private Date fecha;
    private double precio;
    private Obra obra;

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

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
}