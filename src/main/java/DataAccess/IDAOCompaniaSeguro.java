package DataAccess;

import Classes.CompaniaSeguro;

import java.util.List;

public interface IDAOCompaniaSeguro {
    List<CompaniaSeguro> obtenerCompaniasSeguro();
    CompaniaSeguro obtenerCompaniaSeguro(String id);
    CompaniaSeguro obtenerCompaniaSeguroNombre(String nombre);
}
