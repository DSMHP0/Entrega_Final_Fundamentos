import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase GenerateInfoFiles
 * Esta clase genera archivos de entrada pseudoaleatorios que servirán para el procesamiento de ventas.
 */
public class GenerateInfoFiles {
    public static void main(String[] args) {
        try {
            generarArchivoVendedores("vendedores.txt");
            generarArchivoProductos("productos.txt");
            generarArchivoVentas("ventas.txt");
            System.out.println("Archivos generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al generar los archivos: " + e.getMessage());
        }
    }

    // Método para generar el archivo de vendedores con datos aleatorios
    private static void generarArchivoVendedores(String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (int i = 1; i <= 10; i++) {
                String tipoDocumento = "CC";
                long numeroDocumento = 1000000 + i;
                String nombre = "Vendedor" + i;
                String apellido = "Apellido" + i;
                writer.write(tipoDocumento + ";" + numeroDocumento + ";" + nombre + ";" + apellido);
                writer.newLine();
            }
        }
    }

    // Método para generar el archivo de productos con datos aleatorios
    private static void generarArchivoProductos(String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            Random random = new Random();
            for (int i = 1; i <= 5; i++) {
                int idProducto = i;
                String nombreProducto = "Producto" + i;
                double precioPorUnidad = 50 + (1000 * random.nextDouble());
                writer.write(idProducto + ";" + nombreProducto + ";" + String.format("%.2f", precioPorUnidad));
                writer.newLine();
            }
        }
    }

    // Método para generar el archivo de ventas con datos aleatorios
    private static void generarArchivoVentas(String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            Random random = new Random();
            for (int i = 1; i <= 20; i++) {
                int idProducto = 1 + random.nextInt(5); // Genera ID de producto entre 1 y 5
                int cantidadVendida = 1 + random.nextInt(10); // Genera cantidad vendida entre 1 y 10
                writer.write(idProducto + ";" + cantidadVendida);
                writer.newLine();
            }
        }
    }
}
