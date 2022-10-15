package DataAccess;

import Classes.CompaniaSeguro;

import java.util.List;

public interface IDAOCompaniaSeguro extends IDAO {
    List<DTO.CompaniaSeguro> obtenerCompaniasSeguro();
    DTO.CompaniaSeguro obtenerCompaniaSeguro(String id);
    DTO.CompaniaSeguro obtenerCompaniaSeguroNombre(String nombre);
}
