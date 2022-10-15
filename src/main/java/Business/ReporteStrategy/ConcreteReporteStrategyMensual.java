package Business.ReporteStrategy;

import Classes.Turno;
import DAO.TurnoDAO;
import DataAccess.DAOTurnos;

import java.time.LocalDate;
import java.util.List;

public class ConcreteReporteStrategyMensual implements  IReporteStrategy {
    public List<DTO.Turno> GetTurnos(TurnoDAO turnos) {
        LocalDate today = LocalDate.now();
        return turnos.obtenerTurnos(today.withDayOfMonth(1), today.withDayOfMonth(today.getMonth().length(today.isLeapYear()))); }
}
