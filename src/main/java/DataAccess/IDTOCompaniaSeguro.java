package DataAccess;

import Classes.CompaniaSeguro;

import java.util.List;

public interface IDTOCompaniaSeguro {
    List<CompaniaSeguro> obtenerCompaniasSeguro();
    CompaniaSeguro obtenerCompaniaSeguro(String id);
}
