package DataAccess;

import Classes.CompaniaSeguro;
import Classes.FichaMecanica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DTOFichaMecanica implements IDTOFichaMecanica{
    private List<FichaMecanica> _fichasMecanicas;
    private int _ID;

    public DTOFichaMecanica(){
        var _fichasConformidad = new DTOFichaConformidad();
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

    public List<FichaMecanica> obtenerFichasMecanicas(){
        return _fichasMecanicas;
    }

    public FichaMecanica obtenerFichaMecanica(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<FichaMecanica> fichaMecanica =  _fichasMecanicas.stream().filter(getByID).findFirst();
            if(fichaMecanica.isEmpty())
                return null;
            return fichaMecanica.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    private Predicate<FichaMecanica> getByID = new Predicate<FichaMecanica>() {
        @Override
        public boolean test(FichaMecanica element) {
            return element.getId() == _ID;
        }
    };
}
