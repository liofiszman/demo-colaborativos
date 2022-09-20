package Classes;

import java.time.LocalDate;

public class Turno {

    public Turno(){
    }

    public Turno(int id, LocalDate fecha, Mecanico mecanico, Vehiculo vehiculo, FichaMecanica fichaMecanica){
        this.id = id;
        this.fecha = fecha;
        this.mecanico = mecanico;
        this.vehiculo = vehiculo;
        this.fichaMecanica = fichaMecanica;
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private LocalDate fecha;
    public LocalDate getFecha(){
        return fecha;
    }
    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }

    private Boolean asistencia;
    public boolean getAsistencia(){
        return asistencia;
    }
    public boolean setAsistnecia(boolean asistencia){
        return asistencia;
    }

    private Mecanico mecanico;
    public Mecanico getMecanico(){
        return mecanico;
    }
    public void setMecanico(Mecanico mecanico){
        this.mecanico = mecanico;
    }

    private Vehiculo vehiculo;
    public Vehiculo getVehiculo(){
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    private FichaMecanica fichaMecanica;
    public FichaMecanica getFichaMecanica() { return fichaMecanica; }
    public void setFichaMecanica(FichaMecanica fichaMecanica) { this.fichaMecanica = fichaMecanica; }
}
