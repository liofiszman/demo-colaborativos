package DataAccess;

import Classes.FichaConformidad;
import Classes.FichaMecanica;

import java.util.List;

public interface IDTOFichaConformidad {
    List<FichaConformidad> obtenerFichasConformidad();
    FichaConformidad obtenerFichaConformidad(String id);
}
