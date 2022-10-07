package Business;
import Business.ReporteStrategy.ConcreteReporteStrategyDiario;
import Business.ReporteStrategy.ConcreteReporteStrategyMensual;
import Business.ReporteStrategy.IReporteStrategy;
import Business.ReporteStrategy.TipoReporteEnum;
import Classes.*;
import DataAccess.*;

import java.util.List;

public class TurnoBusinessObject {
    DAOTurnos turnos = new DAOTurnos();
    DAOMecanico mecanicos = new DAOMecanico();
    DAOCompaniaSeguro companiasSeguro = new DAOCompaniaSeguro();

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

    public List<String> getCompanias() {
        return companiasSeguro.obtenerCompaniasSeguro()
                .stream().map(CompaniaSeguro::getNombre).toList();
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
