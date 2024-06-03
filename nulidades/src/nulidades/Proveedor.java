package nulidades;

public class Proveedor {
    String id;
    String nombreEmpresa;
    String monto;
    String fecha;
    String cif;
    String codigoNulidad;
    String referencia;
    String email;
    String contacto;

    public Proveedor(String id, String nombreEmpresa, String monto, String fecha, String cif, String codigoNulidad, String referencia, String email, String contacto) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.monto = monto;
        this.fecha = fecha;
        this.cif = cif;
        this.codigoNulidad = codigoNulidad;
        this.referencia = referencia;
        this.email = email;
        this.contacto = contacto;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getCodigoNulidad() {
		return codigoNulidad;
	}

	public void setCodigoNulidad(String codigoNulidad) {
		this.codigoNulidad = codigoNulidad;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

    // Getters and setters (optional)
    
}
