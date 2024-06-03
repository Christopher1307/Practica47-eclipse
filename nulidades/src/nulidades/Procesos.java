package nulidades;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Procesos {

    public static void procesarNulidades() {
        String inputFilePath = "C:\\Users\\Usuario\\eclipse-workspace\\nulidades\\src\\nulidades\\Nulidad.txt";
        String plantillaPath1 = "C:\\Users\\Usuario\\eclipse-workspace\\nulidades\\src\\nulidades\\Modelo carta empresa 1.txt";
        String plantillaPath2 = "C:\\Users\\Usuario\\eclipse-workspace\\nulidades\\src\\nulidades\\Modelo carta empresa 2.txt";
        String outputPath = "salida";
        String correoEmpresaNulidad = "correo@empresa.com";
        String nombreRemitente = "Nombre del remitente";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la empresa: ");
        String nombreEmpresaBuscada = scanner.nextLine().trim();

        List<Proveedor> proveedores = leerArchivoProveedores(inputFilePath);
        List<Proveedor> proveedoresFiltrados = proveedores.stream()
                .filter(proveedor -> proveedor.getNombreEmpresa().equalsIgnoreCase(nombreEmpresaBuscada))
                .collect(Collectors.toList());

        if (proveedoresFiltrados.isEmpty()) {
            System.out.println("No se encontraron proveedores con el nombre de empresa especificado.");
            return;
        }

        limpiarDirectorio(new File(outputPath));

        Proveedor proveedor = proveedoresFiltrados.get(0); // Tomar solo el primer proveedor filtrado
        String contenidoCarta1 = generarCarta(plantillaPath1, proveedor, correoEmpresaNulidad, nombreRemitente);
        String contenidoCarta2 = generarCarta(plantillaPath2, proveedor, correoEmpresaNulidad, nombreRemitente);

        // Mostrar solo una de las dos cartas, en este caso usaremos contenidoCarta1
        guardarCarta(outputPath, proveedor, contenidoCarta1);

        if (proveedor.getEmail() != null && !proveedor.getEmail().isEmpty()) {
            String[] emails = proveedor.getEmail().split(";");
            for (String email : emails) {
                enviarEmail(email, contenidoCarta1, proveedor);
            }
        }
    }

    private static List<Proveedor> leerArchivoProveedores(String filePath) {
        List<Proveedor> proveedores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\t");
                if (fields.length >= 8) {
                    proveedores.add(construirProveedor(fields));
                } else {
                    System.out.println("Formato de línea incorrecto: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    private static Proveedor construirProveedor(String[] fields) {
        String id = fields[0];
        String nombreEmpresa = fields[1];
        String monto = fields[2];
        String fecha = fields[3];
        String cif = fields[4];
        String codigoNulidad = fields[5];
        String referencia = fields[6];
        String email = fields.length > 7 ? fields[7] : "";
        String contacto = fields.length > 8 ? fields[8] : "";

        return new Proveedor(id, nombreEmpresa, monto, fecha, cif, codigoNulidad, referencia, email, contacto);
    }

    private static String generarCarta(String plantillaPath, Proveedor proveedor, String correoEmpresaNulidad, String nombreRemitente) {
        StringBuilder carta = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(plantillaPath));
            for (String line : lines) {
                carta.append(line.replace("{nombre_empresa}", proveedor.getNombreEmpresa())
                                .replace("{total_servicios}", proveedor.getMonto())
                                .replace("{fecha}", proveedor.getFecha())
                                .replace("{cif}", proveedor.getCif())
                                .replace("{Numero_nulidad}", proveedor.getCodigoNulidad())
                                .replace("{referencia}", proveedor.getReferencia())
                                .replace("{Nombre_cliente}", proveedor.getContacto())
                                .replace("{correo_empresa_nulidad}", correoEmpresaNulidad)
                                .replace("{Su_nombre}", nombreRemitente))
                     .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carta.toString();
    }

    private static void guardarCarta(String outputPath, Proveedor proveedor, String contenidoCarta) {
        try {
            Files.createDirectories(Paths.get(outputPath));
            String fileName = outputPath + "/" + proveedor.getNombreEmpresa() + "_carta.txt";
            Files.write(Paths.get(fileName), contenidoCarta.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarEmail(String email, String contenido, Proveedor proveedor) {
        // Implementar la lógica de envío de email aquí
        System.out.println("Enviando email a: " + email + " con el contenido: " + contenido);
    }

    private static void limpiarDirectorio(File directorio) {
        if (directorio.isDirectory()) {
            for (File file : directorio.listFiles()) {
                if (file.isDirectory()) {
                    limpiarDirectorio(file);
                }
                file.delete();
            }
        }
    }
}

