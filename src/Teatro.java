import java.util.ArrayList;
import java.util.List;

public class Teatro {
    private String nombre;
    private ArrayList<Funcion> funciones;
    private ArrayList<Obra> obras;

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

    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }

    public ArrayList<Obra> getObras() {
        return obras;
    }

    public ArrayList<Funcion> obtenerFuncionesDeObra(Obra obra) {         
        ArrayList<Funcion> funcionesDeObra = new ArrayList<>();         
        for (Funcion funcion : funciones) 
        {             
            if (funcion.getObra().getNombre().equalsIgnoreCase(obra.getNombre())) { 
                funcionesDeObra.add(funcion); } } 
            return funcionesDeObra; 
        }
}