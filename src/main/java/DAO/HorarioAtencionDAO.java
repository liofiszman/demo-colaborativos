package DAO;

import DTO.HorarioAtencion;
import DTO.Turno;
import Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioAtencionDAO {
    public HorarioAtencionDAO() {
        try {
            List<DTO.HorarioAtencion> horarios = ReadHorarioAtencionList();
            if(horarios.isEmpty()) {
                MecanicoDAO mecanicos = new MecanicoDAO();
                DTO.Mecanico mecanico_1 = mecanicos.obtenerMecanicoNombre("Gabriel Martinez");
                DTO.Mecanico mecanico_2 = mecanicos.obtenerMecanicoNombre("Yago Marti");
                DTO.Mecanico mecanico_3 = mecanicos.obtenerMecanicoNombre("Juan Perez");
                HorarioAtencion horario;

                horario = new HorarioAtencion();
                horario.set_dia_atencion(DayOfWeek.MONDAY.toString());
                horario.set_hora_desde(Time.valueOf(LocalTime.of(8, 0, 0)));
                horario.set_hora_hasta(Time.valueOf(LocalTime.of(13, 0, 0)));

                CreateHorarioAtencion(horario,mecanico_1.get_id());
                CreateHorarioAtencion(horario,mecanico_2.get_id());
                CreateHorarioAtencion(horario,mecanico_3.get_id());

                horario.set_hora_desde(Time.valueOf(LocalTime.of(16, 0, 0)));
                horario.set_hora_hasta(Time.valueOf(LocalTime.of(20, 0, 0)));

                CreateHorarioAtencion(horario,mecanico_1.get_id());
                CreateHorarioAtencion(horario,mecanico_2.get_id());
                CreateHorarioAtencion(horario,mecanico_3.get_id());

                horario = new HorarioAtencion();
                horario.set_dia_atencion(DayOfWeek.TUESDAY.toString());
                horario.set_hora_desde(Time.valueOf(LocalTime.of(10, 0, 0)));
                horario.set_hora_hasta(Time.valueOf(LocalTime.of(18, 0, 0)));

                CreateHorarioAtencion(horario,mecanico_1.get_id());
                CreateHorarioAtencion(horario,mecanico_2.get_id());
                CreateHorarioAtencion(horario,mecanico_3.get_id());

                horario = new HorarioAtencion();
                horario.set_dia_atencion(DayOfWeek.WEDNESDAY.toString());
                horario.set_hora_desde(Time.valueOf(LocalTime.of(8, 0, 0)));
                horario.set_hora_hasta(Time.valueOf(LocalTime.of(13, 0, 0)));

                CreateHorarioAtencion(horario,mecanico_1.get_id());
                CreateHorarioAtencion(horario,mecanico_3.get_id());

                horario.set_hora_desde(Time.valueOf(LocalTime.of(16, 0, 0)));
                horario.set_hora_hasta(Time.valueOf(LocalTime.of(20, 0, 0)));

                CreateHorarioAtencion(horario,mecanico_1.get_id());
                CreateHorarioAtencion(horario,mecanico_2.get_id());

                horario = new HorarioAtencion();
                horario.set_dia_atencion(DayOfWeek.THURSDAY.toString());
                horario.set_hora_desde(Time.valueOf(LocalTime.of(10, 0, 0)));
                horario.set_hora_hasta(Time.valueOf(LocalTime.of(18, 0, 0)));

                CreateHorarioAtencion(horario,mecanico_1.get_id());
                CreateHorarioAtencion(horario,mecanico_3.get_id());

                horario = new HorarioAtencion();
                horario.set_dia_atencion(DayOfWeek.FRIDAY.toString());
                horario.set_hora_desde(Time.valueOf(LocalTime.of(9, 0, 0)));
                horario.set_hora_hasta(Time.valueOf(LocalTime.of(18, 0, 0)));

                CreateHorarioAtencion(horario,mecanico_2.get_id());
                CreateHorarioAtencion(horario,mecanico_3.get_id());
            }
        }
        catch (Exception ex) {}
    }

    public int CreateHorarioAtencion(HorarioAtencion p, int mecanico_id) throws Exception {
        String sql = "insert into horario_atencion (dia_atencion, hora_desde, hora_hasta, mecanico_id) values (?, ?, ?, ?)";

        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_dia_atencion());
        preparedStatement.setTime(2 ,p.get_hora_desde());
        preparedStatement.setTime(3 ,p.get_hora_hasta());
        preparedStatement.setInt(4 ,mecanico_id);

        return preparedStatement.executeUpdate();
    }

    public List<HorarioAtencion> ReadHorarioAtencionList() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select id,dia_atencion,hora_desde,hora_hasta,mecanico_id from horario_atencion");

        List<HorarioAtencion> horarioAtencionList = new ArrayList<>();
        while(rs.next()) {
            HorarioAtencion horarioAtencion = new HorarioAtencion();
            horarioAtencion.set_id(rs.getInt("id"));
            horarioAtencion.set_dia_atencion(rs.getString("dia_atencion"));
            horarioAtencion.set_hora_desde(rs.getTime("hora_desde"));
            horarioAtencion.set_hora_hasta(rs.getTime("hora_hasta"));
            horarioAtencion.set_mecanico_id(rs.getInt("mecanico_id"));
            horarioAtencionList.add(horarioAtencion);
        }

        return horarioAtencionList;
    }


    public HorarioAtencion ReadHorarioAtencion(Integer id) throws Exception {


        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,dia_atencion,hora_desde,hora_hasta,mecanico_id from horario_atencion where id = ?");
        preparedStatement.setInt(1 ,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        HorarioAtencion horarioAtencion = new HorarioAtencion();
        horarioAtencion.set_id(rs.getInt("id"));
        horarioAtencion.set_dia_atencion(rs.getString("dia_atencion"));
        horarioAtencion.set_hora_desde(rs.getTime("hora_desde"));
        horarioAtencion.set_hora_hasta(rs.getTime("hora_hasta"));
        horarioAtencion.set_mecanico_id(rs.getInt("mecanico_id"));

        return horarioAtencion;
    }


    public int UpdateHorarioAtencion(HorarioAtencion p) throws Exception {

        String sql = "update horario_atencion set dia_atencion=?, hora_desde=?, hora_hasta=?, mecanico_id=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_dia_atencion());
        preparedStatement.setTime(2 ,p.get_hora_desde());
        preparedStatement.setTime(3 ,p.get_hora_hasta());
        preparedStatement.setInt(4 ,p.get_mecanico_id());
        preparedStatement.setInt(5 ,p.get_id());


        return preparedStatement.executeUpdate();
    }


    public int UpdateHorarioAtencion(String dia_atencion, Time hora_desde, Time hora_hasta, Integer mecanico_id, Integer id) throws Exception {

        String sql = "update horario_atencion set dia_atencion=?, hora_desde=?, hora_hasta=?, mecanico_id=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,dia_atencion);
        preparedStatement.setTime(2 ,hora_desde);
        preparedStatement.setTime(3 ,hora_hasta);
        preparedStatement.setInt(4 ,mecanico_id);
        preparedStatement.setInt(5 ,id);

        return preparedStatement.executeUpdate();

    }


    public int DeleteHorarioAtencion(Integer id) throws Exception {

        String sql = "delete from horario_atencion where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }

    public List<HorarioAtencion> GetHorarioAtencionByMecanico (Integer mecanico_id) throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery(
                "select id,dia_atencion,hora_desde,hora_hasta,mecanico_id from horario_atencion where mecanico_id=?");
        List<HorarioAtencion> horarioAtencionList = new ArrayList<>();
        while(rs.next()) {
            HorarioAtencion horarioAtencion = new HorarioAtencion();
            horarioAtencion.set_id(rs.getInt("id"));
            horarioAtencion.set_dia_atencion(rs.getString("dia_atencion"));
            horarioAtencion.set_hora_desde(rs.getTime("hora_desde"));
            horarioAtencion.set_hora_hasta(rs.getTime("hora_hasta"));
            horarioAtencion.set_mecanico_id(rs.getInt("mecanico_id"));
            horarioAtencionList.add(horarioAtencion);
        }
        return horarioAtencionList;
    }
}
