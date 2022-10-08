package DTO;

public class HorarioAtencionDTO {
    private Integer id;
    private String diaAtencion;
    private java.sql.Time horaDesde;
    private java.sql.Time horaHasta;
    private Integer mecanicoId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiaAtencion() {
        return this.diaAtencion;
    }

    public void setDiaAtencion(String diaAtencion) {
        this.diaAtencion = diaAtencion;
    }

    public java.sql.Time getHoraDesde() {
        return this.horaDesde;
    }

    public void setHoraDesde(java.sql.Time horaDesde) {
        this.horaDesde = horaDesde;
    }

    public java.sql.Time getHoraHasta() {
        return this.horaHasta;
    }

    public void setHoraHasta(java.sql.Time horaHasta) {
        this.horaHasta = horaHasta;
    }

    public Integer getMecanicoId() {
        return this.mecanicoId;
    }

    public void setMecanicoId(Integer mecanicoId) {
        this.mecanicoId = mecanicoId;
    }
}
