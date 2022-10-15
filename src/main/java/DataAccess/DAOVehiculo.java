package DataAccess;

import Classes.Cliente;
import Classes.Vehiculo;
import DAO.CompaniaSegurosDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DAOVehiculo implements IDAOVehiculo {
    private List<Vehiculo> _vehiculos;
    private int _ID;
    private String _patente;
    private int id = 123;
    DAOCliente _clientes = new DAOCliente();
    CompaniaSegurosDAO _companias = getCompaSeguro();

    private CompaniaSegurosDAO getCompaSeguro() {
        try {return new CompaniaSegurosDAO();}
        catch(Exception ex)  {return null;}
    }

    public DAOVehiculo(){
        if(_vehiculos == null || _vehiculos.isEmpty()) {
            _vehiculos = new ArrayList<Vehiculo>();

            try{
        _vehiculos.add(new Vehiculo(001, "Peugeot", "OSU997",
                _companias.obtenerCompaniaSeguroNombre("Sancor"), "HJS-32", _clientes.obtenerCliente("001")));
        _vehiculos.add(new Vehiculo(002, "Ford", "HML959",
                _companias.obtenerCompaniaSeguroNombre("Sancor"), "1231231233", _clientes.obtenerCliente("002")));
        _vehiculos.add(new Vehiculo(123, "Ford", "AR423IR",
                _companias.obtenerCompaniaSeguroNombre("Sancor"), "IIS92LLJ", _clientes.obtenerCliente("123")));
        _vehiculos.add(new Vehiculo(123, "Nissan", "OSO002",
                _companias.obtenerCompaniaSeguroNombre("Sancor"), "HJS-33", _clientes.obtenerCliente("001")));
        }
        catch (Exception ex) {}
        }
    }

    public List<Vehiculo> obtenerVehiculos(){
        return _vehiculos;
    }

    public Vehiculo obtenerVehiculoPatente(String patente, String compania, String cliente){
        try {
            _patente = patente;
            Optional<Vehiculo> vehiculo =  _vehiculos.stream().filter(getByPatente).findFirst();
            return vehiculo.get();
        }
        catch (Exception ex) {
        }

        id++;
        try{
        Vehiculo nuevo = new Vehiculo(id, "Subaru", patente,
                _companias.obtenerCompaniaSeguroNombre(compania), "Poliza-xxS", _clientes.obtenerClienteNombre(cliente));
        _vehiculos.add(nuevo);
        return nuevo;}
        catch (Exception ex) {return null;}
    }

    private Predicate<Vehiculo> getByPatente = new Predicate<Vehiculo>() {
        @Override
        public boolean test(Vehiculo element) {
            return element.getPatente() == _patente;
        }
    };

    public Vehiculo obtenerVehiculo(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<Vehiculo> vehiculo =  _vehiculos.stream().filter(getByID).findFirst();
            if(vehiculo.isEmpty())
                return null;
            return vehiculo.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    private Predicate<Vehiculo> getByID = new Predicate<Vehiculo>() {
        @Override
        public boolean test(Vehiculo element) {
            return element.getId() == _ID;
        }
    };
}
