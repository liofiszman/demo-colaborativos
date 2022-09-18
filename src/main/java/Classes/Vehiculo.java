package Classes;public class Vehiculo {
    private int id;
    private String marca;
    private String patente;
    private CompaniaSeguro companiaSeguro;
    private String numeroPoliza;

    public Vehiculo() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getPatente() {
        return patente;
    }
    public void setPatente(String patente) {
        this.patente = patente;
    }

    public CompaniaSeguro getCompaniaSeguro() {
        return companiaSeguro;
    }
    public void setCompaniaSeguro(CompaniaSeguro companiaSeguro) {
        this.companiaSeguro = companiaSeguro;
    }
    public String getNumeroPoliza() {
        return numeroPoliza;
    }
    public void setNumeroPoliza (String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

}
