package DataAccess;

import Classes.Vehiculo;

import java.util.List;

public interface IDAOVehiculo {
    List<Vehiculo> obtenerVehiculos();
    Vehiculo obtenerVehiculo(String id);
    Vehiculo obtenerVehiculoPatente(String patente, String compania, String cliente);
}
