package DataAccess;

import Classes.FichaMecanica;
import Classes.Turno;

import java.util.List;

public interface IDTOFichaMecanica {
    List<FichaMecanica> obtenerFichasMecanicas();
    FichaMecanica obtenerFichaMecanica(String id);
}
