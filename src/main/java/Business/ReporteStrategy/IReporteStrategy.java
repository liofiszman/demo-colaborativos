package Business.ReporteStrategy;

import Classes.Turno;
import DataAccess.DAOTurnos;

import java.util.List;

public interface IReporteStrategy {
    List<Turno> GetTurnos(DAOTurnos turnos);
}
