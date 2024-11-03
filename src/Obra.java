public class Obra {
    private String nombre;
    private double duracion;
    private GrupoDeActores grupoDeActores;
    private String categoria;

    public Obra(String nombre) {
        this.nombre = nombre;
    }

    public Obra(String nombre, double duracion, GrupoDeActores grupoDeActores, String categoria) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.grupoDeActores = grupoDeActores;
        this.categoria = categoria;
    }

    public void asignarGrupo(GrupoDeActores grupo) {
        this.grupoDeActores = grupo;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public Funcion cargarFuncion() {
        return new Funcion();
    }

    public String getNombre() {
        return nombre;
    }

    public double getDuracion() {
        return duracion;
    }

    public GrupoDeActores getGrupoDeActores() {
        return grupoDeActores;
    }

    public String getCategoria() {
        return categoria;
    }
}