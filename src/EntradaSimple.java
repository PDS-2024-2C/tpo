public class EntradaSimple extends Entrada {
    private Funcion funcion;
    private Lugar lugar;

    public EntradaSimple(Funcion funcion, Lugar lugar) {
        this.funcion = funcion;
        this.lugar = lugar;
    }

    @Override
    public double total() {
        return lugar.getCostoAdicional() + funcion.getPrecio();
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public Lugar getLugar() {
        return lugar;
    }
    @Override
    public void detalleEntrada() {
        System.out.println("    Funcion: " + funcion.getObra().getNombre() +
                ", Lugar: " + lugar.getUbicacion() +
                ", Precio: " + total());
    }
}