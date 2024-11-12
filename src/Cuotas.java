public enum Cuotas {
    DOS(10.0),
    TRES(15.3),
    SEIS(23.2);

    private final double valor;

    Cuotas(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }
}
