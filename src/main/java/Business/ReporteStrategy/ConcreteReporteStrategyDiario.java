package Business.ReporteStrategy;

import Classes.Turno;
import DataAccess.DAOTurnos;

import java.time.LocalDate;
import java.util.List;

public class ConcreteReporteStrategyDiario implements IReporteStrategy {
    public List<Turno> GetTurnos(DAOTurnos turnos) {
        return turnos.obtenerTurnos(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)); }

}
