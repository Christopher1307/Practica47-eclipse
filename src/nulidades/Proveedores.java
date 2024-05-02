package nulidades;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Proveedores {
	private int id;
	private String nombre;
	private String email;
	private String personal;
	private int fax;
	private List<Pagos> pagos;
	double total;
	
public Proveedores (int id , String nombre,  String email,  String personal, int fax, List<Pagos> pagos, double total){
	this.id = id;
	this.nombre = nombre;
	this.email = email;
	this.personal = personal;
	this.fax = fax;
	this.pagos = pagos;
	this.total = total;
	

}
	
//getters y setters
public int getId() {
return id;

}

public void setId(int id) {
	this.id = id;
}

public String getNombre() {
return nombre;

}

public void setId(String nombre) {
	this.nombre = nombre;
}

public String getPersonal() {
return personal;

}

public void setPersonal(String personal) {
	this.personal = personal;
}

public int getFax() {
return fax;

}

public void setFax(int fax) {
	this.fax = fax;
}

public List<Pagos> getPagos() {
return pagos;
}

public void setPagos(List<Pagos> pagos) {
    this.pagos = pagos;
}

public double getTotal() {
    return total;
}

public void setTotal(double total) {
    this.total = total;

}

public double calcularTotal() {
	double total = 0;
	for (Pagos pago:pagos) {
		total += pago.getMonto();
	}
	return total;
	
}

public String generarCarta() {
	StringBuilder carta = new StringBuilder();
	carta.append("Estimado o Estimada").append(nombre).append(",\n\n");
	carta.append("le informamos que hemos procesado los siguientes pagos bajo el expendiente de esta nulidad:\n");
	for (Pagos pago:pagos) {
		carta.append("- ").append(pago.getDescripcion()).append(": ").append(pago.getMonto()).append("€\n");	
	}
	carta.append("\nEl total a pagar es: ").append(calcularTotal()).append("€\n\n");
    carta.append("Por favor, confirme la recepción de este correo electrónico.\n\n");
    carta.append("Atentamente,\n");
    carta.append("Nombre de la entidad gubernamental\n");
	return carta.toString();
}

public void enviarNotificacion() {
    String carta = generarCarta();
    enviarEmail(carta);
    enviarFax(carta);
}

private void enviarEmail(String carta) {
    System.out.println("Enviando correo electrónico a: " + email);
    System.out.println("Contenido del correo electrónico:\n" + carta);
    // Aquí iría la lógica real para enviar el correo electrónico
}

private void enviarFax(String carta) {
    System.out.println("Enviando fax a: " + fax);
    System.out.println("Contenido del fax:\n" + carta);
    // Aquí iría la lógica real para enviar el fax
}

public void guardarCarta(String codigoNulidad) {
    String nombreArchivo = "Carta_" + codigoNulidad + "_" + nombre.replace(" ", "_") + ".txt";
    // Aquí iría la lógica para guardar la carta en un archivo
    System.out.println("Guardando carta en archivo: " + nombreArchivo);
}

public void limpiarArchivos() {
    // Aquí iría la lógica para limpiar archivos y directorios relacionados
    System.out.println("Limpiando archivos y directorios relacionados.");

	}

@SuppressWarnings("unused")
private String obtenerModeloCarta1() {
    return obtenerContenidoModelo("/Modelo carta empresa 1.txt");
}

@SuppressWarnings("unused")
private String obtenerModeloCarta2() {
    return obtenerContenidoModelo("/Modelo carta empresa 2.txt");
}

private String obtenerContenidoModelo(String nombreArchivo) {
    StringBuilder contenidoModelo = new StringBuilder();
    try (InputStream is = getClass().getResourceAsStream(nombreArchivo);
         BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            contenidoModelo.append(linea).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return contenidoModelo.toString();
	}
}
	






