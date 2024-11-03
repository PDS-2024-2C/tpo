import java.util.ArrayList;
import java.util.List;

public class GrupoDeActores {
    private List<Actor> integrantes = new ArrayList<>();

    public void agregarActor(Actor actor) {
        integrantes.add(actor);
    }

    public void quitarActor(Actor actor) {
        integrantes.remove(actor);
    }

    public List<Actor> getIntegrantes() {
        return integrantes;
    }
}