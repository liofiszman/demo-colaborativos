package DataAccess;

import Classes.FichaMecanica;

import java.util.List;

public interface IDAOFichaMecanica extends IDAO {
    List<DTO.FichaMecanica> obtenerFichasMecanicas();
    DTO.FichaMecanica obtenerFichaMecanica(String id);
    void actualizar(DTO.FichaMecanica ficha);
}
