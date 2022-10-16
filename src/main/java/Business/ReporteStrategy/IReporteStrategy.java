package Business.ReporteStrategy;

import DAO.TurnoDAO;

import java.util.List;

public interface IReporteStrategy {
    List<DTO.Turno> GetTurnos(TurnoDAO turnos);
}
