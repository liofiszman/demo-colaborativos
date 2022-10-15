package DataAccess;

import Classes.Cliente;

import java.util.List;

public interface IDAOCliente extends IDAO {
    List<DTO.Cliente> obtenerClientes();
    DTO.Cliente obtenerCliente(String id);
    DTO.Cliente obtenerClienteNombre(String nombre);
}
