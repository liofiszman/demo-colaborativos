package DataAccess;

import Classes.FichaMecanica;

import java.util.List;

public interface IDAOFichaMecanica extends IDAO {
    List<FichaMecanica> obtenerFichasMecanicas();
    FichaMecanica obtenerFichaMecanica(String id);
    void actualizar(FichaMecanica ficha);
}
