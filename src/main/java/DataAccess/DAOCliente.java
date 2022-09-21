package DataAccess;

import Classes.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DAOCliente implements IDAOCliente {
    private List<Cliente> _clientes;
    private int _ID;
    private String _nombre;
    private int id = 123;

    public DAOCliente(){
        if(_clientes == null || _clientes.isEmpty()) {
            _clientes = new ArrayList<Cliente>();
            _clientes.add(new Cliente(001, "Lionel Fizman", "3323232323"));
            _clientes.add(new Cliente(002, "Juan Perez", "1231231233"));
            _clientes.add(new Cliente(123, "Hernan Cossu", "5559595959"));
        }
    }

    public List<Cliente> obtenerClientes(){
        return _clientes;
    }

    public Cliente obtenerCliente(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<Cliente> cliente =  _clientes.stream().filter(getByID).findFirst();
            if(cliente.isEmpty())
                return null;
            return cliente.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    private Predicate<Cliente> getByID = new Predicate<Cliente>() {
        @Override
        public boolean test(Cliente element) {
            return element.getId() == _ID;
        }
    };

    public Cliente obtenerClienteNombre(String nombre){
        try {
            _nombre = nombre;
            Optional<Cliente> cliente =  _clientes.stream().filter(getByNombre).findFirst();
            if(! cliente.isEmpty())
                return cliente.get();
        }
        catch (Exception ex) {
        }

        id++;
        Cliente nuevo = new Cliente(id, nombre, nombre);
        _clientes.add(nuevo);
        return nuevo;
    }

    private Predicate<Cliente> getByNombre = new Predicate<Cliente>() {
        @Override
        public boolean test(Cliente element) {
            return element.getNombre() == _nombre;
        }
    };
}
