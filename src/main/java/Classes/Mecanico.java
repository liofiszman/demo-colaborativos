package Classes;
public class Mecanico extends Persona{
    public Mecanico(int id, String nombre, String tipoDocumento, String documento, String telefono, String especialidad) {
        this.setId(id);
        this.setNombre(nombre);
        this.setTipoDocumento(tipoDocumento);
        this.setDocumento(documento);
        this.setTelefono(telefono);
        this.setEspecialidad(especialidad);
    }

    private String especialidad;
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
