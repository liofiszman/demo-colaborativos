package Business.ReporteStrategy;

import Classes.Turno;
import DAO.TurnoDAO;

import java.time.LocalDate;
import java.util.List;

public class ConcreteReporteStrategyMensual implements  IReporteStrategy {
    public List<DTO.Turno> GetTurnos(TurnoDAO turnos) {
        return turnos.obtenerTurnos(
                LocalDate.now().withDayOfMonth(1),
                LocalDate.now().withDayOfMonth(LocalDate.now().getMonth().length(LocalDate.now().isLeapYear())),
                false
        );
    }
}
