package DataAccess;

import Classes.CompaniaSeguro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DAOCompaniaSeguro implements IDAOCompaniaSeguro {
    private List<CompaniaSeguro> _companiasSeguro;
    private int _ID;
    private String _nombre;

    public DAOCompaniaSeguro(){
        if(_companiasSeguro == null || _companiasSeguro.isEmpty()) {
            _companiasSeguro = new ArrayList<CompaniaSeguro>();
            _companiasSeguro.add(new CompaniaSeguro(001, "Sancor"));
            _companiasSeguro.add(new CompaniaSeguro(002, "La Caja"));
            _companiasSeguro.add(new CompaniaSeguro(003, "San Cristobal"));
            _companiasSeguro.add(new CompaniaSeguro(123, "Orbis"));
        }
    }

    public List<CompaniaSeguro> obtenerCompaniasSeguro(){
        return _companiasSeguro;
    }

    public CompaniaSeguro obtenerCompaniaSeguro(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<CompaniaSeguro> companiaSeguro =  _companiasSeguro.stream().filter(getByID).findFirst();
            if(companiaSeguro.isEmpty())
                return null;
            return companiaSeguro.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public CompaniaSeguro obtenerCompaniaSeguroNombre(String nombre){
        try {
            _nombre = nombre;
            Optional<CompaniaSeguro> companiaSeguro =  _companiasSeguro.stream().filter(getByNombre).findFirst();
            if(companiaSeguro.isEmpty())
                return null;
            return companiaSeguro.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    private Predicate<CompaniaSeguro> getByID = new Predicate<CompaniaSeguro>() {
        @Override
        public boolean test(CompaniaSeguro element) {
            return element.getId() == _ID;
        }
    };

    private Predicate<CompaniaSeguro> getByNombre = new Predicate<CompaniaSeguro>() {
        @Override
        public boolean test(CompaniaSeguro element) {
            return element.getNombre() == _nombre;
        }
    };
}
