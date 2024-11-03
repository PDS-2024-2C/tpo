public class Lugar {
    private String ubicacion;
    private double costoAdicional;

    public Lugar(String ubicacion, double costoAdicional) {
        this.ubicacion = ubicacion;
        this.costoAdicional = costoAdicional;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }
}