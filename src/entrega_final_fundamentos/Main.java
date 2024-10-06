import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase Main
 * Esta clase realiza las tareas de procesamiento de ventas y generación de reportes.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Rutas de los archivos generados
            String rutaVendedores = "vendedores.txt";
            String rutaProductos = "productos.txt";
            String rutaVentas = "ventas.txt";

            // Leer datos desde archivos
            HashMap<Long, Vendedor> vendedores = leerArchivoVendedores(rutaVendedores);
            HashMap<Integer, Producto> productos = leerArchivoProductos(rutaProductos);
            leerArchivoVentas(rutaVentas, vendedores, productos);

            // Generar reportes
            generarReporteVendedores(vendedores, "reporte_vendedores.csv");
            generarReporteProductos(productos, "reporte_productos.csv");

            System.out.println("Reportes generados exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al procesar los archivos: " + e.getMessage());
        }
    }

    // Método para leer el archivo de vendedores
    public static HashMap<Long, Vendedor> leerArchivoVendedores(String rutaArchivo) throws IOException {
        HashMap<Long, Vendedor> vendedores = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                String tipoDocumento = partes[0];
                long numeroDocumento = Long.parseLong(partes[1]);
                String nombre = partes[2];
                String apellido = partes[3];
                Vendedor vendedor = new Vendedor(tipoDocumento, numeroDocumento, nombre, apellido);
                vendedores.put(numeroDocumento, vendedor);
            }
        }
        return vendedores;
    }

    // Método para leer el archivo de productos
    public static HashMap<Integer, Producto> leerArchivoProductos(String rutaArchivo) throws IOException {
        HashMap<Integer, Producto> productos = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                int idProducto = Integer.parseInt(partes[0]);
                String nombreProducto = partes[1];
                double precioPorUnidad = Double.parseDouble(partes[2]);
                Producto producto = new Producto(idProducto, nombreProducto, precioPorUnidad);
                productos.put(idProducto, producto);
            }
        }
        return productos;
    }

    // Método para leer el archivo de ventas
    public static void leerArchivoVentas(String rutaArchivo, HashMap<Long, Vendedor> vendedores, HashMap<Integer, Producto> productos) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                int idProducto = Integer.parseInt(partes[0]);
                int cantidadVendida = Integer.parseInt(partes[1]);

                if (productos.containsKey(idProducto)) {
                    Producto producto = productos.get(idProducto);
                    producto.agregarCantidadVendida(cantidadVendida);
                }
            }
        }
    }

    // Método para generar el reporte de vendedores
    public static void generarReporteVendedores(HashMap<Long, Vendedor> vendedores, String rutaSalida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaSalida))) {
            List<Map.Entry<Long, Vendedor>> listaVendedores = vendedores.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(v -> v.getValue().getTotalVentas()).reversed())
                .collect(Collectors.toList());

            for (Map.Entry<Long, Vendedor> entry : listaVendedores) {
                Vendedor vendedor = entry.getValue();
                writer.write(vendedor.getFormatoReporte());
                writer.newLine();
            }
        }
    }

    // Método para generar el reporte de productos
    public static void generarReporteProductos(HashMap<Integer, Producto> productos, String rutaSalida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaSalida))) {
            List<Producto> listaProductos = productos.values().stream()
                .sorted(Comparator.comparingDouble(Producto::calcularTotalVendido).reversed())
                .collect(Collectors.toList());

            for (Producto producto : listaProductos) {
                writer.write(producto.getFormatoReporte());
                writer.newLine();
            }
        }
    }
}
