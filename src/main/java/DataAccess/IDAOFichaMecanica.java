package DataAccess;

import Classes.FichaMecanica;

import java.util.List;

public interface IDAOFichaMecanica extends IDAO {
    List<DTO.FichaMecanica> obtenerFichasMecanicas();
    DTO.FichaMecanica obtenerFichaMecanica(String id);
    DTO.FichaMecanica obtenerFichaMecanica(int id);
    void registrarActividades(int id, String actividadesText,String insumosText);
}
