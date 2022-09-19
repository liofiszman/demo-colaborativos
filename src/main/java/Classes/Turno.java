package Classes;
public class Turno {
    private int id;
    private String fecha;
    private Mecanico mecanico;
    private Vehiculo vehiculo;
    private Boolean asistencia;
    public Turno(){
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFecha(){
        return fecha;
    }
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    public Vehiculo getVehiculo(){
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }
    public boolean getAsistencia(){
        return asistencia;
    }
    public boolean setAsistnecia(boolean asistencia){
        return asistencia;
    }
}
