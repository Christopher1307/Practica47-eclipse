package nulidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Inicio {

	public static void main(String[] args) {
		List<Proveedores> proveedores = ObtenerDatos();	
		
		for(Proveedores proveedor: proveedores){
			proveedor.enviarNotificacion();
			proveedor.guardarCarta("nulidad 2024/05/1");					
		}
		
		limpiarArchivos();
	}
	
	private static List<Proveedores> ObtenerDatos(){
		List<Proveedores> proveedores = new ArrayList<>();
		try(InputStream is = Inicio.class.getResourceAsStream("/Nulidad.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			String linea;
			while((linea = br.readLine()) !=null) {
				String[] partes = linea.split(",");
				String nombre = partes[0];
				String email = partes[1];
				String fax = partes[2];
				List<Pagos> pagos = new ArrayList<>();
				for (int i =3 ; i < partes.length; i +=2) {
					String descripcion = partes[i];
					double monto = Double.parseDouble(partes[i]);
					pagos.add(new Pagos(descripcion, monto));	
				}
				 proveedores.add(new Proveedores(0, nombre, email, fax, 0, pagos, 0));
			
			 	}
			} catch (IOException e) {
				e.printStackTrace();
			
		}
		return proveedores;
	}
	
	 private static void limpiarArchivos() {
	        // Directorio donde se encuentran los archivos a limpiar
	        String directorio = "directorio_a_limpiar";
	        
	        // Crear un objeto File para el directorio
	        File dir = new File(directorio);
	        
	        // Verificar si el directorio existe y es un directorio
	        if (dir.exists() && dir.isDirectory()) {
	            // Obtener una lista de archivos en el directorio
	            File[] archivos = dir.listFiles();
	            
	            // Eliminar cada archivo en el directorio
	            if (archivos != null) {
	                for (File archivo : archivos) {
	                    try {
	                        Files.deleteIfExists(archivo.toPath());
	                        System.out.println("Archivo eliminado: " + archivo.getName());
	                    } catch (IOException e) {
	                        System.err.println("Error al eliminar el archivo: " + archivo.getName());
	                        e.printStackTrace();
	                    }
	                }
	            }
	            
	            // Eliminar el directorio
	            try {
	                Files.deleteIfExists(dir.toPath());
	                System.out.println("Directorio eliminado: " + dir.getName());
	            } catch (IOException e) {
	                System.err.println("Error al eliminar el directorio: " + dir.getName());
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("El directorio no existe o no es un directorio válido.");
	        }
	    }
	}

	
