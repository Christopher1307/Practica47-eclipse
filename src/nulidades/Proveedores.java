package nulidades;

import java.util.ArrayList;
import java.util.List;

public class Proveedores {
	private int id;
	private String nombre;
	private String email;
	private String personal;
	private int fax;
	private List<String> pagos;
	double total;
	
public Proveedores (int id , String nombre,  String email,  String personal, int fax, List<String> pagos, double total){
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

public List<String> getPagos() {
return pagos;
}

public void setPagos(List<String> pagos) {
    this.pagos = pagos;
}

public double getTotal() {
    return total;
}

public void setTotal(double total) {
    this.total = total;

}




}
