package DAO;

import DTO.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public int create_cliente(Cliente p) throws Exception {
        String sql = "insert into cliente values (?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setString(1 ,p.get_telefono());
        preparedStatement.setString(1 ,p.get_apellido());
        preparedStatement.setString(1 ,p.get_tipo_documento());
        preparedStatement.setString(1 ,p.get_documento());

        return preparedStatement.executeUpdate();
    }

    public List<Cliente> read_client_list() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from cliente");

        List<Cliente> clienteList = new ArrayList<>();
        while(rs.next()) {
            Cliente cliente = new Cliente();
            cliente.set_id(rs.getInt("Id"));
            cliente.set_nombre(rs.getString("nombre"));
            cliente.set_telefono(rs.getString("telefono"));
            cliente.set_apellido(rs.getString("apellido"));
            cliente.set_tipo_documento(rs.getString("tipo_documento"));
            cliente.set_documento(rs.getString("documento"));
            clienteList.add(cliente);
        }

        return clienteList;
    }


    public Cliente read_cliente(Integer id) throws Exception {


        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from cliente where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Cliente cliente = new Cliente();
        cliente.set_id(rs.getInt("Id"));
        cliente.set_nombre(rs.getString("nombre"));
        cliente.set_telefono(rs.getString("telefono"));
        cliente.set_apellido(rs.getString("apellido"));
        cliente.set_tipo_documento(rs.getString("tipo_documento"));
        cliente.set_documento(rs.getString("documento"));

        return cliente;
    }


    public int update_cliente(Cliente p) throws Exception {

        String sql = "update cliente set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setString(1 ,p.get_telefono());
        preparedStatement.setString(1 ,p.get_apellido());
        preparedStatement.setString(1 ,p.get_tipo_documento());
        preparedStatement.setString(1 ,p.get_documento());
        preparedStatement.setInt(2,p.get_id());


        return preparedStatement.executeUpdate();
    }


    public int update_cliente(String nombre, Integer id) throws Exception {

        String sql = "update cliente set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,nombre);
        preparedStatement.setInt(2,id);

        return preparedStatement.executeUpdate();

    }


    public int delete_cliente(Integer id) throws Exception {

        String sql = "delete from cliente where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }


}