package Business;
import Business.ReporteStrategy.ConcreteReporteStrategyDiario;
import Business.ReporteStrategy.ConcreteReporteStrategyMensual;
import Business.ReporteStrategy.IReporteStrategy;
import Business.ReporteStrategy.TipoReporteEnum;
import Classes.Opcion;
import DAO.*;
import DTO.*;
import DataAccess.*;
import java.util.List;

public class TurnoBusinessObject {
    TurnoDAO turnos = (TurnoDAO) getDAO(TipoDAOEnum.DAOTurnos);
    MecanicoDAO mecanicos = (MecanicoDAO) getDAO(TipoDAOEnum.DAOMecanico);
    CompaniaSegurosDAO companiasSeguro = (CompaniaSegurosDAO) getDAO(TipoDAOEnum.DAOCompaniaSeguro);

    IDAO getDAO(TipoDAOEnum tipoDAO) {
        try {
            switch (tipoDAO) {
                case DAOTurnos -> { return new TurnoDAO(); }
                case DAOMecanico -> { return new MecanicoDAO(); }
                case DAOCompaniaSeguro -> { return new CompaniaSegurosDAO(); }
                default -> { return null; }
            }
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<DTO.Turno> obtenerTurnos(String patente) {
        return turnos.obtenerTurnos(patente);
    }

    public List<DTO.Turno> obtenerTurnos(Opcion opcion) {
        List<DTO.Mecanico> opcionesMecanicos = mecanicos.obtenerTurnos(opcion);
        return turnos.obtenerTurnos(opcion, opcionesMecanicos);
    }

    private IReporteStrategy Strategy;
    private void setStrategy(IReporteStrategy strategy) {
        this.Strategy = strategy; }
    private List<DTO.Turno> executeStrategy() {
        return Strategy.GetTurnos(turnos); }

    public List<DTO.Turno> GetTurnos(TipoReporteEnum tipoReporteEnum) {
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
    public Mecanico obtenerMecanico(int id) {
        return mecanicos.obtenerMecanico(id);
    }

    public void registrarAsistencia(String id) {
        turnos.registrarAsistencia(id);
    }
    public void cancelarTurno(String id) {
        turnos.cancelarTurno(id);
    }
}
