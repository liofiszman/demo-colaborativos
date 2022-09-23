package DataAccess;

import Classes.FichaConformidad;
import Classes.FichaMecanica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DAOFichaMecanica implements IDAOFichaMecanica {
    private List<FichaMecanica> _fichasMecanicas;
    private int _ID;
    private int id = 123;
    DAOFichaConformidad _fichasConformidad = new DAOFichaConformidad();


    public DAOFichaMecanica(){
        if(_fichasMecanicas == null || _fichasMecanicas.isEmpty()) {
            _fichasMecanicas = new ArrayList<FichaMecanica>();
            _fichasMecanicas.add(new FichaMecanica(001, "Cambio de aceite.", "LION400, $2200.",
                    _fichasConformidad.obtenerFichaConformidad("001")));
            _fichasMecanicas.add(new FichaMecanica(002, "Alineado y balanceado.", "Sin insumos.",
                    _fichasConformidad.obtenerFichaConformidad("002"))); // frenos
            _fichasMecanicas.add(new FichaMecanica(003, "Cambio de batería.", "Cliente trae batería, sólo MO.",
                    _fichasConformidad.obtenerFichaConformidad("003")));
            _fichasMecanicas.add(new FichaMecanica(123, "Cambio de filtros.", "filtros XXS-200, $1150.",
                    _fichasConformidad.obtenerFichaConformidad("123")));
        }
    }

    public List<FichaMecanica> obtenerFichasMecanicas(){
        return _fichasMecanicas;
    }

    public void actualizar(FichaMecanica ficha){
        _ID = ficha.getId();

        _fichasConformidad.actualizar(ficha.getFichaConformidad());
        _fichasMecanicas.removeIf(getByID);
        _fichasMecanicas.add(ficha);
    }

    public FichaMecanica obtenerFichaMecanica(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<FichaMecanica> fichaMecanica =  _fichasMecanicas.stream().filter(getByID).findFirst();
            if(! fichaMecanica.isEmpty())
                return fichaMecanica.get();
        }
        catch (Exception ex) {
        }

        this.id++;
        FichaMecanica nueva = new FichaMecanica(this.id, "", "", _fichasConformidad.obtenerFichaConformidad("0"));
        _fichasMecanicas.add(nueva);
        return nueva;
    }

    private Predicate<FichaMecanica> getByID = new Predicate<FichaMecanica>() {
        @Override
        public boolean test(FichaMecanica element) {
            return element.getId() == _ID;
        }
    };
}
