package DAO;

import Classes.FichaConformidad;
import Classes.Opcion;
import DTO.FichaMecanica;
import DTO.HorarioAtencion;
import DTO.Turno;
import DataAccess.IDAOTurno;
import Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TurnoDAO implements IDAOTurno {
    FichaMecanicaDAO _fichaMecanicaDAO = new FichaMecanicaDAO();
    FichaConformidadDAO _fichaConformidadDAO = new FichaConformidadDAO();
    HorarioAtencionDAO _horarioAtencionDAO = new HorarioAtencionDAO();
    Opcion _opcion;

    public int CreateTurno(Turno p) throws Exception {
        String sql = "insert into turno (active, fecha, hora, mecanico_id, vehiculo_id, asistencia, ficha_mecanica_id) values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setBoolean(1 ,p.get_active());
        preparedStatement.setDate(2 ,p.get_fecha());
        preparedStatement.setTime(3 ,p.get_hora());
        preparedStatement.setInt(4 ,p.get_mecanico_id());
        preparedStatement.setInt(5 ,p.get_vehiculo_id());
        preparedStatement.setBoolean(5 ,p.get_asistencia());
        preparedStatement.setInt(5 ,p.get_ficha_mecanica_id());

        return preparedStatement.executeUpdate();
    }

    public List<Turno> ReadTurnoList() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery(
                "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id from turno");

        List<Turno> turnoList = new ArrayList<>();
        while(rs.next()) {
            Turno turno = new Turno();
            turno.set_id(rs.getInt("id"));
            turno.set_active(rs.getBoolean("active"));
            turno.set_fecha(rs.getDate("fecha"));
            turno.set_hora(rs.getTime("hora"));
            turno.set_mecanico_id(rs.getInt("mecanico_id"));
            turno.set_vehiculo_id(rs.getInt("vehiculo_id"));
            turno.set_asistencia(rs.getBoolean("asistencia"));
            turno.set_ficha_mecanica_id(rs.getInt("ficha_mecanica_id"));
            turnoList.add(turno);
        }

        return turnoList;
    }


    public Turno ReadTurno(Integer id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id from turno where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Turno turno = new Turno();
        turno.set_id(rs.getInt("id"));
        turno.set_active(rs.getBoolean("active"));
        turno.set_fecha(rs.getDate("fecha"));
        turno.set_hora(rs.getTime("hora"));
        turno.set_mecanico_id(rs.getInt("mecanico_id"));
        turno.set_vehiculo_id(rs.getInt("vehiculo_id"));
        turno.set_asistencia(rs.getBoolean("asistencia"));
        turno.set_ficha_mecanica_id(rs.getInt("ficha_mecanica_id"));

        return turno;
    }


    public int UpdateTurno(Turno p) throws Exception {

        String sql = "update turno set active=?, fecha=?, hora=?, mecanico_id=?, vehiculo_id=?, asistencia=?, ficha_mecanica_id=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setBoolean(1 ,p.get_active());
        preparedStatement.setDate(2 ,p.get_fecha());
        preparedStatement.setTime(3 ,p.get_hora());
        preparedStatement.setInt(4 ,p.get_mecanico_id());
        preparedStatement.setInt(5 ,p.get_vehiculo_id());
        preparedStatement.setBoolean(6 ,p.get_asistencia());
        preparedStatement.setInt(7 ,p.get_ficha_mecanica_id());
        preparedStatement.setInt(8 ,p.get_id());


        return preparedStatement.executeUpdate();
    }


    public int UpdateTurno(Boolean active, Date fecha, Time hora, Integer mecanico_id, Integer vehiculo_id, Boolean asistencia, Integer ficha_mecanica_id, Integer id) throws Exception {

        String sql = "update turno set active=?, fecha=?, hora=?, mecanico_id=?, vehiculo_id=?, asistencia=?, ficha_mecanica_id=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setBoolean(1 ,active);
        preparedStatement.setDate(2 ,fecha);
        preparedStatement.setTime(3 ,hora);
        preparedStatement.setInt(4 ,mecanico_id);
        preparedStatement.setInt(5 ,vehiculo_id);
        preparedStatement.setBoolean(6 ,asistencia);
        preparedStatement.setInt(7 ,ficha_mecanica_id);
        preparedStatement.setInt(8 ,id);

        return preparedStatement.executeUpdate();

    }

    public int DeleteTurno(Integer id) throws Exception {

        String sql = "delete from turno where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }

    public List<Turno> GetTurnoByMecanico(Integer mecanico_id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id from turno where mecanico_id=?");
        preparedStatement.setInt(1,mecanico_id);
        ResultSet rs  = preparedStatement.executeQuery();
        return rsToList(rs);
    }

    public List<Turno> GetTurnoByFichaMecanica(Integer ficha_mecanica_id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id from turno where ficha_mecanica_id=?");
        preparedStatement.setInt(1,ficha_mecanica_id);
        ResultSet rs  = preparedStatement.executeQuery();
        return rsToList(rs);
    }

    public List<Turno> GetTurnoByVehiculo(Integer vehiculo_id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id from turno where vehiculo_id=?");
        preparedStatement.setInt(1,vehiculo_id);
        ResultSet rs  = preparedStatement.executeQuery();
        return rsToList(rs);
    }

    private List<Turno> rsToList(ResultSet rs) throws Exception {
        List<Turno> turnoList = new ArrayList<>();
        while(rs.next()) {
            Turno turno = new Turno();
            turno.set_id(rs.getInt("Id"));
            turno.set_active(rs.getBoolean("active"));
            turno.set_fecha(rs.getDate("fecha"));
            turno.set_hora(rs.getTime("hora"));
            turno.set_mecanico_id(rs.getInt("mecanico_id"));
            turno.set_vehiculo_id(rs.getInt("vehiculo_id"));
            turno.set_asistencia(rs.getBoolean("asistencia"));
            turno.set_ficha_mecanica_id(rs.getInt("ficha_mecanica_id"));
            turnoList.add(turno);
        }
        return turnoList;
    }

    public List<DTO.Turno> obtenerTurnos(String patente){
        try {
            return GetTurnoByPatente(patente);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<Turno> GetTurnoByPatente(String patente) {
        try {
            PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                    "select t.id,t.active,t.fecha,t.hora,t.mecanico_id,t.vehiculo_id,t.asistencia,t.ficha_mecanica_id " +
                            "from turno t inner join vehiculo v on t.vehiculo_id = v.id where v.patente = ?");
            preparedStatement.setString(1,patente);
            ResultSet rs  = preparedStatement.executeQuery();
            return rsToList(rs);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<DTO.Turno> obtenerTurnos(LocalDate fechaDesde, LocalDate fechaHasta, boolean onlyActive) {
        try {
            return GetTurnoByFechas(fechaDesde,fechaHasta,onlyActive);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<Turno> GetTurnoByFechas(LocalDate fechaDesde, LocalDate fechaHasta, boolean onlyActive) {
        try {
            PreparedStatement preparedStatement;
            if(onlyActive)
                preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                        "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id " +
                                "from turno where active and fecha between ? and ?");
            else
                preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                        "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id " +
                                "from turno where fecha between ? and ?");
            preparedStatement.setDate(1,Date.valueOf(fechaDesde));
            preparedStatement.setDate(2,Date.valueOf(fechaHasta));
            ResultSet rs  = preparedStatement.executeQuery();
            return rsToList(rs);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<Classes.Turno> obtenerTurnosC(Opcion opcion, List<DTO.Mecanico> mecanicos) throws Exception {

        List<Classes.Turno> opciones = new ArrayList<Classes.Turno>();
        List<Turno> turnosDados = GetTurnoByFechas(opcion.getFecha(), opcion.getFechaHasta(), true);

        for (int i = 0; i < 8; i++) {
            if(opcion.getFecha().plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY)
                continue;

            for (DTO.Mecanico mecanico : mecanicos) {
                List<HorarioAtencion> horariosAtencion = _horarioAtencionDAO.getByMecanico(mecanico.get_id());
                for (HorarioAtencion horario : horariosAtencion){
                    // Buscar quien trabaja el día esperado.
                    LocalDate fecha = opcion.getFecha().plusDays(i);
                    if(fecha.getDayOfWeek().toString() != horario.get_dia_atencion())
                        continue;
                    _opcion.setFecha(fecha);
                    _opcion.setMecanico(mecanico);

                    // armar slots de tiempo. 30m.
                    LocalTime horaDesde = horario.get_hora_desde().toLocalTime();
                    LocalTime horaHasta = horario.get_hora_hasta().toLocalTime();
                    // ver si ya tiene trabajo previo.
                    while (horaDesde.isBefore(horaHasta)) {
                        _opcion.setHora(horaDesde);
                        if(turnosDados.stream().anyMatch(checkExists)) {
                            horaDesde = horaDesde.plusMinutes(30);
                            continue;
                        }

                        opciones.add(new Classes.Turno(fecha, horaDesde, mecanico));
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
            return turno.get_fecha().toLocalDate().isEqual(_opcion.getFecha())
                    && turno.get_hora().toLocalTime().getHour() == _opcion.getHora().getHour()
                    && turno.get_hora().toLocalTime().getMinute() == _opcion.getHora().getMinute()
                    && turno.get_mecanico_id() == _opcion.getMecanico().get_id();
        }
    };

    public DTO.Turno obtenerTurno(String id) {
        try {
            return ReadTurno(Integer.valueOf(id));
        }
        catch (Exception ex) {
            return null;
        }
    }

    public Classes.Turno obtenerTurnoCompleto(String id) {
        try {
            Classes.Turno turno = new Classes.Turno();
            Turno turnoData = ReadTurno(Integer.valueOf(id));

            turno.setId(turnoData.get_id());
            turno.setActive(turnoData.get_active());
            turno.setFecha(turnoData.get_fecha().toLocalDate());
            turno.setHora(turnoData.get_hora().toLocalTime());
            turno.setAsistnecia(turnoData.get_asistencia());

            FichaMecanica fichaMecanica = _fichaMecanicaDAO.obtenerFichaMecanica(turnoData.get_ficha_mecanica_id());
            if(fichaMecanica != null) {
                DTO.FichaConformidad fichaConformidad = _fichaConformidadDAO.obtenerFichaConformidad(fichaMecanica.get_ficha_conformidad_id());
                turno.setFichaMecanica(new Classes.FichaMecanica(
                        fichaMecanica.get_id(), fichaMecanica.get_actividades(), fichaMecanica.get_repuestos(), null));

                if(fichaConformidad != null)
                    turno.getFichaMecanica().setFichaConformidad(
                            new FichaConformidad(fichaConformidad.get_id(), fichaConformidad.get_firmada_conforme()));
            }

            return turno;
        }
        catch (Exception ex) {
            return null;
        }
    }

    public void registrarAsistencia(String id) {
        try {
            DTO.Turno turno = ReadTurno(Integer.valueOf(id));
            turno.set_asistencia(true);
            UpdateTurno(turno);
        }
        catch (Exception ex) {

        }
    }

    public void cancelarTurno(String id) {
        Turno turno = obtenerTurno(id);
        turno.set_active(false);
        turno.set_asistencia(false);
        try {UpdateTurno(turno);}
        catch (Exception ex) {}
    }

    public int addTurno(Turno turno, Opcion opcion) {
        return 0;
    }

    public void registrarActividades(String numeroTurno, String actividadesText, String insumosText) {
        Turno turno = obtenerTurno(numeroTurno);
        try {
            _fichaMecanicaDAO.registrarActividades(turno.get_ficha_mecanica_id(), actividadesText, insumosText);
        }
        catch (Exception ex) {}
    }

    public void firmaConforme(String numeroTurno) {
        Turno turno = obtenerTurno(numeroTurno);
        FichaMecanica fichaMecanica = _fichaMecanicaDAO.obtenerFichaMecanica(turno.get_ficha_mecanica_id());
        _fichaConformidadDAO.firmar(fichaMecanica.get_ficha_conformidad_id(), true);
    }

    public void firmaInconforme(String numeroTurno) {
        Turno turno = obtenerTurno(numeroTurno);
        FichaMecanica fichaMecanica = _fichaMecanicaDAO.obtenerFichaMecanica(turno.get_ficha_mecanica_id());
        _fichaConformidadDAO.firmar(fichaMecanica.get_ficha_conformidad_id(), false);
    }
}
