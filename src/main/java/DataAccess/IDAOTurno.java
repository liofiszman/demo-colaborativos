package DataAccess;

import Classes.Mecanico;
import Classes.Opcion;
import Classes.Turno;

import java.util.List;

public interface IDAOTurno {
    List<Turno> obtenerTurnos(String patente);
    List<Turno> obtenerTurnos(Opcion opcion, List<Mecanico> mecanicos);
    Turno obtenerTurno(String id);
    void registrarAsistencia(String id);
    void cancelarTurno(String id);
    int addTurno(Turno turno, Opcion opcion);
    String obtenerTurnoID();
    void registrarActividades(String numeroTurno, String actividadesText, String insumosText);

    void firmaConforme(String numeroTurno);
    void firmaInconforme(String numeroTurno);
}
