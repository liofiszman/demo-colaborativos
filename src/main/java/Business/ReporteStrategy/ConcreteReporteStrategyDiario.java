package Business.ReporteStrategy;

import Classes.Turno;
import DAO.TurnoDAO;

import java.time.LocalDate;
import java.util.List;

public class ConcreteReporteStrategyDiario implements IReporteStrategy {
    public List<DTO.Turno> GetTurnos(TurnoDAO turnos) {
        return turnos.obtenerTurnos(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1),
                false); }

}
