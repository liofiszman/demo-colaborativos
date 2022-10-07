package DTO;

public class FichaConformidadDTO {
    private Integer id;
    private String motivosDisconforme;
    private Byte firmada;
    private Byte firmadaConforme;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivosDisconforme() {
        return this.motivosDisconforme;
    }

    public void setMotivosDisconforme(String motivosDisconforme) {
        this.motivosDisconforme = motivosDisconforme;
    }

    public Byte getFirmada() {
        return this.firmada;
    }

    public void setFirmada(Byte firmada) {
        this.firmada = firmada;
    }

    public Byte getFirmadaConforme() {
        return this.firmadaConforme;
    }

    public void setFirmadaConforme(Byte firmadaConforme) {
        this.firmadaConforme = firmadaConforme;
    }
}
