package DataAccess;

import Classes.Mecanico;
import Classes.Opcion;
import Classes.Turno;

import java.util.List;

public interface IDAOMecanico extends IDAO {
    List<Mecanico> obtenerMecanicos();
    List<Mecanico> obtenerTurnos(Opcion opcion);
    List<String> obtenerEspecialidades();
    Mecanico obtenerMecanico(String id);
}
