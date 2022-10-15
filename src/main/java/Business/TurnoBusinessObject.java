package Business;
import Business.ReporteStrategy.ConcreteReporteStrategyDiario;
import Business.ReporteStrategy.ConcreteReporteStrategyMensual;
import Business.ReporteStrategy.IReporteStrategy;
import Business.ReporteStrategy.TipoReporteEnum;
import Classes.*;
import DAO.*;
import DataAccess.*;

import java.util.List;

public class TurnoBusinessObject {
    DAOTurnos turnos = (DAOTurnos) getDAO(TipoDAOEnum.DAOTurnos);
    DAOMecanico mecanicos = (DAOMecanico) getDAO(TipoDAOEnum.DAOMecanico);
    CompaniaSegurosDAO companiasSeguro = (CompaniaSegurosDAO) getDAO(TipoDAOEnum.DAOCompaniaSeguro);

    IDAO getDAO(TipoDAOEnum tipoDAO) {
        try {
            switch (tipoDAO) {
                case DAOTurnos -> { return new DAOTurnos(); }
                case DAOMecanico -> { return new DAOMecanico(); }
                case DAOCompaniaSeguro -> { return new CompaniaSegurosDAO(); }
                default -> { return null; }
            }
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<Turno> obtenerTurnos(String patente) {
        return turnos.obtenerTurnos(patente);
    }

    public List<Turno> obtenerTurnos(Opcion opcion) {
        List<Mecanico> opcionesMecanicos = mecanicos.obtenerTurnos(opcion);
        return turnos.obtenerTurnos(opcion, opcionesMecanicos);
    }

    private IReporteStrategy Strategy;
    private void setStrategy(IReporteStrategy strategy) {
        this.Strategy = strategy; }
    private List<Turno> executeStrategy() {
        return Strategy.GetTurnos(turnos); }

    public List<Turno> GetTurnos(TipoReporteEnum tipoReporteEnum) {
        if (tipoReporteEnum == TipoReporteEnum.Diario)
            setStrategy(new ConcreteReporteStrategyDiario());
        if (tipoReporteEnum == TipoReporteEnum.Mensual)
            setStrategy(new ConcreteReporteStrategyMensual());

        return executeStrategy();
    }

    public int addTurno(Turno turno, Opcion opcion) {
        return turnos.addTurno(turno, opcion);
    }

    public String obtenerTurnoID() {
        return turnos.obtenerTurnoID();
    }

    public List<String> obtenerEspecialidades() {
        return mecanicos.obtenerEspecialidades();
    }

    public List<String> getCompanias() throws Exception {
        return companiasSeguro.obtenerCompaniasSeguro()
                .stream().map(DTO.CompaniaSeguro::getNombre).toList();
    }

    public void registrarActividades(String numeroTurno,String actividadesText,String insumosText){
        turnos.registrarActividades(numeroTurno, actividadesText, insumosText);
    }

    public void firmaConforme(String numeroTurno){
        turnos.firmaConforme(numeroTurno);
    }
    public void firmaInconforme(String numeroTurno){
        turnos.firmaInconforme(numeroTurno);
    }

    public Turno obtenerTurno(String id) {
        return turnos.obtenerTurno(id);
    }

    public void registrarAsistencia(String id) { turnos.registrarAsistencia(id); }
    public void cancelarTurno(String id) { turnos.cancelarTurno(id); }
}
