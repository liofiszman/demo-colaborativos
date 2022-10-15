package DataAccess;

import Classes.FichaConformidad;
import Classes.FichaMecanica;

import java.util.List;

public interface IDAOFichaConformidad extends IDAO {
    List<DTO.FichaConformidad> obtenerFichasConformidad();
    DTO.FichaConformidad obtenerFichaConformidad(String id);
    void actualizar(DTO.FichaConformidad ficha);
}
