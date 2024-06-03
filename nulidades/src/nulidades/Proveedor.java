package nulidades;

public class Proveedor {
    private String id;
    private String nombreEmpresa;
    private String monto;
    private String fecha;
    private String cif;
    private String codigoNulidad;
    private String referencia;
    private String email;
    private String contacto;

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

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCif() {
        return cif;
    }

    public String getCodigoNulidad() {
        return codigoNulidad;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getEmail() {
        return email;
    }

    public String getContacto() {
        return contacto;
    }
}
