package DataAccess;

import Classes.Cliente;

import java.util.List;

public interface IDAOCliente extends IDAO {
    List<Cliente> obtenerClientes();
    Cliente obtenerCliente(String id);
    Cliente obtenerClienteNombre(String nombre);
}
