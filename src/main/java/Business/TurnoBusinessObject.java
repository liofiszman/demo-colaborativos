package Business;
import Classes.*;
import DataAccess.*;

import java.util.List;

public class TurnoBusinessObject {
    DTOTurnos turnos = new DTOTurnos();

    public List<Turno> obtenerTurnos(String patente) {
        return turnos.obtenerTurnos(patente);
    }

    public Turno obtenerTurno(String id) {
        return turnos.obtenerTurno(id);
    }
}
