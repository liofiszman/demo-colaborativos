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
import java.util.stream.Collectors;

public class TurnoBusinessObject {
    TurnoDAO turnos;
    MecanicoDAO mecanicos;
    CompaniaSegurosDAO companiasSeguro;
    VehiculoDAO vehiculos;
    FichaMecanicaDAO fichasMecanicas;
    FichaConformidadDAO fichasConformidad;

    public TurnoBusinessObject() {
        turnos = (TurnoDAO) getDAO(TipoDAOEnum.DAOTurnos);
        mecanicos = (MecanicoDAO) getDAO(TipoDAOEnum.DAOMecanico);
        companiasSeguro = (CompaniaSegurosDAO) getDAO(TipoDAOEnum.DAOCompaniaSeguro);
        vehiculos = (VehiculoDAO) getDAO(TipoDAOEnum.DAOVehiculo);
        fichasMecanicas = (FichaMecanicaDAO) getDAO(TipoDAOEnum.DAOFichaMecanica);
        fichasConformidad = (FichaConformidadDAO) getDAO(TipoDAOEnum.DAOFichaConformidad);
    }


    IDAO getDAO(TipoDAOEnum tipoDAO) {
        try {
            switch (tipoDAO) {
                case DAOTurnos : { return new TurnoDAO(); }
                case DAOMecanico : { return new MecanicoDAO(); }
                case DAOVehiculo : { return new VehiculoDAO(); }
                case DAOCompaniaSeguro : { return new CompaniaSegurosDAO(); }
                case DAOFichaConformidad : { return new FichaConformidadDAO(); }
                case DAOFichaMecanica : { return new FichaMecanicaDAO(); }
                default : { return null; }
            }
        }
        catch (Exception ex){
            return null;
        }
    }

    public List<DTO.Turno> obtenerTurnos(String patente) {
        return turnos.obtenerTurnos(patente);
    }

    public List<Classes.Turno> obtenerTurnos(Opcion opcion) throws Exception{
        List<DTO.Mecanico> opcionesMecanicos = mecanicos.obtenerMecanicosPorEspecialidad(opcion);
        return turnos.obtenerTurnosC(opcion, opcionesMecanicos);
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

    public int addTurno(Classes.Turno turno, Opcion opcion) {
        return turnos.addTurno(turno, opcion);
    }

    public List<String> obtenerEspecialidades() {
        return mecanicos.obtenerEspecialidades();
    }

    public List<String> getCompanias() throws Exception {
        return companiasSeguro.obtenerCompaniasSeguro()
                .stream().map(DTO.CompaniaSeguro::getNombre).collect(Collectors.toList());
    }
    public CompaniaSeguro obtenerCompaniaSeguro(int id) {
        return companiasSeguro.obtenerCompaniaSeguro(id);
    }

    public void registrarActividades(String numeroTurno,String actividadesText,String insumosText){
        turnos.registrarActividades(numeroTurno, actividadesText, insumosText);
    }

    public FichaConformidad obtenerFichaConformidad(int id) {
        return fichasConformidad.obtenerFichaConformidad(id);
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
    public Classes.Turno obtenerTurnoCompleto(String id) {
        return turnos.obtenerTurnoCompleto(id);
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

    public FichaMecanica obtenerFichaMecanica(int id)  {
        return fichasMecanicas.obtenerFichaMecanica(id);
    }

    public Vehiculo obtenerVehiculo(int id)  {
        return vehiculos.obtenerVehiculo(id);
    }

    public String obtenerTurnoID() {
        return turnos.obtenerTurnoID();
    }
}
