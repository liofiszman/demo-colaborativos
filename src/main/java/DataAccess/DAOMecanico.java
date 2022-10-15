/*
package DataAccess;


import Classes.HorarioAtencion;
import Classes.Mecanico;
import Classes.Opcion;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DAOMecanico implements IDAOMecanico {
    private List<Mecanico> _mecanicos;
    private int _ID;
    private Opcion _opcion;

    public DAOMecanico(){
        if(_mecanicos == null || _mecanicos.isEmpty()) {
            _mecanicos = new ArrayList<Mecanico>();

        }
    }

    public List<Mecanico> obtenerMecanicos(){
        return _mecanicos;
    }

    public List<String> obtenerEspecialidades(){
        return _mecanicos.stream().map(Mecanico::getEspecialidad).toList();
    }

    public Mecanico obtenerMecanico(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<Mecanico> mecanico =  _mecanicos.stream().filter(getByID).findFirst();
            if(mecanico.isEmpty())
                return null;
            return mecanico.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    private Predicate<Mecanico> getByID = new Predicate<Mecanico>() {
        @Override
        public boolean test(Mecanico element) {
            return element.getId() == _ID;
        }
    };

    public List<Mecanico> obtenerTurnos(Opcion opcion) {
        _opcion = opcion;
        return _mecanicos.stream().filter(getTurnosByEspecialidad).toList();
    }

    private Predicate<Mecanico> getTurnosByEspecialidad = new Predicate<Mecanico>() {
        @Override
        public boolean test(Mecanico element) {
            return element.getEspecialidad() == _opcion.getEspecialidad();
        }
    };
}
 */