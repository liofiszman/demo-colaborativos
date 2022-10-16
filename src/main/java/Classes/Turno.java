package Classes;

import Business.EstadoTurnoEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

    public Turno(){
    }

    public Turno(int id, LocalDate fecha, LocalTime hora, Mecanico mecanico){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.mecanico = mecanico;
    }

    public Turno(LocalDate fecha, LocalTime hora, DTO.Mecanico _mecanico){
        this.fecha = fecha;
        this.hora = hora;
        Mecanico mecanico = new Mecanico(_mecanico.get_id(), _mecanico.get_nombre(), _mecanico.get_especialidad());
        this.mecanico = mecanico;
    }

    public Turno(int id, LocalDate fecha, LocalTime hora, Mecanico mecanico, Vehiculo vehiculo, FichaMecanica fichaMecanica){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.mecanico = mecanico;
        this.vehiculo = vehiculo;
        this.fichaMecanica = fichaMecanica;
        this.patente = vehiculo.getPatente();
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private boolean isActive = true;
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    private LocalDate fecha;
    public LocalDate getFecha(){
        return fecha;
    }
    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }

    private LocalTime hora;
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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
        this.vehiculo = vehiculo; this.patente = vehiculo.getPatente();
    }
    private String patente;
    public String getPatente() {
        return vehiculo.getPatente();
    }
    public void setPatente(String patente) {
        this.patente = patente;
    }

    private FichaMecanica fichaMecanica;
    public FichaMecanica getFichaMecanica() { return fichaMecanica; }
    public void setFichaMecanica(FichaMecanica fichaMecanica) { this.fichaMecanica = fichaMecanica; }

    private String mecanicoNombre;
    public String getMecanicoNombre() {
        return this.getMecanico().getNombre();
    }

    public EstadoTurnoEnum getEstadoTurno()
    {
        if(!isActive)
            return EstadoTurnoEnum.CANCELADO;
        if(fichaMecanica != null
                && fichaMecanica.getFichaConformidad() != null
                && fichaMecanica.getFichaConformidad().isFirmada())
            return EstadoTurnoEnum.FIRMADO;
        if(fichaMecanica != null)
            return EstadoTurnoEnum.TRABAJADO;
        if(asistencia)
            return EstadoTurnoEnum.ASISTENCIA;

        return EstadoTurnoEnum.CREADO;
    }
}
