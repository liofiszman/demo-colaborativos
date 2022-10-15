package Classes;
public class Vehiculo {
    public Vehiculo(int id, String marca, String patente, CompaniaSeguro companiaSeguro, String numeroPoliza, Cliente cliente) {
        setId(id);
        setMarca(marca);
        setPatente(patente);
        setCompaniaSeguro(companiaSeguro);
        setNumeroPoliza(numeroPoliza);
        setCliente(cliente);
    }

    public Vehiculo(int id, String marca, String patente, DTO.CompaniaSeguro companiaSeguro, String numeroPoliza, Cliente cliente) {
        setId(id);
        setMarca(marca);
        setPatente(patente);
        CompaniaSeguro seguro = new CompaniaSeguro(companiaSeguro.getId(), companiaSeguro.getNombre());
        setCompaniaSeguro(seguro);
        setNumeroPoliza(numeroPoliza);
        setCliente(cliente);
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String marca;
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    private String patente;
    public String getPatente() {
        return patente;
    }
    public void setPatente(String patente) {
        this.patente = patente;
    }

    private CompaniaSeguro companiaSeguro;
    public CompaniaSeguro getCompaniaSeguro() {
        return companiaSeguro;
    }
    public void setCompaniaSeguro(CompaniaSeguro companiaSeguro) {
        this.companiaSeguro = companiaSeguro;
    }

    private String numeroPoliza;
    public String getNumeroPoliza() {
        return numeroPoliza;
    }
    public void setNumeroPoliza (String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    private Cliente cliente;
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente (Cliente numeroPoliza) {
        this.cliente = cliente;
    }
}
