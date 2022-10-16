package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class Opcion {
    private String Patente;
    public void setPatente(String value) { Patente = value; }
    public String getPatente() { return Patente; }

    private String Especialidad;
    public void setEspecialidad(String value) { Especialidad = value; }
    public String getEspecialidad() { return Especialidad; }

    private String Compania;
    public void setCompania(String value) { Compania = value; }
    public String getCompania() { return Compania; }

    private String Cliente;
    public void setCliente(String value) { Cliente = value; }
    public String getCliente() { return Cliente; }

    private LocalDate Fecha;
    public void setFecha(LocalDate value) { Fecha = value; }
    public LocalDate getFecha() { return Fecha; }

    private LocalTime Hora;

    public LocalTime getHora() {
        return Hora;
    }

    public void setHora(LocalTime hora) {
        Hora = hora;
    }

    private DTO.Mecanico Mecanico;

    public DTO.Mecanico getMecanico() {
        return Mecanico;
    }

    public void setMecanico(DTO.Mecanico mecanico) {
        Mecanico = mecanico;
    }

    private LocalDate FechaHasta;

    public LocalDate getFechaHasta() {
        return FechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        FechaHasta = fechaHasta;
    }
}
