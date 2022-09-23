package DataAccess;

import Classes.FichaConformidad;
import Classes.FichaMecanica;

import java.util.List;

public interface IDAOFichaConformidad {
    List<FichaConformidad> obtenerFichasConformidad();
    FichaConformidad obtenerFichaConformidad(String id);
    void actualizar(FichaConformidad ficha);
}
