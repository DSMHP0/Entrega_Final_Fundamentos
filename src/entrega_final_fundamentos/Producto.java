public class Producto {
    private int idProducto;
    private String nombreProducto;
    private double precioPorUnidad;
    private int cantidadVendida;

    public Producto(int idProducto, String nombreProducto, double precioPorUnidad) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioPorUnidad = precioPorUnidad;
        this.cantidadVendida = 0;
    }

    public void agregarCantidadVendida(int cantidad) {
        this.cantidadVendida += cantidad;
    }

    public double calcularTotalVendido() {
        return this.cantidadVendida * this.precioPorUnidad;
    }

    public String getFormatoReporte() {
        return this.nombreProducto + ";" + this.cantidadVendida + ";" + calcularTotalVendido();
    }
}
