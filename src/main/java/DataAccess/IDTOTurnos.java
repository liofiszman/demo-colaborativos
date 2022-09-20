package DataAccess;

import Classes.Turno;

import java.util.List;

public interface IDTOTurnos {
    List<Turno> obtenerTurnos(String patente);
    Turno obtenerTurno(String id);
}
