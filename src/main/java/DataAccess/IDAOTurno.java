package DataAccess;

import Classes.Mecanico;
import Classes.Opcion;
import DTO.Turno;

import java.time.LocalDate;
import java.util.List;

public interface IDAOTurno extends IDAO {
    List<DTO.Turno> obtenerTurnos(String patente);
    List<DTO.Turno> obtenerTurnos(LocalDate fechaDesde, LocalDate fechaHasta, boolean onlyActive);
    List<Classes.Turno> obtenerTurnosC(Opcion opcion, List<DTO.Mecanico> mecanicos) throws Exception;
    DTO.Turno obtenerTurno(String id);
    Classes.Turno obtenerTurnoCompleto(String id);
    void registrarAsistencia(String id);
    void cancelarTurno(String id);
    int addTurno(Turno turno, Opcion opcion);
    void registrarActividades(String numeroTurno, String actividadesText, String insumosText);

    void firmaConforme(String numeroTurno);
    void firmaInconforme(String numeroTurno);
}
