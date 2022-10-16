package DataAccess;

import DTO.Vehiculo;

import java.util.List;

public interface IDAOVehiculo extends IDAO {
    List<DTO.Vehiculo> obtenerVehiculos();
    Vehiculo obtenerVehiculo(String id);
    Vehiculo obtenerVehiculoPatente(String patente, String compania, String cliente);
}
