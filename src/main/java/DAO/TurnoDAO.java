package DAO;

import DTO.Turno;
import Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO {

    public int create_turno(Turno p) throws Exception {
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

    public List<Turno> read_client_list() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from turno");

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


    public Turno read_turno(Integer id) throws Exception {


        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from turno where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Turno turno = new Turno();
        turno.set_id(rs.getInt("Id"));
        turno.set_active(rs.getBoolean("active"));
        turno.set_fecha(rs.getDate("fecha"));
        turno.set_hora(rs.getTime("hora"));
        turno.set_mecanico_id(rs.getInt("mecanico_id"));
        turno.set_vehiculo_id(rs.getInt("vehiculo_id"));
        turno.set_asistencia(rs.getBoolean("asistencia"));
        turno.set_ficha_mecanica_id(rs.getInt("ficha_mecanica_id"));

        return turno;
    }


    public int update_turno(Turno p) throws Exception {

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


    public int update_turno(Boolean active, Date fecha, Time hora, Integer mecanico_id, Integer vehiculo_id, Boolean asistencia, Integer ficha_mecanica_id, Integer id) throws Exception {

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


    public int delete_turno(Integer id) throws Exception {

        String sql = "delete from turno where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }

    public List<Turno> get_turno_by_mecanico(Integer mecanico_id) throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from turno where mecanico_id=?");
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

    public List<Turno> get_turno_by_ficha_mecanica(Integer ficha_mecanica_id) throws Exception {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from turno where ficha_mecanica_id=?");
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

    public List<Turno> get_turno_by_vehiculo(Integer vehiculo_id) throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from turno where vehiculo_id=?");
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

}