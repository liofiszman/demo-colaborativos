package Business.ReporteStrategy;

import Classes.Turno;
import DataAccess.DAOTurnos;

import java.time.LocalDate;
import java.util.List;

public class ConcreteReporteStrategyMensual implements  IReporteStrategy {
    public List<Turno> GetTurnos(DAOTurnos turnos) {
        LocalDate today = LocalDate.now();
        return turnos.obtenerTurnos(today.withDayOfMonth(1), today.withDayOfMonth(today.getMonth().length(today.isLeapYear()))); }
}
