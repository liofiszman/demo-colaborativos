package DataAccess;

import Classes.CompaniaSeguro;
import Classes.FichaConformidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DTOFichaConformidad implements IDTOFichaConformidad {
    private List<FichaConformidad> _fichasConformidad;
    private int _ID;

    public DTOFichaConformidad(){
        _fichasConformidad = new ArrayList<FichaConformidad>();
        _fichasConformidad.add(new FichaConformidad(001, true));
        _fichasConformidad.add(new FichaConformidad(002, true));
        _fichasConformidad.add(new FichaConformidad(003, false, "Es todo muy caro."));
        _fichasConformidad.add(new FichaConformidad(123, true));
    }

    public List<FichaConformidad> obtenerFichasConformidad(){
        return _fichasConformidad;
    }

    public FichaConformidad obtenerFichaConformidad(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<FichaConformidad> fichaConformidad =  _fichasConformidad.stream().filter(getByID).findFirst();
            if(fichaConformidad.isEmpty())
                return null;
            return fichaConformidad.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    private Predicate<FichaConformidad> getByID = new Predicate<FichaConformidad>() {
        @Override
        public boolean test(FichaConformidad element) {
            return element.getId() == _ID;
        }
    };
}
