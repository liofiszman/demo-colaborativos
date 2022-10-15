package DAO;

import Classes.Mecanico;
import Classes.Opcion;
import DTO.Turno;
import DataAccess.IDAOTurno;
import Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO implements IDAOTurno {

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
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery(
                "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id from turno where mecanico_id=?");
        return rsToList(rs);
    }

    public List<Turno> GetTurnoByFichaMecanica(Integer ficha_mecanica_id) throws Exception {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery(
                "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id from turno where ficha_mecanica_id=?");
        return rsToList(rs);
    }

    public List<Turno> GetTurnoByVehiculo(Integer vehiculo_id) throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery(
                "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id from turno where vehiculo_id=?");
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
            Statement st = Utils.DBConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(
                    "select t.id,t.active,t.fecha,t.hora,t.mecanico_id,t.vehiculo_id,t.asistencia,t.ficha_mecanica_id " +
                            "from turno t inner join vehiculo v on t.vehiculo_id = v.id where v.patente = ?");
            return rsToList(rs);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<DTO.Turno> obtenerTurnos(LocalDate fechaDesde, LocalDate fechaHasta) {
        try {
            return GetTurnoByFechas(fechaDesde,fechaHasta);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<Turno> GetTurnoByFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        try {
            Statement st = Utils.DBConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(
                    "select id,active,fecha,hora,mecanico_id,vehiculo_id,asistencia,ficha_mecanica_id " +
                            "from turno where fecha between ? and ?");
            return rsToList(rs);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<DTO.Turno> obtenerTurnos(Opcion opcion, List<DTO.Mecanico> mecanicos) {
        return null;
    }

    public DTO.Turno obtenerTurno(String id) {
        try {
            return ReadTurno(Integer.valueOf(id));
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

    }

    public int addTurno(Turno turno, Opcion opcion) {
        return 0;
    }

    public String obtenerTurnoID() {
        return null;
    }

    public void registrarActividades(String numeroTurno, String actividadesText, String insumosText) {

    }

    public void firmaConforme(String numeroTurno) {

    }

    public void firmaInconforme(String numeroTurno) {

    }
}
