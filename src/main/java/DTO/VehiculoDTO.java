package DTO;

public class VehiculoDTO {
    private Integer id;
    private Integer companiaSeguroId;
    private Integer clienteId;
    private String numeroPoliza;
    private String marca;
    private String patente;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompaniaSeguroId() {
        return this.companiaSeguroId;
    }

    public void setCompaniaSeguroId(Integer companiaSeguroId) {
        this.companiaSeguroId = companiaSeguroId;
    }

    public Integer getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNumeroPoliza() {
        return this.numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPatente() {
        return this.patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }
}
