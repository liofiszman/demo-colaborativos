package DataAccess;

import Classes.FichaConformidad;

import java.util.List;

public interface IDAOFichaConformidad {
    List<FichaConformidad> obtenerFichasConformidad();
    FichaConformidad obtenerFichaConformidad(String id);
}
