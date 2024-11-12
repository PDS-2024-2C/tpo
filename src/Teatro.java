import java.util.ArrayList;
import java.util.List;

public class Teatro {
    private String nombre;
    private List<Funcion> funciones;
    private List<Obra> obras;

    private static Teatro instance;

    private Teatro(String nombre) {
        this.nombre = nombre;
        this.funciones = new ArrayList<Funcion>();
        this.obras = new ArrayList<Obra>();
    }

    public static Teatro getInstance(String nombre) {
        if (instance == null) {
            instance = new Teatro(nombre);
        }
        return instance;
    }

    public Funcion cargarFuncion(Obra obra) {
        Funcion funcion = new Funcion();
        funcion.setObra(obra);
        funciones.add(funcion);
        return funcion;
    }

    public Obra cargarObra(String nombre) {
        Obra obra = new Obra(nombre);
        obras.add(obra);
        return obra;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public List<Obra> getObras() {
        return obras;
    }
}