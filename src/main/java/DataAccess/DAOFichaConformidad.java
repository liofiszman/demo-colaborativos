/*
package DataAccess;

import Classes.FichaConformidad;
import Classes.FichaMecanica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DAOFichaConformidad implements IDAOFichaConformidad {
    private List<FichaConformidad> _fichasConformidad;
    private int _ID;
    private int id = 123;

    public DAOFichaConformidad(){
        if(_fichasConformidad == null || _fichasConformidad.isEmpty()) {
            _fichasConformidad = new ArrayList<FichaConformidad>();
            _fichasConformidad.add(new FichaConformidad(001, true));
            _fichasConformidad.add(new FichaConformidad(002, true));
            _fichasConformidad.add(new FichaConformidad(003, false, "Es todo muy caro."));
            _fichasConformidad.add(new FichaConformidad(123, true));
        }
    }

    public List<FichaConformidad> obtenerFichasConformidad(){
        return _fichasConformidad;
    }

    public FichaConformidad obtenerFichaConformidad(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<FichaConformidad> fichaConformidad =  _fichasConformidad.stream().filter(getByID).findFirst();
            if(!fichaConformidad.isEmpty())
                return fichaConformidad.get();
        }
        catch (Exception ex) {
        }

        this.id++;
        FichaConformidad nueva = new FichaConformidad(this.id);
        _fichasConformidad.add(nueva);
        return nueva;
    }

    public void actualizar(FichaConformidad ficha){
        _ID = ficha.getId();

        _fichasConformidad.removeIf(getByID);
        _fichasConformidad.add(ficha);
    }

    private Predicate<FichaConformidad> getByID = new Predicate<FichaConformidad>() {
        @Override
        public boolean test(FichaConformidad element) {
            return element.getId() == _ID;
        }
    };
}
*/