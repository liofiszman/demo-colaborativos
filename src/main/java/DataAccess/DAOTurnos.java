package DataAccess;

import Classes.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DAOTurnos implements IDAOTurno {
    private List<Turno> _turnos;
    private String _patente;
    private int _ID;
    private int id = 123;
    private Opcion _opcion;
    DAOVehiculo _vehiculos = new DAOVehiculo();
    DAOMecanico _mecanicos = new DAOMecanico();
    DAOFichaMecanica _fichaMecanica = new DAOFichaMecanica();

    public DAOTurnos(){
        if(_turnos == null || _turnos.isEmpty()) {
            _turnos = new ArrayList<Turno>();
            _turnos.add(new Turno(123, java.time.LocalDate.of(2022,10,21),
                    LocalTime.of(10,0,0), _mecanicos.obtenerMecanico("001"),
                    _vehiculos.obtenerVehiculo("001"), _fichaMecanica.obtenerFichaMecanica("001") ));
            _turnos.add(new Turno(001, java.time.LocalDate.now(),
                    LocalTime.of(10,0,0), _mecanicos.obtenerMecanico("123"),
                    _vehiculos.obtenerVehiculo("002"), _fichaMecanica.obtenerFichaMecanica("002") ));
        }
    }

    public List<Turno> obtenerTurnos(String patente){
        _patente = patente;
        return _turnos.stream().filter(getTurnosByPantente).toList();
    }

    private Predicate<Turno> getTurnosByPantente = new Predicate<Turno>() {
        @Override
        public boolean test(Turno turno) {
            return turno.getPatente() == _patente;
        }
    };

    public int addTurno(Turno turno, Opcion opcion) {
        id++;
        turno.setId(id);

        turno.setVehiculo(_vehiculos.obtenerVehiculoPatente(opcion.getPatente(), opcion.getCompania(), opcion.getCliente()));
        turno.setFichaMecanica(_fichaMecanica.obtenerFichaMecanica("0"));

        _turnos.add(turno);
        return turno.getId();
    }

    public String obtenerTurnoID() {
        return String.valueOf(id);
    }

    public Turno obtenerTurno(String id){
        try {
            _ID = Integer.valueOf(id);
            Optional<Turno> turno =  _turnos.stream().filter(getByID).filter(checkActive).findFirst();
            if(turno.isEmpty())
                return null;
            return turno.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public void registrarAsistencia(String id) {
        _ID = Integer.valueOf(id);
        Turno turno =  _turnos.stream().filter(getByID).filter(checkActive).findFirst().get();

        turno.setAsistnecia(true);
        _turnos.removeIf(getByID);
        _turnos.add(turno);
    }
    public void cancelarTurno(String id) {
        _ID = Integer.valueOf(id);
        Turno turno =  _turnos.stream().filter(getByID).filter(checkActive).findFirst().get();

        turno.setAsistnecia(false);
        turno.setActive(false);
        _turnos.removeIf(getByID);
        _turnos.add(turno);
    }

    public void registrarActividades(String numeroTurno, String actividadesText, String insumosText){
        _ID = Integer.valueOf(id);
        Turno turno =  _turnos.stream().filter(getByID).filter(checkActive).findFirst().get();

        turno.getFichaMecanica().setActividades(actividadesText);
        turno.getFichaMecanica().setRepuestos(insumosText);

        _fichaMecanica.actualizar(turno.getFichaMecanica());
        _turnos.removeIf(getByID);
        _turnos.add(turno);
    }

    public void firmaConforme(String numeroTurno){
        firmar(numeroTurno, true);
    }

    private void firmar(String numeroTurno, boolean conforme) {
        _ID = Integer.valueOf(id);
        Turno turno =  _turnos.stream().filter(getByID).filter(checkActive).findFirst().get();
        turno.getFichaMecanica().getFichaConformidad().setFirmaConforme(conforme);
        turno.getFichaMecanica().getFichaConformidad().setFirmada(true);

        _fichaMecanica.actualizar(turno.getFichaMecanica());

        _turnos.removeIf(getByID);
        _turnos.add(turno);
    }

    public void firmaInconforme(String numeroTurno){
        firmar(numeroTurno, false);_ID = Integer.valueOf(id);
    }

    public List<Turno> obtenerTurnos(Opcion opcion, List<Mecanico> mecanicos){
        _opcion = opcion;
        List<Turno> opciones = new ArrayList<Turno>();
        List<Turno> turnosDados = _turnos.stream()
                .filter(checkActive)
                .filter(getFechaDesde)
                .filter(getFechaHasta)
                .toList();

        for (int i = 0; i < 8; i++) {
            if(opcion.getFecha().plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY)
                continue;

            for (Mecanico mecanico : mecanicos) {
                for (HorarioAtencion horario : mecanico.getHorariosAtencion()){
                    // Buscar quien trabaja el día esperado.
                    LocalDate fecha = opcion.getFecha().plusDays(i);
                    if(fecha.getDayOfWeek() != horario.getDiaAtencion())
                        continue;
                    _opcion.setFecha(fecha);
                    _opcion.setMecanico(mecanico);

                    // armar slots de tiempo. 30m.
                    LocalTime horaDesde = horario.getHoraDesde();
                    LocalTime horaHasta = horario.getHoraHasta();
                    // ver si ya tiene trabajo previo.
                    while (horaDesde.isBefore(horaHasta)) {
                        _opcion.setHora(horaDesde);
                        if(turnosDados.stream().anyMatch(checkExists)) {
                            horaDesde = horaDesde.plusMinutes(30);
                            continue;
                        }

                        opciones.add(new Turno(0, fecha, horaDesde, mecanico));
                        horaDesde = horaDesde.plusMinutes(30);
                    }
                }
            }
        }

        return opciones;
    }

    private Predicate<Turno> checkExists = new Predicate<Turno>() {
        @Override
        public boolean test(Turno turno) {
            return turno.getFecha().isEqual(_opcion.getFecha())
                    && turno.getHora().getHour() == _opcion.getHora().getHour()
                    && turno.getHora().getMinute() == _opcion.getHora().getMinute()
                    && turno.getMecanico().getId() == _opcion.getMecanico().getId();
        }
    };

    private Predicate<Turno> getFechaDesde = new Predicate<Turno>() {
        @Override
        public boolean test(Turno turno) {
            return turno.getFecha().isAfter(_opcion.getFecha());
        }
    };

    private Predicate<Turno> getFechaHasta = new Predicate<Turno>() {
        @Override
        public boolean test(Turno turno) {
            return turno.getFecha().isBefore(_opcion.getFecha().plusDays(7 ));
        }
    };

    private Predicate<Turno> checkActive = new Predicate<Turno>() {
        @Override
        public boolean test(Turno turno) {
            return turno.isActive();
        }
    };

    private Predicate<Turno> getByID = new Predicate<Turno>() {
        @Override
        public boolean test(Turno turno) {
            return turno.getId() == _ID;
        }
    };
}
