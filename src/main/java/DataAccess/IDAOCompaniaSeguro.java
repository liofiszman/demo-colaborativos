package DataAccess;

import Classes.CompaniaSeguro;

import java.util.List;

public interface IDAOCompaniaSeguro extends IDAO {
    List<DTO.CompaniaSeguro> obtenerCompaniasSeguro() throws Exception;
    DTO.CompaniaSeguro obtenerCompaniaSeguro(String id) throws Exception ;
    DTO.CompaniaSeguro obtenerCompaniaSeguroNombre(String nombre) throws Exception ;
}
