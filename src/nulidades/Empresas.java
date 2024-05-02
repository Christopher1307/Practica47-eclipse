package nulidades;
import java.util.List;


public class Empresas {
	private int id;
	private String nombre;
	private String empresa;
	private String cartaAsociada;
	private  List<Proveedores> proveedores;
	
public Empresas (int id , String nombre, String empresa, String cartaAsociada, List<Proveedores> proveedores ) {
	this.id = id;
	this.nombre = nombre;
	this.empresa = empresa;
	this.cartaAsociada = cartaAsociada;
	this.proveedores = proveedores;
	
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

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getEmpresa() {
	return empresa;
}

public void setEmpresa(String empresa) {
	this.empresa = empresa;
}

public String getCartaAsociada() {
	return cartaAsociada;
}

public void setCartaAsociada(String cartaAsociada) {
	this.cartaAsociada = cartaAsociada;
}

public List<Proveedores> getProveedores(){
	return proveedores;
}

public void setProveedores(List<Proveedores> proveedores) {
    this.proveedores = proveedores;
}


public void agregarProveedor(Proveedores proveedor) {
    this.proveedores.add(proveedor);
	}

}

