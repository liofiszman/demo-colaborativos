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
            ArrayList<HorarioAtencion> horarios = new ArrayList<HorarioAtencion>();
            horarios.add(new HorarioAtencion(1001, DayOfWeek.MONDAY,
                    LocalTime.of(8, 0, 0), LocalTime.of(13,0,0)));
            horarios.add(new HorarioAtencion(1002, DayOfWeek.MONDAY,
                    LocalTime.of(17, 0, 0), LocalTime.of(20,0,0)));
            horarios.add(new HorarioAtencion(1003, DayOfWeek.TUESDAY,
                    LocalTime.of(10, 0, 0), LocalTime.of(17,0,0)));
            horarios.add(new HorarioAtencion(1004, DayOfWeek.WEDNESDAY,
                    LocalTime.of(10, 0, 0), LocalTime.of(17,0,0)));
            horarios.add(new HorarioAtencion(1005, DayOfWeek.FRIDAY,
                    LocalTime.of(9, 0, 0), LocalTime.of(16,0,0)));
            _mecanicos.add(new Mecanico(001, "Gabriel Martinez", "DNI",
                    "33.333.333", "3323232323", "Mecánica en general", horarios));
            horarios.add(new HorarioAtencion(1006, DayOfWeek.THURSDAY,
                    LocalTime.of(9, 0, 0), LocalTime.of(13,0,0)));
            _mecanicos.add(new Mecanico(002, "Otro Juan Perez", "DNI",
                    "44.444.444", "1231231233", "Chapa y pintura",horarios));
            horarios.add(new HorarioAtencion(1006, DayOfWeek.THURSDAY,
                    LocalTime.of(15, 0, 0), LocalTime.of(20,0,0)));
            _mecanicos.add(new Mecanico(123, "Yago Marti", "DNI",
                    "55.555.555", "5559595959", "Frenos",horarios));
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
