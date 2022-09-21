package Classes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class HorarioAtencion {
    public HorarioAtencion(int id, DayOfWeek diaAtencion, LocalTime horaDesde, LocalTime horaHasta) {
        setId(id);
        setDiaAtencion(diaAtencion);
        setHoraDesde(horaDesde);
        setHoraHasta(horaHasta);
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private DayOfWeek diaAtencion;
    public DayOfWeek getDiaAtencion() {
        return diaAtencion;
    }
    public void setDiaAtencion(DayOfWeek diaAtencion) {
        this.diaAtencion = diaAtencion;
    }

    private LocalTime horaDesde;
    public LocalTime getHoraDesde() {
        return horaDesde;
    }
    public void setHoraDesde(LocalTime horaDesde) {
        this.horaDesde = horaDesde;
    }

    private LocalTime horaHasta;
    public LocalTime getHoraHasta() {
        return horaHasta;
    }
    public void setHoraHasta(LocalTime horaHasta) {
        this.horaHasta = horaHasta;
    }
}
