package DataAccess;

import Classes.Turno;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DTOTurnos implements IDTOTurnos{
    private List<Turno> _turnos;
    private String _patente;
    private int _ID;

    public DTOTurnos(){
        _turnos = new ArrayList<Turno>();
        _turnos.add(new Turno());
        _turnos.add(new Turno());
        _turnos.add(new Turno());
        _turnos.add(new Turno());
        _turnos.add(new Turno());
        _turnos.add(new Turno());
        _turnos.add(new Turno());
        _turnos.add(new Turno());
        _turnos.add(new Turno());
        _turnos.add(new Turno());
    }

    public List<Turno> obtenerTurnos(String patente){
        _patente = patente;
        return _turnos.stream().filter(getTurnosByPantente).toList();
    }

    public Turno obtenerTurno(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<Turno> turno =  _turnos.stream().filter(getByID).findFirst();
            if(turno.isEmpty())
                return null;
            return turno.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    private Predicate<Turno> getTurnosByPantente = new Predicate<Turno>() {
        @Override
        public boolean test(Turno turno) {
            return turno.getVehiculo().getPatente() == _patente;
        }
    };

    private Predicate<Turno> getByID = new Predicate<Turno>() {
        @Override
        public boolean test(Turno turno) {
            return turno.getId() == _ID;
        }
    };
}
