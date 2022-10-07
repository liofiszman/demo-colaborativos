package DTO;

public class FichaMecanicaDTO {
    private Integer id;
    private String actividades;
    private Integer fichaConformidadId;
    private String repuestos;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActividades() {
        return this.actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public Integer getFichaConformidadId() {
        return this.fichaConformidadId;
    }

    public void setFichaConformidadId(Integer fichaConformidadId) {
        this.fichaConformidadId = fichaConformidadId;
    }

    public String getRepuestos() {
        return this.repuestos;
    }

    public void setRepuestos(String repuestos) {
        this.repuestos = repuestos;
    }
}
