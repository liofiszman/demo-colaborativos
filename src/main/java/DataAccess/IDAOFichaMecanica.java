package DataAccess;

import Classes.FichaMecanica;

import java.util.List;

public interface IDAOFichaMecanica {
    List<FichaMecanica> obtenerFichasMecanicas();
    FichaMecanica obtenerFichaMecanica(String id);
}
