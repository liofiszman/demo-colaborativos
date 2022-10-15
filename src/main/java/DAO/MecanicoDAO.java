package DAO;

import DTO.Mecanico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MecanicoDAO {

    public int CreateMecanico(Mecanico p) throws Exception {
        String sql = "insert into mecanico (nombre, telefono, apellido, tipo_documento, documento) values (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setString(2 ,p.get_telefono());
        preparedStatement.setString(3 ,p.get_apellido());
        preparedStatement.setString(4 ,p.get_tipo_documento());
        preparedStatement.setString(5 ,p.get_documento());

        return preparedStatement.executeUpdate();
    }

    public List<Mecanico> ReadMecanicoList() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from mecanico");

        List<Mecanico> mecanicoList = new ArrayList<>();
        while(rs.next()) {
            Mecanico mecanico = new Mecanico();
            mecanico.set_id(rs.getInt("Id"));
            mecanico.set_nombre(rs.getString("nombre"));
            mecanico.set_telefono(rs.getString("telefono"));
            mecanico.set_apellido(rs.getString("apellido"));
            mecanico.set_tipo_documento(rs.getString("tipo_documento"));
            mecanico.set_documento(rs.getString("documento"));
            mecanicoList.add(mecanico);
        }

        return mecanicoList;
    }


    public Mecanico ReadMecanico(Integer id) throws Exception {


        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from mecanico where id = ?");
        preparedStatement.setInt(1 ,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Mecanico mecanico = new Mecanico();
        mecanico.set_id(rs.getInt("Id"));
        mecanico.set_nombre(rs.getString("nombre"));
        mecanico.set_telefono(rs.getString("telefono"));
        mecanico.set_apellido(rs.getString("apellido"));
        mecanico.set_tipo_documento(rs.getString("tipo_documento"));
        mecanico.set_documento(rs.getString("documento"));

        return mecanico;
    }


    public int UpdateMecanico(Mecanico p) throws Exception {

        String sql = "update mecanico set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setString(2 ,p.get_telefono());
        preparedStatement.setString(3 ,p.get_apellido());
        preparedStatement.setString(4 ,p.get_tipo_documento());
        preparedStatement.setString(5 ,p.get_documento());
        preparedStatement.setInt(6 ,p.get_id());


        return preparedStatement.executeUpdate();
    }


    public int UpdateMecanico(String nombre, String telefono, String apellido, String tipo_documento, String documento, Integer id) throws Exception {

        String sql = "update mecanico set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,nombre);
        preparedStatement.setString(2 ,telefono);
        preparedStatement.setString(3 ,apellido);
        preparedStatement.setString(4 ,tipo_documento);
        preparedStatement.setString(5 ,documento);
        preparedStatement.setInt(6 ,id);

        return preparedStatement.executeUpdate();

    }


    public int DeleteMecanico(Integer id) throws Exception {

        String sql = "delete from mecanico where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }


}
