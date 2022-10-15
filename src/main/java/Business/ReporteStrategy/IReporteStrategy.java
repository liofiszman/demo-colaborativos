package Business.ReporteStrategy;

import Classes.Turno;
import DAO.TurnoDAO;
import DataAccess.DAOTurnos;

import java.util.List;

public interface IReporteStrategy {
    List<DTO.Turno> GetTurnos(TurnoDAO turnos);
}
