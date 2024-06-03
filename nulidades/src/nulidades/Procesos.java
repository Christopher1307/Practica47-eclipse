package nulidades;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Procesos {

    static void procesarNulidades() {
        String inputFilePath = "C:\\Users\\Usuario\\eclipse-workspace\\nulidades\\src\\nulidades\\Nulidad.txt";
        String plantillaPath1 = "C:\\Users\\Usuario\\eclipse-workspace\\nulidades\\src\\nulidades\\Modelo carta empresa 1.txt";
        String plantillaPath2 = "C:\\Users\\Usuario\\eclipse-workspace\\nulidades\\src\\nulidades\\Modelo carta empresa 2.txt";
        String outputPath = "salida";
        String correoEmpresaNulidad = "correo@empresa.com";
        String nombreRemitente = "Nombre del remitente";

        List<Proveedor> proveedores = leerArchivoProveedores(inputFilePath);

        for (Proveedor proveedor : proveedores) {
            String contenidoCarta1 = generarCarta(plantillaPath1, proveedor, correoEmpresaNulidad, nombreRemitente);
            String contenidoCarta2 = generarCarta(plantillaPath2, proveedor, correoEmpresaNulidad, nombreRemitente);
            guardarCarta(outputPath, proveedor, contenidoCarta1, contenidoCarta2);

            if (proveedor.email != null && !proveedor.email.isEmpty()) {
                String[] emails = proveedor.email.split(";");
                for (String email : emails) {
                	enviarNotificacion (email, contenidoCarta1, proveedor);
                	enviarNotificacion (email, contenidoCarta2, proveedor);
                }
            }
        }
    }

    private static List<Proveedor> leerArchivoProveedores(String filePath) {
        List<Proveedor> proveedores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\t");
                if (fields.length >= 9) {
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
                carta.append(line.replace("{empresa}", proveedor.nombreEmpresa)
                                .replace("{monto}", proveedor.monto)
                                .replace("{fecha}", proveedor.fecha)
                                .replace("{cif}", proveedor.cif)
                                .replace("{codigoNulidad}", proveedor.codigoNulidad)
                                .replace("{referencia}", proveedor.referencia)
                                .replace("{contacto}", proveedor.contacto)
                                .replace("{correoEmpresaNulidad}", correoEmpresaNulidad)
                                .replace("{nombreRemitente}", nombreRemitente))
                     .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carta.toString();
    }

    private static void guardarCarta(String outputPath, Proveedor proveedor, String contenidoCarta1, String contenidoCarta2) {
        try {
            Files.createDirectories(Paths.get(outputPath));
            String fileName1 = outputPath + "/" + proveedor.nombreEmpresa + "_carta1.txt";
            String fileName2 = outputPath + "/" + proveedor.nombreEmpresa + "_carta2.txt";
            Files.write(Paths.get(fileName1), contenidoCarta1.getBytes());
            Files.write(Paths.get(fileName2), contenidoCarta2.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarNotificacion(String destinatario, String asunto, Proveedor proveedor) {
        // Simulación de envío de notificación por correo electrónico
        System.out.println("Enviando correo electrónico a: " + destinatario);
        System.out.println("Asunto: " + asunto);
        System.out.println("Cuerpo:\n" + proveedor);
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
