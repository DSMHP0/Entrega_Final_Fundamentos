public class Vendedor {
    private String tipoDocumento;
    private long numeroDocumento;
    private String nombre;
    private String apellido;
    private double totalVentas;

    public Vendedor(String tipoDocumento, long numeroDocumento, String nombre, String apellido) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.totalVentas = 0.0;
    }

    public void agregarVenta(double monto) {
        this.totalVentas += monto;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    public double getTotalVentas() {
        return this.totalVentas;
    }

    public String getFormatoReporte() {
        return getNombreCompleto() + ";" + this.totalVentas;
    }
}
