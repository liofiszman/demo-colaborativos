package DataAccess;

import Classes.Mecanico;
import Classes.Opcion;
import Classes.Turno;

import java.util.List;

public interface IDAOMecanico extends IDAO {
    List<DTO.Mecanico> obtenerMecanicos();
    List<DTO.Mecanico> obtenerMecanicosPorEspecialidad(Opcion opcion);
    List<String> obtenerEspecialidades();
    DTO.Mecanico obtenerMecanico(String id);
    DTO.Mecanico obtenerMecanico(int id);
    DTO.Mecanico obtenerMecanicoNombre(String nombre);
}
