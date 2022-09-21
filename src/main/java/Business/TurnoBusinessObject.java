package Business;
import Classes.*;
import DataAccess.*;

import java.util.List;

public class TurnoBusinessObject {
    DAOTurnos turnos = new DAOTurnos();
    DAOMecanico mecanicos = new DAOMecanico();
    DAOCompaniaSeguro companiasSeguro = new DAOCompaniaSeguro();

    public List<Turno> obtenerTurnos(String patente) {
        return turnos.obtenerTurnos(patente);
    }

    public List<Turno> obtenerTurnos(Opcion opcion) {
        List<Mecanico> opcionesMecanicos = mecanicos.obtenerTurnos(opcion);
        return turnos.obtenerTurnos(opcion, opcionesMecanicos);
    }

    public int addTurno(Turno turno, Opcion opcion) {
        return turnos.addTurno(turno, opcion);
    }

    public String obtenerTurnoID() {
        return turnos.obtenerTurnoID();
    }

    public List<String> obtenerEspecialidades() {
        return mecanicos.obtenerEspecialidades();
    }

    public List<String> getCompanias() {
        return companiasSeguro.obtenerCompaniasSeguro()
                .stream().map(CompaniaSeguro::getNombre).toList();
    }

    public Turno obtenerTurno(String id) {
        return turnos.obtenerTurno(id);
    }

    public void registrarAsistencia(String id) { turnos.registrarAsistencia(id); }
    public void cancelarTurno(String id) { turnos.cancelarTurno(id); }
}
