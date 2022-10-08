package DTO;

public class TurnoDTO {
    private Integer id;
    private Byte active;
    private java.sql.Date fecha;
    private java.sql.Time hora;
    private Integer mecanicoId;
    private Integer vehiculoId;
    private Byte asistencia;
    private Integer fichaMecanicaId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getActive() {
        return this.active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public java.sql.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public java.sql.Time getHora() {
        return this.hora;
    }

    public void setHora(java.sql.Time hora) {
        this.hora = hora;
    }

    public Integer getMecanicoId() {
        return this.mecanicoId;
    }

    public void setMecanicoId(Integer mecanicoId) {
        this.mecanicoId = mecanicoId;
    }

    public Integer getVehiculoId() {
        return this.vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Byte getAsistencia() {
        return this.asistencia;
    }

    public void setAsistencia(Byte asistencia) {
        this.asistencia = asistencia;
    }

    public Integer getFichaMecanicaId() {
        return this.fichaMecanicaId;
    }

    public void setFichaMecanicaId(Integer fichaMecanicaId) {
        this.fichaMecanicaId = fichaMecanicaId;
    }
}
