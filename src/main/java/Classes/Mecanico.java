package Classes;

import java.util.List;

public class Mecanico extends Persona{
    public Mecanico(int id, String nombre, String tipoDocumento, String documento, String telefono, String especialidad
    , List<HorarioAtencion> horariosAtencion) {
        this.setId(id);
        this.setNombre(nombre);
        this.setTipoDocumento(tipoDocumento);
        this.setDocumento(documento);
        this.setTelefono(telefono);
        this.setEspecialidad(especialidad);
        this.setHorariosAtencion(horariosAtencion);
    }

    public Mecanico(int id, String nombre, String especialidad) {
        this.setId(id);
        this.setNombre(nombre);
        this.setEspecialidad(especialidad);
    }

    private String especialidad;
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    private List<HorarioAtencion> horariosAtencion;
    public List<HorarioAtencion> getHorariosAtencion() {
        return horariosAtencion;
    }
    public void setHorariosAtencion(List<HorarioAtencion> horariosAtencion) {
        this.horariosAtencion = horariosAtencion;
    }
}
